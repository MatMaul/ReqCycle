package org.eclipse.reqcycle.ui.datepropseditor.internal.components;

import java.util.Date;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.reqcycle.ui.eattrpropseditor.api.AbstractPropsEditorComponent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DatePropsEditorComponent extends AbstractPropsEditorComponent<Date> {

    private final CommonDatePropsEditor commonDatePropsEditor;

    public DatePropsEditorComponent(EAttribute attribute, Composite parent, int style) {
        super(attribute, parent, style);
        setLayout(new GridLayout(1, false));
        this.commonDatePropsEditor = new CommonDatePropsEditor(attribute, this, getStyle());
        this.commonDatePropsEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    }

    @Override
    public Date getValue() {
        return this.commonDatePropsEditor.getCalendarValue().getTime();
    }

    @Override
    public boolean isValid() {
        return true;
    }

}
