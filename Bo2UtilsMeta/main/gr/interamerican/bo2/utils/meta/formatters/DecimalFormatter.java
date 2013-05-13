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
package gr.interamerican.bo2.utils.meta.formatters;

import gr.interamerican.bo2.utils.StringConstants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Formatter for numbers objects.
 * 
 * @param <T>
 *        Type of Number.
 */
public class DecimalFormatter<T extends Number> 
implements Formatter<T> {
	
	/**
	 * Decimal format.
	 */
	DecimalFormat df;
	
	/**
	 * Creates a new DateFormatter object. 
	 *
	 * @param df
	 */
	public DecimalFormatter(DecimalFormat df) {
		super();
		this.df = df;
	}
	
	/**
	 * Creates a new DecimalFormatter object.
	 * @param decimalDigits 
	 */
	public DecimalFormatter(int decimalDigits) {
		this.df = new DecimalFormat();
		if (decimalDigits==0) {
			df.setGroupingUsed(false);
		}
		df.setMaximumFractionDigits(decimalDigits);
		df.setMinimumFractionDigits(decimalDigits);
	}
	
	/**
	 * Creates a new DecimalFormatter object. 
	 * 
	 * TODO: remove this and have the system locale handle the DecimalFormatSymbols?
	 *
	 * @param decimalDigits
	 * @param decimalSep
	 * @param groupSep
	 */
	public DecimalFormatter(int decimalDigits, Character decimalSep, Character groupSep) {
		super();
		this.df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(decimalSep);
		if (groupSep==null) {
			df.setGroupingUsed(false);
		} else {
			dfs.setGroupingSeparator(groupSep);
			df.setGroupingUsed(true);
		}
		if (decimalDigits==0) {
			df.setGroupingUsed(false);
		}
		df.setMaximumFractionDigits(decimalDigits);
		df.setMinimumFractionDigits(decimalDigits);
		df.setDecimalFormatSymbols(dfs);
	}

	public String format(T t) {
		if(t==null) {
			return StringConstants.NULL;
		}
		return df.format(t);
	}
}
