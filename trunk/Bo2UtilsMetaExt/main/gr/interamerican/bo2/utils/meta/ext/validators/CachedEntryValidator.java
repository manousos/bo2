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
package gr.interamerican.bo2.utils.meta.ext.validators;

import gr.interamerican.bo2.arch.ext.Cache;
import gr.interamerican.bo2.arch.ext.TypedSelectable;
import gr.interamerican.bo2.utils.meta.exceptions.ValidationException;
import gr.interamerican.bo2.utils.meta.validators.AbstractValidator;
import gr.interamerican.bo2.utils.meta.validators.Validator;

/**
 * Validator for cached objects.
 * 
 * @param <T>
 *        Type of cached object. 
 * @param <C> 
 *        Type of cache code.
 */
public class CachedEntryValidator
<T extends TypedSelectable<C>, 
 C extends Comparable<? super C>> 
extends AbstractValidator implements Validator<T> {
	
	/**
	 * Cache.
	 */
	private Cache<C> cache;
		
	/**
	 * Creates a new CachedObjectValidator object. 
	 *
	 * @param cache
	 */
	public CachedEntryValidator(Cache<C> cache) {
		super();
		this.cache = cache;
	}
	
	public void validate(T value) throws ValidationException {
		if (value!=null &&  cache.get(value.getTypeId(), value.getCode())==null) {
			throw invalid();
		}
	}

}
