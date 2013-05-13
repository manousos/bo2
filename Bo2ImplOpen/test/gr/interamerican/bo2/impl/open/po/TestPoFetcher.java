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
package gr.interamerican.bo2.impl.open.po;

import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.impl.open.annotations.Bo2AnnoUtils;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.impl.open.po.PoFetcher.MethodCall;
import gr.interamerican.bo2.impl.open.runtime.AbstractBo2RuntimeCmd;
import gr.interamerican.bo2.impl.open.runtime.CrudCmd;
import gr.interamerican.bo2.impl.open.transformations.DeepCopy;
import gr.interamerican.bo2.samples.archutil.po.User;
import gr.interamerican.bo2.samples.archutil.po.UserKey;
import gr.interamerican.bo2.test.def.posamples.Invoice;
import gr.interamerican.bo2.test.def.posamples.InvoiceKey;
import gr.interamerican.bo2.test.def.posamples.InvoiceLine;
import gr.interamerican.bo2.test.def.posamples.InvoiceLineKey;
import gr.interamerican.bo2.test.def.posamples.SamplesFactory;
import gr.interamerican.bo2.test.utils.UtilityForBo2Test;
import gr.interamerican.bo2.utils.ReflectionUtils;
import gr.interamerican.bo2.utils.adapters.Modification;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for cache.
 */
public class TestPoFetcher {
	
	/**
	 * Test for get.
	 * 
	 * Tests that an object can be fetched.
	 * Tests that when a cache has been defined for a class, then
	 * subsequent fetches of an element with the same id 
	 * 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testGet_withoutCache() throws UnexpectedException, DataException, LogicException {					
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException, InitializationException, UnexpectedException {
				InvoiceKey ik = Factory.create(InvoiceKey.class);
				ik.setInvoiceNo("no no no"); //$NON-NLS-1$
				Invoice invoice = PoFetcher.get(Invoice.class, ik);
				Assert.assertNull(invoice);
			}
		}.execute();
		
	}
	
	
	/**
	 * Test for get.
	 * 
	 * Tests that an object can be fetched.
	 * Tests that when a cache has been defined for a class, then
	 * subsequent fetches of an element with the same id 
	 * 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testSet() {
		Bo2AnnoUtils.setManagerName(User.class, "LOCALDB"); //$NON-NLS-1$
		User expected = new User();		
		expected.setId(50);
		PoFetcher.set(User.class, expected);
		UserKey key = Factory.create(UserKey.class);
		key.setId(50);
		User actual = PoFetcher.get(User.class, key);
		Assert.assertEquals(expected, actual);	
	}
	
	
	
	
	
	
	/**
	 * Test for get.
	 * 
	 * Tests that an object can be fetched.
	 * Tests that when a cache has been defined for a class, then
	 * subsequent fetches of an element with the same id 
	 * 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testGet() throws UnexpectedException, DataException, LogicException {
		Bo2AnnoUtils.setManagerName(User.class, "LOCALDB"); //$NON-NLS-1$
		
		final User[] users = new User[3];
		
		final UserKey exists = Factory.create(UserKey.class);
		exists.setId(UtilityForBo2Test.getExistingUserId());
		final UserKey notExists = Factory.create(UserKey.class);
		notExists.setId(UtilityForBo2Test.getNotExistingUserId());
		
		
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException, InitializationException, UnexpectedException {				
				users[0] = PoFetcher.get(User.class, exists);
			}
		}.execute();
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException, InitializationException, UnexpectedException {				
				users[1] = PoFetcher.get(User.class, exists);
				users[2] = PoFetcher.get(User.class, notExists);
			}
		}.execute();
		Assert.assertNotNull(users[0]);
		Assert.assertNotSame(users[0], users[1]);
		Assert.assertNull(users[2]);		
	}
	
	/**
	 * Test for get throwing an exception.
	 */
	@Test(expected=RuntimeException.class)
	public void testGet_withoutProvider() {		
		UserKey k = new UserKey(5);
		PoFetcher.get(User.class,k);
	}
	
	/**
	 * Test for get throwing an exception.
	 */
	@SuppressWarnings("nls")
	@Test()
	public void testSetGetChildMethod() {		
		PoFetcher.setGetChildMethod(InvoiceLine.class, Invoice.class, "getLineByNo", "lineNo");
		
		MethodCall mc = PoFetcher.fetchMethods.get(InvoiceLine.class);
		Assert.assertNotNull(mc);
		Assert.assertEquals(Invoice.class, mc.poClass);
		Assert.assertEquals(InvoiceKey.class, mc.poKeyClass);
		Assert.assertEquals("getLineByNo", mc.method.getName());
		String[] argNames = {"lineNo"};
		Assert.assertArrayEquals(argNames, mc.argNames);
	}
	
	/**
	 * Test for get throwing an exception.
	 */
	@Test()
	public void testSetCache() {		
		PoFetcher.setCacheSize(Invoice.class, 5);
		@SuppressWarnings("unchecked")
		PoCache<InvoiceKey, Invoice> pocache =
			(PoCache<InvoiceKey, Invoice>) PoFetcher.caches.get(Invoice.class);
		Assert.assertNotNull(pocache);
		Assert.assertEquals(5, pocache.maxSize);
	}
	
	/**
	 * Test for get.
	 * 
	 * Tests that an object can be fetched.
	 * Tests that when a cache has been defined for a class, then
	 * subsequent fetches of an element with the same id 
	 * 
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@SuppressWarnings("nls")
	@Test
	public void testGet_withMethod() throws UnexpectedException, DataException, LogicException {
		final String invoiceNo = "POFETCH";
		
		PoFetcher.setCacheSize(Invoice.class, 5);		
		PoFetcher.setGetChildMethod(InvoiceLine.class, Invoice.class, "getLineByNo", "lineNo");
		
		CrudCmd<Invoice> crud = new CrudCmd<Invoice>(Factory.createPw(Invoice.class),true);
		Invoice invoice = SamplesFactory.getBo2Factory().sampleInvoiceFull(5);
		invoice.setInvoiceNo(invoiceNo);		
		crud.delete(invoice);		
		crud.store(invoice);
		
		final InvoiceLine[] lines = new InvoiceLine[4];
		
		
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException, InitializationException, UnexpectedException {	
				InvoiceLineKey key = Factory.create(InvoiceLineKey.class);
				key.setInvoiceNo(invoiceNo);
				key.setLineNo(3);
				lines[0] = PoFetcher.get(InvoiceLine.class, key); //Fetched using method	
				Assert.assertNotNull(lines[0]);
			}
		}.execute();
		
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException, InitializationException, UnexpectedException {	
				InvoiceKey key = Factory.create(InvoiceKey.class);
				key.setInvoiceNo(invoiceNo);
				Invoice inv = PoFetcher.get(Invoice.class, key);
				Assert.assertNotNull(inv);
				InvoiceLine line3 = inv.getLineByNo(3);
				Assert.assertSame(lines[0], line3);
			}
		}.execute();
		
		crud.delete(invoice);	
	}
	
	/**
	 * Test for registerModification.
	 */
	@Test()
	public void testRegisterModification_beforeCacheCreation() {		
		Modification<Invoice> modification = new DeepCopy<Invoice>();
		PoFetcher.registerModification(Invoice.class, modification);
		PoFetcher.setCacheSize(Invoice.class, 5);
		@SuppressWarnings("unchecked")
		PoCache<InvoiceKey, Invoice> pocache =
			(PoCache<InvoiceKey, Invoice>) PoFetcher.caches.get(Invoice.class);				
		Assert.assertNotNull(pocache);
		Assert.assertEquals(modification, pocache.modification);		
	}
	
	/**
	 * Test for registerModification.
	 */
	@Test()
	public void testRegisterModification_afterCacheCreation() {		
		Modification<Invoice> modification = new DeepCopy<Invoice>();
		cleanUp();
		PoFetcher.setCacheSize(Invoice.class, 5);
		PoFetcher.registerModification(Invoice.class, modification);
		@SuppressWarnings("unchecked")
		PoCache<InvoiceKey, Invoice> pocache =
			(PoCache<InvoiceKey, Invoice>) PoFetcher.caches.get(Invoice.class);				
		Assert.assertNotNull(pocache);
		Assert.assertEquals(modification, pocache.modification);		
	}
	
	/**
	 * Cleans up PoFetched caches.
	 */
	private void cleanUp() {
		PoFetcher.caches.clear();
		((Map)ReflectionUtils.get("selections", PoFetcher.modifications)).clear();
		PoFetcher.fetchMethods.clear();
	}

}
