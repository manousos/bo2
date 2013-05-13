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
package gr.interamerican.bo2.impl.open.workers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gr.interamerican.bo2.arch.PersistenceWorker;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.impl.open.runtime.AbstractBo2RuntimeCmd;
import gr.interamerican.bo2.test.def.posamples.CompanyUser;
import gr.interamerican.bo2.test.def.posamples.CompanyUserKey;
import gr.interamerican.bo2.test.def.samples.enums.Sex;

import org.junit.Test;

/**
 * 
 */
public class TestPwWrapperQuery {

	
	/**
	 * query to test
	 */
	PwWrapperQuery<CompanyUser, CompanyUserKey> query = new PwWrapperQuery<CompanyUser, CompanyUserKey>(CompanyUser.class);
	
	
	/**
	 * Tests execute
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testPwWrapperQuery() throws UnexpectedException, DataException, LogicException {
		/*
		 * setup data for this test.
		 */
		new AbstractBo2RuntimeCmd() { 			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {
				CompanyUser po = Factory.create(CompanyUser.class);
				PersistenceWorker<CompanyUser> pw = openPw(CompanyUser.class);
				po.setId(10L);
				po.setSex(Sex.MALE);
				pw.store(po);
			}
		}.execute();
		
		new AbstractBo2RuntimeCmd() {			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {						
				query.init(getProvider());
				query.open();
				CompanyUserKey key = Factory.create(CompanyUserKey.class);
				key.setId(10L);
				query.setCriteria(key);
				query.getRow();
				query.execute();
				assertEquals(Sex.MALE,query.getEntity().getSex());
			}
		}.execute();
	}
	
	
	

	/**
	 * Tests PwWrapperQuery when Po doesn't exist
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testPwWrapperQuery_PoNotFounException() throws UnexpectedException, DataException, LogicException {
		new AbstractBo2RuntimeCmd() {			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {						
				query.init(getProvider());
				query.open();
				CompanyUserKey key = Factory.create(CompanyUserKey.class);
				key.setId(1L);
				query.setCriteria(key);
				query.execute();
				query.next();
			}
		}.execute();
	}
	
	
	/**
	 * Tests PwWrapperQuery without giving criteria 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test(expected = DataException.class)
	public void testPwWrapperQuery_dataException() throws UnexpectedException, DataException, LogicException {
		new AbstractBo2RuntimeCmd() {			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {						
				query.init(getProvider());
				query.open();
				query.execute();
			}
		}.execute();
	}
	
	
	/**
	 * Tests PwWrapperQuery without giving criteria 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test(expected = DataException.class)
	public void testGetEntity() throws UnexpectedException, DataException, LogicException {
		new AbstractBo2RuntimeCmd() {			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {						
				query.init(getProvider());
				query.open();
				query.getEntity();
			}
		}.execute();
	}
	
	/**
	 * Tests getCriteria
	 */
	@Test
	public void testGetCriteria(){
		CompanyUserKey key = Factory.create(CompanyUserKey.class);
		key.setId(2L);
		query.setCriteria(key);
		assertEquals(key,query.getCriteria());
	}
	
	
	/**
	 *Tests isAvoidLock 
	 */
	@Test
	public void testIsAvoidLock(){
		assertTrue(query.isAvoidLock());
	}
	
	
	/**
	 *Tests isAvoidLock 
	 */
	@Test
	public void testSetAvoidLock(){
		query.setAvoidLock(false);
		assertTrue(query.isAvoidLock());
	}
	
}
