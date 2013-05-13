/*******************************************************************************
 * Copyright (c) 2013 INTERAMERICAN PROPERTY AND CASUALTY INSURANCE COMPANY S.A. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * This library is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 ******************************************************************************/
package gr.interamerican.wicket.factories;

import gr.interamerican.wicket.behavior.ValidationStyleBehavior;
import gr.interamerican.wicket.components.BigDecimalTextField;
import gr.interamerican.wicket.components.CustomDateField;
import gr.interamerican.wicket.components.CustomDateTextField;
import gr.interamerican.wicket.components.DoubleTextField;
import gr.interamerican.wicket.components.PercentageTextField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * CmpFactory class - (Component Factory).
 * 
 */
public class ComponentFactory {

	/**
	 * ���������� ��� DropDownChoice component, 
	 * �������� to wicketId ��� Component,
	 * �� ����� ��� object ��� ������� �� ���������,
	 * ��� �� collection ��� ������������ �� ����� �� ���������.
	 * 
	 * @param <P>
	 * @param wicketId
	 * @param displayExpression 
	 * @param pos
	 * @return DropDownChoice
	 * 
	 */
	public static <P extends Serializable> DropDownChoice<P> addDropDownWithChoiceRenderer(String wicketId,String displayExpression,List<P> pos) {
		DropDownChoice<P> dd = new DropDownChoice<P>(wicketId, new Model<P>(), pos);
		ChoiceRenderer<P> cr = new ChoiceRenderer<P>(displayExpression);
		dd.setChoiceRenderer(cr);
		return dd;
	}
	
	/**
	 * ���������� CheckBox �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� CheckBox.
	 */
	public static void addCheckBoxes(MarkupContainer cmp, String[] wicketIds) {
		for (String booleanField : wicketIds) {
			cmp.add(new CheckBox(booleanField));
		}
	}
	

	/**
	 * ���������� Label �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Label.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Label.
	 */
	public static void addLabels(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			cmp.add(new Label(field)
			);
		}
	}
	
	/**
	 * ���������� TextField<String> �� ��� Model<String> �� �� wicketId 
	 * ��� ��� ������� ��� �� ��������� ���� markupContainer ����� �� �� 
	 * ����� bind ���� Object ��� model ��� MarkupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param fields
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * 
	 */
	public static void addTextFieldsWithoutBinding(MarkupContainer cmp, String[] fields) {
		for (String field : fields) {
			cmp.add(new TextField<String>(field,new Model<String>()));
		}
	}
	
	/**
	 * ���������� TextField<String> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 */
	public static void addTextFields(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			TextField<String> tf = new TextField<String>(field);
			tf.setConvertEmptyInputStringToNull(false);
			tf.setOutputMarkupPlaceholderTag(true);			
			cmp.add(tf);
		}
	}

	/**
	 * ���������� TextArea �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� TextArea.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� TextArea.
	 */
	public static void addTextAreaFields(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			TextArea<String> ta = new TextArea<String>(field);
			ta.setConvertEmptyInputStringToNull(false);
			cmp.add(ta);
		}
	}
	/**
	 * ���������� Disabled TextField<String> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 */
	@SuppressWarnings("rawtypes")
	public static void addDisableTextFields(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			cmp.add(new TextField(field).setEnabled(false));
		}
	}
	
	/**
  	 * ���������� DateField �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� DateFields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� DateField.
     */
    public static void addDateFields(MarkupContainer cmp, String[] wicketIds) {
    	for (String f : wicketIds) { 
    		cmp.add(new CustomDateField(f));
    	}
    }
    
   
    /**
     * ���������� DateField �� ��� Model<Date> �� �� wicketId 
	 * ��� ��� ������� ��� �� ��������� ���� markupContainer ����� �� �� 
	 * ����� bind ���� Object ��� model ��� MarkupContainer.
     * 
     * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� DateFields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� DateField.
     * 
     */
    public static void addDateFieldsWithoutBinding(MarkupContainer cmp, String[] wicketIds) {
    	for (String f : wicketIds) { 
    		cmp.add(new CustomDateField(f, new Model<Date>()));
    	}
    }
    
    
    /**
     * ���������� DateTextField �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� DateTextField.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� DateTextField.
     * 
     */
    public static void addDateTextFields(MarkupContainer cmp, String[] wicketIds) {
    	for (String field : wicketIds) {
    		cmp.add(new CustomDateTextField(field));
    	}
    }
    
    
    /**
     * ���������� DateTextField �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer �������� �� bind �� ��
	 * Object ��� ��� �������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� DateTextField.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� DateTextField.
     * @param modelObject 
     * 		  To object ��� ����� �� �� ������� bind.	
     * 		
     */
    public static void addDateTextFields(MarkupContainer cmp, String[] wicketIds, Object modelObject) {
    	for (String field : wicketIds) {
    		PropertyModel<Date> dateModel = 
				new PropertyModel<Date>(modelObject, field);
    		
    		CustomDateTextField customDateTextField = 
    			new CustomDateTextField(field,dateModel);
    		
    		cmp.add(customDateTextField);  
    	}
    }
    
    /**
	 * ���������� TextField<Double> �� ����� ����� �����������, �� �� wicketId 
	 * ��� ��� ������� ��� �� ��������� ���� markupContainer. ��� textField 
	 * ������� �� ������� ������ ��� ������������� ��������� ������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param decimals 
	 * 		  O �������� ������� ��� ������������� ��������� ������.
	 * 
     */
    public static void addRequiredDoubleTextField(MarkupContainer cmp, String[] wicketIds, Integer decimals){
    	addDoubleTextField(cmp, wicketIds, decimals);
    	for(String field : wicketIds){
    		@SuppressWarnings("unchecked")
			TextField<Double> tf =  (TextField<Double>) cmp.get(field);
    		tf.setRequired(true);
    	}
    }
    
	/**
	 * ���������� TextField<Double> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.��� textField 
	 * ������� �� ������� ������ ��� ������������� ��������� ������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param decimals 
	 * 		  O �������� ������� ��� ������������� ��������� ������.
	 */
	public static void addDoubleTextField(MarkupContainer cmp, String[] wicketIds, Integer decimals) {
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;
		}
		for (String field : wicketIds) {
			TextField<Double> tf = new DoubleTextField(field, dec);
			cmp.add(tf);
		}
	}
	
	/**
	 * ���������� TextField<Double> �� �� wicketId (field) ��� ��� ������� 
	 * ��� ����� bind ��� CompoundPropertyModel<T> model.
	 * ��� textField 
	 * ������� ������ �� ������� ������ ��� ������������� ��������� ������.
	 * @param <T> 
	 * @param field 
	 * 
	 * 		�� wicketId ��� TextField.
	 * @param model �� ������� ��� �� �������� �� field ��� ����� �� ������� �� binding.
	 * @param decimals � �������� ������� ��� ������������� ��������� ������
	 * @return TF
	 */
	
	public static <T extends Serializable>  TextField<Double> 
	addDoubleTextFieldWithBinding(String field, CompoundPropertyModel<T> model,Integer decimals) {
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;
		}
		IModel<Double> doubleModel = model.bind(field);
		return new DoubleTextField(field,doubleModel,dec);		
	}
	/**
	 * ���������� TextField<Double> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.��� textField 
	 * ������� �� ������� ������ ��� ������������� ��������� ������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param modelObject
	 * 		�� object ��� ����� bind to component. 
	 * @param decimals 
	 */
	public static void addDoubleTextFields(MarkupContainer cmp, String[] wicketIds, Object modelObject,Integer decimals){
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;	
		}
		for (String field : wicketIds) {		
			PropertyModel<Double> doubleModel = 
				new PropertyModel<Double>(modelObject, field);
			cmp.add(new DoubleTextField(field, doubleModel, dec));
		}
	}
	

	
	/**
	 * ���������� TextField<Double> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer ����� �� �� 
	 * ����� bind ���� Object ��� model.��� textField 
	 * ������� �� ������� ������ ��� ������������� ��������� ������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param decimals 
	 * 
	 */
	public static void addDoubleTextFieldWithoutBinding(MarkupContainer cmp, String[] wicketIds, Integer decimals) {
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;
		}
		for (String field : wicketIds) {
			cmp.add(new DoubleTextField(field, new Model<Double>(), dec));
		}
	}
	
	/**
	 * ���������� TextField<BigDecimal> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer �������� �� bind �� ��
	 * Object ��� ��� �������. 
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param modelObject 
	 */
	public static void addBigDecimalTextFields(MarkupContainer cmp, String[] wicketIds, Object modelObject){
		for(String field : wicketIds){
			cmp.add(new TextField<BigDecimal>(field, new PropertyModel<BigDecimal>(modelObject, field),BigDecimal.class)); 
		}
	}

	/**
	 * ���������� TextField<BigDecimal> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.��� textField 
	 * ������� �� ������� ������ ��� ������������� ��������� ������.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param decimals 
	 * 		  O �������� ������� ��� ������������� ��������� ������.
	 */
	public static void addBigDecimalTextFields(MarkupContainer cmp, String[] wicketIds, Integer decimals){
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;
		}
		for (String field : wicketIds) {
			TextField<BigDecimal> tf = new BigDecimalTextField(field, dec);
			cmp.add(tf);
		}
	}

	/**
	 * ���������� TextField<BigDecimal> �� ��� Model<BigDecimal> �� �� wicketId 
	 * ��� ��� ������� ��� �� ��������� ���� markupContainer ����� �� �� 
	 * ����� bind ���� Object ��� model ��� MarkupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param fields
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 *
	 */
	public static void addBigDecimalTextFieldsWithoutBinding(MarkupContainer cmp, String[] fields) {
		for (String field : fields) {
			cmp.add(new TextField<BigDecimal>(field, new Model<BigDecimal>(),BigDecimal.class));
		}
	}

	/**
	 * ���������� TextField<BigDecimal> �� �� wicketId (field) ��� ��� ������� 
	 * ��� ����� bind ��� CompoundPropertyModel<T> model.
	 * ��� textField 
	 * ������� ������ �� ������� ������ ��� ������������� ��������� ������.
	 * @param <T> 
	 * @param field 
	 * 
	 * 		�� wicketId ��� TextField.
	 * @param model �� ������� ��� �� �������� �� field ��� ����� �� ������� �� binding.
	 * @param decimals � �������� ������� ��� ������������� ��������� ������
	 * @return TF
	 */
	
	public static <T extends Serializable>  TextField<BigDecimal> 
	addBigDecimalTextFieldWithBinding(String field, CompoundPropertyModel<T> model,Integer decimals) {
		final Integer dec;
		if(decimals == null){
			dec=new Integer(0);
		}else{
			dec=decimals;
		}
		IModel<BigDecimal> bigDecimalModel = model.bind(field);
		return new BigDecimalTextField(field, bigDecimalModel , dec);		
	}

	/**
	 * ���������� TextField<Long> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 */
	public static void addLongTextField(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			TextField<Long> tf = new TextField<Long>(field);
			tf.add(ValidationStyleBehavior.INSTANCE);
			tf.setOutputMarkupId(true);
			cmp.add(tf);
		}
	}

	/**
	 * ���������� TextField<Integer> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 */
	public static void addIntegerTextField(MarkupContainer cmp, String[] wicketIds) {
		for (String field : wicketIds) {
			TextField<Integer> tf = new TextField<Integer>(field);
			tf.add(ValidationStyleBehavior.INSTANCE);
			tf.setOutputMarkupId(true);
			cmp.add(tf);
		}
	}
	

	/**
	 * ���������� Percentage TextField �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param decimals � ������� ��� ���������
	 */
	public static void addPercentageTextField(MarkupContainer cmp, String[] wicketIds, int decimals) {
		for (String field : wicketIds) {
			 PercentageTextField tf = new PercentageTextField(field, decimals);
			 cmp.add(tf);
		}
	}
	
	/**
	 * ���������� ��� Label �� �� message ��� �� wicketId ��� ��� �������.
	 * 
	 * @param id 
	 * 		  �� wicketId ��� Label.
	 * @param message 
	 * 		  To ������ ��� �� ��������� �� Label.
	 * 
	 * @return Label
	 * 
	 */
	public static Label returnViewLabels(String id, String message) {
		Label label = new Label(id, new Model<String>(message));
		return label;
	}
	
	/**
	 * ���������� ��� Label �� �� �� ��� ���� ��� Enum ��� �� 
	 * wicketId ��� ��� �������.
	 * 
	 * @param id 
	 * 		  �� wicketId ��� Label.
	 * @param enumeration 
	 * 		  � ���� ��� �� ��������� �� Label.
	 * 
	 * @return Label
	 * 
	 */
	public static Label returnViewLabels(String id, Enum<?> enumeration) {
		Label label = new Label(id, new Model<Enum<?>>(enumeration));
		return label;
	}
	
	/**
	 * ���������� AjaxCheckBox �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer.
	 * 
	 * @param markupContainer
	 * 		O markupContainer,���� ����� �� ���������� �� AjaxCheckBox.
	 * @param checkBoxes
	 * 		�� wicketIds �� �� ����� �� ������������� �� AjaxCheckBox.
	 * 
	 */
	public static void addAjaxCheckBox(MarkupContainer markupContainer, String[] checkBoxes){
		for(String s : checkBoxes){
		AjaxCheckBox checkBox = new AjaxCheckBox(s){
			/**
			 * serialize.
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target){
				//TODO empty on update
			}
		};
		markupContainer.add(checkBox);
		}
	}
	
	/**
	 * 
	 * 
	 * ���������� TextField<Integer> �� �� wicketId ��� ��� ������� 
	 * ��� �� ��������� ���� markupContainer �������� �� bind �� ��
	 * Object ��� ��� �������. .
	 * 
	 * @param cmp
	 * 		O markupContainer,���� ����� �� ���������� �� Textfields.
	 * @param wicketIds
	 * 		�� wicketIds �� �� ����� �� ������������� �� Textfields.
	 * @param modelObject 
	 * 		  The Object that contains the property.
	 */
	public static void addIntegerTextField(MarkupContainer cmp, String[] wicketIds, Object modelObject) {
		for (String field : wicketIds) {
			TextField<Integer> tf = new TextField<Integer>(field,new PropertyModel<Integer>(modelObject,field));
			tf.add(ValidationStyleBehavior.INSTANCE);
			tf.setOutputMarkupId(true);
			cmp.add(tf);
		}
	}
}
