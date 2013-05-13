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
package gr.interamerican.bo2.test.def.samples;

import gr.interamerican.bo2.test.def.posamples.Invoice;

import java.io.Serializable;

/**
 * Bean used as a Component in mapping of {@link Invoice}
 */
public interface InvoiceInfo extends Serializable{
	
	/**
	 * Sets the barCode.
	 * @param barCode
	 */
	void setBarCode(String barCode);
	
	/**
	 * Gets the barcode.
	 * @return barcode.
	 */
	String getBarCode();
}
