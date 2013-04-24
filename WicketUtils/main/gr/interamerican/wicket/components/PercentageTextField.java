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
package gr.interamerican.wicket.components;

import gr.interamerican.wicket.behavior.ValidationStyleBehavior;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.DoubleConverter;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * ���������� ��� TextField �������� �� ������ ��������� ��� ����������� ��� �� decimals.
 * ���� RangeValidator ��� ���������� ��� ���� ��� �������� ��� ����� ����� [0, 100].
 * 
 * 
 */
public class PercentageTextField extends TextField<Double> {

	/**
	 * serialize.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * � ������� ��� ��������� ������.
	 * 
	 */
	private Integer decimals;

	/**
	 * Creates a new PercentageTextField object. r
	 *
	 * @param id
	 * @param decimals 
	 */
	public PercentageTextField(String id, Integer decimals) {
		super(id);
		this.decimals = decimals;
		add(new RangeValidator<Double>(0.0,100.0));
		add(ValidationStyleBehavior.INSTANCE);
		setOutputMarkupId(true);
		add(new NumberFormatBehaviour(decimals));
	}
	
	@Override
	public IConverter getConverter(final Class<?> type) {
		int counter = decimals;
		String sharp_str = "#,"; //$NON-NLS-1$
		String zero_str = "0."; //$NON-NLS-1$
		while(counter>0){
			sharp_str = sharp_str.concat("#"); //$NON-NLS-1$
			zero_str  = zero_str.concat("0"); //$NON-NLS-1$
			counter--;
		}
		String decimalFormat = sharp_str.concat(zero_str);
		NumberFormat nf = new DecimalFormat(decimalFormat);
		nf.setMaximumIntegerDigits(3);
		nf.setMaximumFractionDigits(decimals);
		nf.setMinimumFractionDigits(4);
		DoubleConverter dc = new DoubleConverter();
		dc.setNumberFormat(getLocale(), nf);
		return dc;
	}
}
