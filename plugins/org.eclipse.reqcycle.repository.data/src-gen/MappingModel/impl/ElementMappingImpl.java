/**
 */
package MappingModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import MappingModel.AttributeMapping;
import MappingModel.ElementMapping;
import MappingModel.MappingModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link MappingModel.impl.ElementMappingImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link MappingModel.impl.ElementMappingImpl#getTargetElement <em>Target Element</em>}</li>
 *   <li>{@link MappingModel.impl.ElementMappingImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link MappingModel.impl.ElementMappingImpl#getSourceQualifier <em>Source Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementMappingImpl extends MinimalEObjectImpl.Container implements ElementMapping {
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTargetElement() <em>Target Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetElement()
	 * @generated
	 * @ordered
	 */
	protected EClass targetElement;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeMapping> attributes;

	/**
	 * The default value of the '{@link #getSourceQualifier() <em>Source Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceQualifier()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_QUALIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceQualifier() <em>Source Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceQualifier()
	 * @generated
	 * @ordered
	 */
	protected String sourceQualifier = SOURCE_QUALIFIER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingModelPackage.Literals.ELEMENT_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingModelPackage.ELEMENT_MAPPING__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTargetElement() {
		if (targetElement != null && targetElement.eIsProxy()) {
			InternalEObject oldTargetElement = (InternalEObject)targetElement;
			targetElement = (EClass)eResolveProxy(oldTargetElement);
			if (targetElement != oldTargetElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT, oldTargetElement, targetElement));
			}
		}
		return targetElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetTargetElement() {
		return targetElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetElement(EClass newTargetElement) {
		EClass oldTargetElement = targetElement;
		targetElement = newTargetElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT, oldTargetElement, targetElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttributeMapping> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<AttributeMapping>(AttributeMapping.class, this, MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceQualifier() {
		return sourceQualifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceQualifier(String newSourceQualifier) {
		String oldSourceQualifier = sourceQualifier;
		sourceQualifier = newSourceQualifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingModelPackage.ELEMENT_MAPPING__SOURCE_QUALIFIER, oldSourceQualifier, sourceQualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingModelPackage.ELEMENT_MAPPING__DESCRIPTION:
				return getDescription();
			case MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT:
				if (resolve) return getTargetElement();
				return basicGetTargetElement();
			case MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES:
				return getAttributes();
			case MappingModelPackage.ELEMENT_MAPPING__SOURCE_QUALIFIER:
				return getSourceQualifier();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MappingModelPackage.ELEMENT_MAPPING__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT:
				setTargetElement((EClass)newValue);
				return;
			case MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends AttributeMapping>)newValue);
				return;
			case MappingModelPackage.ELEMENT_MAPPING__SOURCE_QUALIFIER:
				setSourceQualifier((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MappingModelPackage.ELEMENT_MAPPING__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT:
				setTargetElement((EClass)null);
				return;
			case MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES:
				getAttributes().clear();
				return;
			case MappingModelPackage.ELEMENT_MAPPING__SOURCE_QUALIFIER:
				setSourceQualifier(SOURCE_QUALIFIER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MappingModelPackage.ELEMENT_MAPPING__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MappingModelPackage.ELEMENT_MAPPING__TARGET_ELEMENT:
				return targetElement != null;
			case MappingModelPackage.ELEMENT_MAPPING__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case MappingModelPackage.ELEMENT_MAPPING__SOURCE_QUALIFIER:
				return SOURCE_QUALIFIER_EDEFAULT == null ? sourceQualifier != null : !SOURCE_QUALIFIER_EDEFAULT.equals(sourceQualifier);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: ");
		result.append(description);
		result.append(", sourceQualifier: ");
		result.append(sourceQualifier);
		result.append(')');
		return result.toString();
	}

} //ElementMappingImpl
