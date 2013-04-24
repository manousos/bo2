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
package gr.interamerican.bo2.utils.meta.ext.descriptors;

import gr.interamerican.bo2.arch.ext.Cache;
import gr.interamerican.bo2.utils.meta.descriptors.AbstractBoPropertyDescriptor;
import gr.interamerican.bo2.utils.meta.formatters.Formatter;
import gr.interamerican.bo2.utils.meta.parsers.Parser;
import gr.interamerican.bo2.utils.meta.validators.Validator;

/**
 * BoPropertyDescriptor for objects that have something to do
 * with the {@link Cache}. This includes cache entries, cache
 * entry owners, lists of cache entries.
 * 
 * @param <T>
 *        Type of object described by this descriptor. 
 * @param <C> 
 *        Type of Cache code.
 */
public abstract class AbstractCacheRelatedObjectBoPropertyDescriptor<T, C extends Comparable<? super C>>
extends AbstractBoPropertyDescriptor<T>{
	
	/**
	 * type id.
	 */	
	protected Long typeId;
	
	/**
	 * sub type id;
	 */	
	protected Long subTypeId;
	
	/**
	 * Cache.
	 */
	protected Cache<C> cache;
	
	/**
	 * Parser for cache code.
	 */
	protected Parser<C> codeParser;
	
	/**
	 * Formatter for cache code.
	 */
	protected Formatter<C> codeFormatter;
	
	/**
	 * Creates a new TypedSelectableBoPropertyDescriptor object. 
	 *
	 * @param typeId 
	 *        TypeId of the list related to the described object.
	 * @param subTypeId 
	 *        SubTypeId of the list related to the described object.
	 * @param cache
	 *        Cache with the objects.
	 * @param codeParser 
	 *        Parser for the Cache code.
	 * @param codeFormatter
	 *        Formatter for the Cache code. 
	 */
	public AbstractCacheRelatedObjectBoPropertyDescriptor(
	Long typeId, Long subTypeId, Cache<C> cache, Parser<C> codeParser, Formatter<C> codeFormatter) {
		super(null);
		this.typeId = typeId;
		this.subTypeId = subTypeId;
		this.cache = cache;
		this.codeParser = codeParser;
		this.codeFormatter = codeFormatter;
		validators.put(AbstractCacheRelatedObjectBoPropertyDescriptor.class, getValidator());
	}
	
	/**
	 * @return Returns the appropriate validator.
	 */
	protected abstract Validator<T> getValidator();

}
