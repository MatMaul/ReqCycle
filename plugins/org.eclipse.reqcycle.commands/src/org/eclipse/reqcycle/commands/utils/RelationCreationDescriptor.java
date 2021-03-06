package org.eclipse.reqcycle.commands.utils;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.uri.model.Reachable;

public class RelationCreationDescriptor
{

    private int type;

    private Relation relation;

    private Reachable source;

    private Reachable target;

    public final static int DOWNSTREAM_TO_UPSTREAM = -1;

    public final static int UPSTREAM_TO_DOWNSTREAM = 1;
    
    public final static int BOTH = 0;

    private AdapterFactoryLabelProvider labelProvider;

    public RelationCreationDescriptor(int type, Relation relation, Reachable source, Reachable target)
    {
        this.type = type;
        this.relation = relation;
        this.source = source;
        this.target = target;
        ComposedAdapterFactory factory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        labelProvider = new AdapterFactoryLabelProvider(factory);
    }

    public Relation getRelation()
    {
        return relation;
    }

    public Reachable getSource()
    {
        return source;
    }

    public Reachable getTarget()
    {
        return target;
    }

    public String getLabel()
    {
        StringBuilder labelBuilder = new StringBuilder(relation.getKind().toUpperCase());
        //if both, then up and down types are the same
        if (type == UPSTREAM_TO_DOWNSTREAM || type == BOTH)
        {
            labelBuilder.append(" - FROM " + labelProvider.getText(relation.getUpstreamType()));
            labelBuilder.append(" TO " + labelProvider.getText(relation.getDownstreamType()));
        }
        else
        {
            labelBuilder.append(" - FROM " + labelProvider.getText(relation.getDownstreamType()));
            labelBuilder.append(" TO " + labelProvider.getText(relation.getUpstreamType()));
        }
        return labelBuilder.toString();
    }

    public boolean isDownstreamToUpstream()
    {
        return type==DOWNSTREAM_TO_UPSTREAM || type == BOTH;
    }
    
    public boolean isUpstreamToDownstream()
    {
        return type==UPSTREAM_TO_DOWNSTREAM || type == BOTH;
    }

}
