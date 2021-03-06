package org.eclipse.reqcycle.uri;

import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface IReachableManager {

	/**
	 * Returns a {@link IReachableHandler} from a {@link Reachable}
	 * 
	 * @param t
	 *            the reachable object
	 * @return the {@link IReachableManager}
	 * @throws IReachableHandlerException
	 *             if no {@link IReachableHandler} can handle t
	 */
	IReachableHandler getHandlerFromReachable(Reachable t)
			throws IReachableHandlerException;

	/**
	 * Returns a {@link IReachableHandler} from a {@link Reachable}
	 * 
	 * @param u
	 *            the url
	 * @return the {@link IReachableManager}
	 * @throws IReachableHandlerException
	 *             if no {@link IReachableHandler} can handle u
	 */
	IObjectHandler getHandlerFromObject(Object o)
			throws IReachableHandlerException;

}
