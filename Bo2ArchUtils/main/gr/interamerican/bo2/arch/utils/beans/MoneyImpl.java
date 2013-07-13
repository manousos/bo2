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
package gr.interamerican.bo2.arch.utils.beans;

import gr.interamerican.bo2.arch.Money;
import gr.interamerican.bo2.utils.StringUtils;
import gr.interamerican.bo2.utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * Money.
 * 
 */
public class MoneyImpl implements Money {
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Amount.
	 */
	private BigDecimal amount=BigDecimal.ZERO;


	/**
	 * Currency. For now euro. 
	 * 
	 * TODO: Default value based on default environment Locale.
	 */
	private Currency currency=Currency.getInstance("EUR");
	
	/**
	 * Creates a new MoneyImpl object. 
	 */
	public MoneyImpl() {
		super();
		/* empty */
	}
	
	/**
	 * Creates a new MoneyImpl object. 
	 *
	 * @param amount	
	 */
	public MoneyImpl(BigDecimal amount) {
		super();
		setAmount(amount);		
	}	
	
	/**
	 * Creates a new MoneyImpl object. 
	 *
	 * @param amount
	 * @param currency
	 */
	public MoneyImpl(BigDecimal amount, Currency currency) {
		super();		
		this.currency = currency;
		setAmount(amount);
	}
	
	/**
	 * Rounds to the count of decimal digits supported by the currency.
	 * 
	 * @param value Value to round.
	 * 
	 * TODO: Check the rounding mode.
	 * 
	 * @return Returns the rounded value.
	 */
	private BigDecimal round(BigDecimal value) {
		int decimalDigits = currency.getDefaultFractionDigits();
		return value.setScale(decimalDigits, RoundingMode.HALF_EVEN);
	}

	public String getCurrencyCode() {
		return currency.getCurrencyCode();
	}
	
	public void setCurrencyCode(String currencyCode) {
		if(!StringUtils.isNullOrBlank(currencyCode)) {
			currency = Currency.getInstance(currencyCode);
		} else {
			currency = Currency.getInstance(Locale.getDefault());
		}
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {	
		this.amount = round(Utils.notNull(amount, BigDecimal.ZERO));
	}
	
	@Override
	public String toString() {		
		return amount.toPlainString();
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (obj==this) {
			return true;
		}
		if (obj==null) {
			return false;
		}
		if (obj instanceof Money) {
			Money two = (Money) obj;
			return Utils.equals(this.getCurrencyCode(), two.getCurrencyCode()) 
			   &&  Utils.equals(this.getAmount(), two.getAmount());
		} else {
			return false;
		}		
	}
	
	@Override
	public int hashCode() {
		Object[] fields = {amount, currency};		
		return Utils.generateHashCode(fields);
	}

	public int compareTo(Money o) {
		if(o==null || o.getAmount()==null || o.getCurrencyCode()==null) {
			return 1;
		}
		if(!o.getCurrencyCode().equals(this.getCurrencyCode())) {
			return Utils.nullSafeCompare(this.getCurrencyCode(), o.getCurrencyCode());
		}
		return Utils.nullSafeCompare(this.getAmount(), o.getAmount());
	}
	
}
