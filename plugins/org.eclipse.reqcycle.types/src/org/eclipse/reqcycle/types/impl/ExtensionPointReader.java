package org.eclipse.reqcycle.types.impl;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.reqcycle.types.Activator;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.types.ITypeChecker;
import org.osgi.framework.Bundle;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class ExtensionPointReader {
	public static final ImageDescriptor DEFAULT_ICON = ImageDescriptor
			.createFromURL(Activator.getDefault().getBundle()
					.getEntry("/icons/type.gif"));

	public static final String EXT_ID = "types";

	public Map<String, IType> read() {
		return Maps.uniqueIndex(Iterables.transform(Arrays.asList(Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, EXT_ID)), new Conf2Type()),
				new Function<IType, String>() {
					public String apply(IType type) {
						return type.getId();
					}
				});
	}

	public class Conf2Type implements Function<IConfigurationElement, IType> {
		public IType apply(IConfigurationElement c) {
			Bundle bundle = Platform.getBundle(c.getContributor().getName());
			Type type = null;
			try {
				@SuppressWarnings("unchecked")
				Class<? extends ITypeChecker> aClass = (Class<? extends ITypeChecker>) bundle
						.loadClass(c.getAttribute("checker"));
				// if (IInjectedTypeChecker.class.isAssignableFrom(aClass)) {
				// type = new Type.InjectedType();
				// } else {
				type = new Type();
				// }
				type.setChecker(aClass);

				String id = c.getAttribute("id");
				type.setId(id);

				String label = c.getAttribute("label");
				if (label == null || label.length() == 0) {
					label = id;
				}
				type.setLabel(label);

				String icon = c.getAttribute("icon");
				ImageDescriptor desc = DEFAULT_ICON;
				if (icon != null && icon.length() >= 0) {

					desc = ImageDescriptor.createFromURL(bundle.getEntry(icon));
				}
				type.setIcon(desc);

				type.setSubTypeOf(c.getAttribute("subTypeOf"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return type;
		}
	}
}
