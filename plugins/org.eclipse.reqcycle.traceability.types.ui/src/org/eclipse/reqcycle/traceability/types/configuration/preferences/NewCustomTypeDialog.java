package org.eclipse.reqcycle.traceability.types.configuration.preferences;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.reqcycle.traceability.types.configuration.preferences.validators.RegexPredicate;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.CustomType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Entry;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Type;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.TypeconfigurationFactory;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.reqcycle.types.IType.FieldDescriptor;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class NewCustomTypeDialog extends TitleAreaDialog {

	private static final String AN_ENTRY = "entry";
	private static final String A_PREDICATE = "predicate";
	private CustomType newCustomType;
	private Text textName;
	@Inject
	ITypesManager manager;
	private IType injectedJavaType;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @param injected
	 */
	public NewCustomTypeDialog(Shell parentShell, Type injected) {
		super(parentShell);
		newCustomType = TypeconfigurationFactory.eINSTANCE.createCustomType();
		newCustomType.setSuperType(injected);
	}

	@PostConstruct
	public void init() {
		IType javaType = newCustomType.getSuperType().getIType();
		injectedJavaType = javaType;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Configure a type");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblName = new Label(container, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblName.setText("Name :");

		textName = new Text(container, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		textName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (textName.getText().length() > 0) {
					newCustomType.setTypeId(textName.getText());
				}
			}
		});

		Group grpParameters = new Group(container, SWT.NONE);
		grpParameters.setLayout(new GridLayout(1, false));
		grpParameters.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1));
		grpParameters.setText("Parameters");

		ScrolledComposite scrolledComposite = new ScrolledComposite(
				grpParameters, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		createEntries(composite);

		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));

		return area;
	}

	private void createEntries(Composite composite) {
		for (FieldDescriptor d : injectedJavaType.getDescriptors()) {
			createEntry(composite, d);
		}

	}

	private void createEntry(Composite composite, FieldDescriptor d) {
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText(getName(d) + " :");
		Text text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		final Predicate<String> predicate = getPredicate(d.type);
		Entry entry = TypeconfigurationFactory.eINSTANCE.createEntry();
		entry.setName(d.name);
		newCustomType.getEntries().add(entry);
		text_1.setData(AN_ENTRY, entry);
		text_1.setData(A_PREDICATE, predicate);
		text_1.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Widget w = e.widget;
				if (w instanceof Text) {
					Text text = (Text) w;
					text.setBackground(Display.getDefault().getSystemColor(
							SWT.COLOR_WHITE));
					Predicate<String> p = (Predicate<String>) text
							.getData(A_PREDICATE);
					Entry currentEntry = (Entry) text.getData(AN_ENTRY);
					String value = text.getText();
					if (value.length() != 0) {
						if (!p.apply(value)) {
							text.setBackground(Display.getDefault()
									.getSystemColor(SWT.COLOR_RED));
						} else {
							currentEntry.setValue(value);
						}
					}
				}
			}
		});
	}

	private Predicate<String> getPredicate(Class<?> type) {
		if (String.class.isAssignableFrom(type)) {
			return Predicates.alwaysTrue();
		} else if ((Boolean.class.isAssignableFrom(type))
				|| (boolean.class.isAssignableFrom(type))) {
			return new RegexPredicate("true|false");
		} else if ((Integer.class.isAssignableFrom(type))
				|| int.class.isAssignableFrom(type)) {
			return new RegexPredicate("\\d*");
		} else {
			return Predicates.alwaysFalse();
		}
	}

	protected String getName(FieldDescriptor d) {
		StringBuilder builder = new StringBuilder();
		char[] inChar = d.name.toCharArray();
		String currentWord = "";
		for (char c : inChar) {
			if (Character.isUpperCase(c)) {
				builder.append(currentWord).append(" ");
				currentWord = "";
				currentWord += Character.toLowerCase(c);
			} else {
				currentWord += c;
			}
		}
		builder.append(currentWord);
		return builder.toString();
	}

	@Override
	protected void buttonPressed(int buttonId) {
		super.buttonPressed(buttonId);
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public CustomType getCustomType() {
		return newCustomType;
	}

	@Override
	protected void okPressed() {
		if (newCustomType.getTypeId() == null
				|| newCustomType.getTypeId().length() == 0) {
			setErrorMessage("Please set a name");
			return;
		}
		super.okPressed();
	}

}
