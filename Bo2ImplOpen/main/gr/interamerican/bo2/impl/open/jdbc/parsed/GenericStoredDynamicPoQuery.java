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
package gr.interamerican.bo2.impl.open.jdbc.parsed;

import gr.interamerican.bo2.arch.EntitiesQuery;
import gr.interamerican.bo2.arch.PersistentObject;
import gr.interamerican.bo2.arch.Provider;
import gr.interamerican.bo2.arch.exceptions.DataAccessException;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.ext.CriteriaDependent;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.impl.open.jdbc.AbstractJdbcWorker;
import gr.interamerican.bo2.utils.JavaBeanUtils;

/**
 * This class is a {@link GenericStoredDynamicEntitiesQuery} decorator that makes
 * possible the conversion of its results to {@link PersistentObject}s. 
 * <p/>
 * A {@link GenericStoredDynamicEntitiesQuery} may fetch a bean that contains
 * some of the properties of a {@link PersistentObject}. In this case, 
 * it is possible to wrap this query in a StoredDynamicPoQuery, so that 
 * getEntity() returns, in fact, an instance of the {@link PersistentObject}.
 * Note, that if the query does not fetch all the key properties of the
 * po, the applicability of the result is limited.
 * <p/>
 * In order for this to happen, the SQL supplied to the
 * {@link GenericStoredDynamicEntitiesQuery} must produce a results bean with
 * properties that are named exactly the same, as the properties of the
 * {@link PersistentObject}.
 * <p/>
 * Reminder: The convention for the property names of the synthetic results
 * bean of the {@link StoredDynamicEntitiesQuery} is as follows: The result 
 * set column names are converted to property names by converting all letters
 * to small case and capitalizing each letter that is preceded by an underscore.
 * For example, FIRST_NAME column corresponds to firstName property.
 * 
 * @param <P> 
 *        type of PersistentObject
 * @param <C>
 *        type of criteria object.
 */
public class GenericStoredDynamicPoQuery<P extends PersistentObject<?>, C> 
extends AbstractJdbcWorker 
implements EntitiesQuery<P>, CriteriaDependent<C> {
	
	/**
	 * Wrapped stored dynamic entities query.
	 */
	protected GenericStoredDynamicEntitiesQuery<C> query;
	
	/**
	 * Avoid lock.
	 */
	private boolean avoidLock = false;
	
	/**
	 * Criteria.
	 */
	private C criteria;
	
	/**
	 * PersistentObject class.
	 */
	private Class<P> clazz;

	/**
	 * Creates a new StoredDynamicPoQuery object. 
	 * 
	 * @param query 
	 * @param clazz 
	 */
	public GenericStoredDynamicPoQuery(GenericStoredDynamicEntitiesQuery<C> query, Class<P> clazz) {
		this.query = query;
		this.clazz = clazz;
		query.setAvoidLock(avoidLock);
	}
	
	public void execute() throws DataException {
		query.execute();
	}

	public boolean next() throws DataAccessException {
		return query.next();
	}

	public int getRow() throws DataAccessException {
		return query.getRow();
	}

	public void setAvoidLock(boolean avoidLock) {
		this.avoidLock = avoidLock;
		query.setAvoidLock(avoidLock);
	}

	public boolean isAvoidLock() {
		return false;
	}

	public void setCriteria(C criteria) {
		query.setCriteria(criteria);
		this.criteria = criteria;
	}

	public C getCriteria() {
		return criteria;
	}

	public P getEntity() throws DataAccessException {
		P po = Factory.create(clazz);
		JavaBeanUtils.copyProperties(query.getEntity(), po);
		return po;
	}
	
	@Override
	public void setManagerName(String managerName) {	
		super.setManagerName(managerName);
		query.setManagerName(managerName);
	}
	
	@Override
	public void init(Provider parent) throws InitializationException {
		super.init(parent);
		query.init(parent);
	}
	
	@Override
	public void open() throws DataException {
		super.open();
		query.open();
	}
	
	@Override
	public void close() throws DataException {
		query.close();
		super.close();
	}

}
