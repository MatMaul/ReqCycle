package org.eclipse.reqcycle.traceability.storage;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITraceabilityStorage {

	/**
	 * Save the instance of {@link ITraceabilityStorage}
	 */
	void save();

	/**
	 * Create an upward relation ship
	 * 
	 * @param kind
	 *            the kind of relation ship
	 * @param container
	 *            the container of the relation ship
	 * @param source
	 *            the source of the upward relationship
	 * @param targets
	 *            the targets of the upward relationship
	 */
	void newUpwardRelationShip(TType kind, Reachable container,
			Reachable source, Reachable... targets);

	/**
	 * Get a reachable from the storage
	 * 
	 * @param uri
	 * @return
	 */
	Reachable getReachable(String uri);

	Iterable<Pair<Link, Reachable>> getTraceability(Reachable r,
			DIRECTION direction);

	/**
	 * Dispose the storage
	 */
	void dispose();

	/**
	 * start a transaction for the storage
	 */
	void startTransaction();

	/**
	 * Commit the modification during the curent transaction
	 */
	void commit();

	/**
	 * Cancel the modification made during the current transaction
	 */
	void rollback();

	/**
	 * Removes all the traceability links contained in the given reachable
	 * 
	 * @param reachable
	 */
	void removeTraceabilityLinksContainedIn(Reachable reachable);

	void removeUpwardRelationShip(String kind, Reachable container,
			Reachable source, Reachable... targets);

}
