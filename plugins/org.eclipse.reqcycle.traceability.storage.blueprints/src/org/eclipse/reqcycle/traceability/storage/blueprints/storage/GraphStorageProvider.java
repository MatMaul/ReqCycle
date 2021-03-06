package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.IGraphProvider;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class GraphStorageProvider implements IStorageProvider {

	@Inject
	IGraphProvider graphProvider;

	@Override
	public ITraceabilityStorage getStorage(String path) {
		GraphStorage graphStorage = null;
		if (getProvider() instanceof ISpecificGraphProvider) {
			ISpecificGraphProvider specProvider = (ISpecificGraphProvider) getProvider();
			graphStorage = new GraphStorage(specProvider.getGraph(path),
					specProvider.getBusinessOperation());
		} else {
			graphStorage = new GraphStorage(getProvider().getGraph(path));
		}
		ZigguratInject.inject(graphStorage);
		return graphStorage;
	}

	protected IGraphProvider getProvider() {
		return graphProvider;
	}

}
