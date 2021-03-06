package org.eclipse.reqcycle.traceability.storage.blueprints.storage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.blueprints.graph.ISpecificGraphProvider;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.collect.Lists;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;

public class GraphStorage implements ITraceabilityStorage {

	@Inject
	IReachableCreator creator;
	private Graph graph;
	private ISpecificGraphProvider.IBusinessOperations graphUtils;

	public GraphStorage(Graph graph) {
		this(graph, null);
	}

	public GraphStorage(Graph graph,
			ISpecificGraphProvider.IBusinessOperations operations) {
		this.graph = graph;
		if (operations == null) {
			this.graphUtils = new GraphUtils();
			ZigguratInject.inject(this.graphUtils);
		} else {
			this.graphUtils = operations;
		}
	}

	@Override
	public void save() {
		if (graph instanceof TransactionalGraph) {
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.commit();
		}
	}

	@Override
	public void newUpwardRelationShip(TType kind, Reachable container,
			Reachable source, Reachable... targets) {
		for (Reachable target : targets) {
			Vertex vertex = graphUtils.addTraceabilityRelation(graph, source,
					target, kind);
			graphUtils.addChildrenRelation(graph,
					graphUtils.getVertex(graph, container), vertex);
		}
	}

	@Override
	public Reachable getReachable(String uri) {
		Vertex v = graphUtils.getVertex(graph, uri);
		if (v != null) {
			Reachable r;
			try {
				r = creator.getReachable(new URI(uri));
				Map<String, String> properties = graphUtils.getProperties(v);
				for (String s : properties.keySet()) {
					r.put(s, (String) properties.get(s));
				}
				return r;
			} catch (URISyntaxException e) {
			}
		}
		return null;
	}

	@Override
	public Iterable<Pair<Link, Reachable>> getTraceability(Reachable r,
			DIRECTION direction) {
		Direction graphDirection = null;
		if (direction == DIRECTION.DOWNWARD) {
			graphDirection = Direction.IN;
		} else {
			graphDirection = Direction.OUT;
		}
		Vertex v = graph.getVertex(r.toString());
		if (v == null) {
			return Lists.newArrayList();
		}
		Iterable<Vertex> tracVertexes = graphUtils.getTraceability(v,
				graphDirection);
		List<Pair<Link, Reachable>> result = new LinkedList<Pair<Link, Reachable>>();
		for (Vertex trac : tracVertexes) {
			Vertex target = graphUtils.getTraceabilityTarget(trac,
					graphDirection);
			Reachable rTarget = getReachable((String) target.getId());
			Link link = new Link(graphUtils.getKind(trac), r, rTarget);
			result.add(new Pair<Link, Reachable>(link, rTarget));
		}
		return result;
	}

	@Override
	public void dispose() {
		graph.shutdown();
	}

	@Override
	public void startTransaction() {
	}

	@Override
	public void commit() {
		if (graph instanceof TransactionalGraph) {
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.commit();
		}
	}

	@Override
	public void rollback() {
		if (graph instanceof TransactionalGraph) {
			TransactionalGraph tGraph = (TransactionalGraph) graph;
			tGraph.rollback();
		}

	}

	@Override
	public void removeTraceabilityLinksContainedIn(Reachable reachable) {
		Iterable<Vertex> traceabilities = graphUtils.getTraceabilityIn(graph,
				reachable);
		for (Vertex v : traceabilities) {
			graphUtils.delete(v);
		}

	}

	@Override
	public void removeUpwardRelationShip(String kind, Reachable container,
			Reachable source, Reachable... targets) {
		// TODO Auto-generated method stub

	}

}
