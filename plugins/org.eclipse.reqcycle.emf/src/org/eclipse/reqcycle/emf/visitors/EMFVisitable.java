package org.eclipse.reqcycle.emf.visitors;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.uri.utils.ReachableUtils;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public class EMFVisitable implements IVisitable, IAdaptable {

	Resource resource = null;
	private URI uri;

	public EMFVisitable(URI uri) {
		this.uri = uri;
	}

	private Resource getResource(URI uri) {
		ResourceSet set = getResourceSet();
		Resource r = null;
		try {
			r = set.getResource(uri.trimFragment(), true);
		} catch (WrappedException e) {
			r = set.getResource(uri.trimFragment(), false);
		}
		return r;
	}

	protected ResourceSet getResourceSet() {
		return EMFUtils.getFastAndUnresolvingResourceSet();
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.start(this);
		resource = getResource(uri);
		if (resource != null) {
			Notifier start = resource;
			if (uri.fragment() != null && uri.fragment().length() > 0) {
				start = resource.getEObject(uri.fragment());
			}
			if (visitor.visit(start, this)) {
				Iterator<EObject> i = getAllProperContents(start);
				if (i != null) {
					while (i.hasNext()) {
						if (!visitor.visit(i.next(), this)) {
							break;
						}
					}
				}
			}
		}
		visitor.end(this);
	}

	public Iterator<EObject> getAllProperContents(Notifier start) {
		if (start instanceof EObject) {
			return EcoreUtil.getAllContents((EObject) start, false);
		} else if (start instanceof Resource) {
			return EcoreUtil.getAllProperContents((Resource) start, false);
		}
		return null;
	}

	@Override
	public void dispose() {
		if (resource != null) {
			resource.unload();
		}
	}

	public String getResourceTimeStamp() {
		long timeStamp = 0;
		boolean error = false;
		if (uri.isPlatformResource()) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(uri.toPlatformString(true)));
			if (file != null && file.exists()) {
				timeStamp = file.getLocalTimeStamp();
			} else {
				// the file does not exist : error
				error = true;
			}
		}
		if (timeStamp == 0 && !error) {
			if (uri.isFile()) {
				File f = new File(uri.path());
				if (f.exists()) {
					timeStamp = f.lastModified();
				} else {
					// the file does not exist : error
					error = true;
				}
			}
		}
		if (timeStamp == 0 && !error) {
			resource = getResource(uri);
			timeStamp = resource.getTimeStamp();
		}
		if (!error
				&& (timeStamp == URIConverter.NULL_TIME_STAMP || timeStamp == 0)) {
			try {
				return ReachableUtils
						.hashStream(resource.getResourceSet().getURIConverter()
								.createInputStream(resource.getURI()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(timeStamp);
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
