package org.eclipse.reqcycle.sesame.handlers;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.reqcycle.sesame.graph.SailBusinessOperations;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.tinkerpop.blueprints.Vertex;

public class SesameReachableObject implements ReachableObject {

	private Reachable reachable;
	@Inject
	IReachableCreator creator;
	private SailBusinessOperations op;

	public SesameReachableObject(Reachable t, SailBusinessOperations op) {
		this.reachable = t;
		this.op = op;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		SesameVisitable sesameVisitable = new SesameVisitable(reachable);
		ZigguratInject.inject(sesameVisitable);
		return sesameVisitable;
	}

	@Override
	public String getRevisionIdentification() {
		String path = reachable.getPath();
		if ("file".equals(reachable.getScheme())) {
			File f = new File(path);
			if (f.exists()) {
				return String.valueOf(f.lastModified());
			}
		} else if ("platform".equals(reachable.getScheme())) {
			IFile f = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(path));
			if (f.exists()) {
				return String.valueOf(f.getModificationStamp());
			}
		}
		return null;
	}

	@Override
	public Reachable getReachable(Object o) {
		if (o instanceof Vertex) {
			Vertex vertex = (Vertex) o;
			return op.getReachable(vertex, creator);
		} else  {
			return this.reachable;
		}
	}
}
