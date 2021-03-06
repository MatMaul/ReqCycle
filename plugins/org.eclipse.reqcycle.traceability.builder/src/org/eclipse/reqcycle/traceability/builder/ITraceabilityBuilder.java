package org.eclipse.reqcycle.traceability.builder;

import java.net.URI;
import java.util.List;

import org.eclipse.reqcycle.traceability.builder.exceptions.BuilderException;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITraceabilityBuilder {
	/**
	 * Builds the {@link Reachable} to save traceability information
	 * 
	 * @param r
	 *            the reachable to build
	 * @param callBack
	 *            the callback which handles the detection of traceability link
	 * @param forceBuild
	 *            if true the build will not try to identify {@link Reachable}
	 *            modification
	 * @throws BuilderException
	 *             if an error occurs during build
	 */
	void build(Reachable r, IBuilderCallBack callBack, boolean forceBuild)
			throws BuilderException;

	public interface IBuilderCallBack {

		/**
		 * @param reachable
		 * @return
		 */
		boolean needsBuild(Reachable reachable);

		/**
		 * Notify that a build is starting for the given {@link Reachable}
		 * 
		 * @param reachable
		 * @return true if the build shall continue false otherwise
		 */
		void startBuild(Reachable reachable);

		/**
		 * Notify that a build has finished
		 * 
		 * @param reachable
		 */
		void endBuild(Reachable reachable);

		/**
		 * Notify that an error occurs during the build
		 * 
		 * @param reachable
		 */
		void errorOccurs(Reachable reachable, Throwable t);

		/**
		 * Identify a new relationship. The direction between the source and the
		 * target is an downstream to upstream When several targets are filled
		 * it is important to notice that the traceability link will be
		 * consistent only with all the targets
		 * 
		 * @param resource
		 *            the {@link URI} of the resource containing the relation
		 * @param source
		 *            the {@link Object} source of the relation
		 * @param targets
		 *            the {@link Object} targets of the relation
		 * @param label
		 *            the label identifying the relation
		 */
		void newUpwardRelation(Object resource, Object source,
				List<? extends Object> targets, TType label);

	}

}
