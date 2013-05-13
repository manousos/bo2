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
package gr.interamerican.bo2.arch;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 */
public interface Money extends Serializable, Comparable<Money> {

	/**
	 * Currency code.
	 * 
	 * @return Returns the currency code.
	 */
	public abstract String getCurrencyCode();

	/**
	 * Sets the currency code.
	 * 
	 * @param currencyCode New currency code.
	 */
	public abstract void setCurrencyCode(String currencyCode);

	/**
	 * Gets the amount.
	 *
	 * @return Returns the amount.
	 */
	public abstract BigDecimal getAmount();

	/**
	 * Sets the amount.
	 *
	 * @param amount New amount.
	 */
	public abstract void setAmount(BigDecimal amount);

}
