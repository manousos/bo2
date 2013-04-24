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
package gr.interamerican.bo2.utils.meta.formatters.nr;

import gr.interamerican.bo2.utils.meta.formatters.DecimalFormatter;

import java.text.DecimalFormat;

/**
 * NullFilteringFormatter for Numbers.
 * 
 * @param <T>
 *        Type of number.
 */
public class NrDecimalFormatter<T extends Number> extends NullReturningFormatter<T> {

	/**
	 * Creates a new NfDecimalFormatter object. 
	 *
	 * @param df
	 */
	public NrDecimalFormatter(DecimalFormat df) {
		super(new DecimalFormatter<T>(df));
	}
	
	/**
	 * Creates a new NrDecimalFormatter object. 
	 * @param decimalDigits 
	 */
	public NrDecimalFormatter(int decimalDigits) {
		super(new DecimalFormatter<T>(decimalDigits));
	}

	/**
	 * Creates a new NfDecimalFormatter object. 
	 *
	 * @param decimalDigits
	 * @param decimalSep
	 * @param groupSep
	 */
	public NrDecimalFormatter(int decimalDigits, Character decimalSep, Character groupSep) {
		super(new DecimalFormatter<T>(decimalDigits, decimalSep, groupSep));
	}

}
