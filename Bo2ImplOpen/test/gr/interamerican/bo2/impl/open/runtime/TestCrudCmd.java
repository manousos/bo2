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
package gr.interamerican.bo2.impl.open.runtime;

import gr.interamerican.bo2.arch.PersistenceWorker;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.PoNotFoundException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.test.def.posamples.Customer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CrudCmd}.
 */
public class TestCrudCmd {
	
	/**
	 * Unit test for all methods.
	 * @throws LogicException 
	 * @throws DataException 
	 * @throws UnexpectedException 
	 */
	@SuppressWarnings("nls")
	@Test
	public void testAllMethods() throws UnexpectedException, DataException, LogicException {
		PersistenceWorker<Customer> pw = Factory.createPw(Customer.class);		
		CrudCmd<Customer> cmd = new CrudCmd<Customer>(pw);
		
		Customer cust = Factory.create(Customer.class);
		cust.setCustomerNo("TestCrudCmd");
		
		Customer saved = cmd.store(cust);
		Assert.assertNotNull(saved);
		Customer read = cmd.read(cust);
		Assert.assertNotNull(read);
		read.setCustomerName("Updated");		
		Customer updated = cmd.update(read);
		Assert.assertNotNull(updated);
		
		cmd.delete(updated);		
		try {
			read = cmd.read(cust);
		} catch (PoNotFoundException e) {
			/*
			 * Expecting a PoNotFoundException.
			 */
		}
	}
	
	/**
	 * Unit test for the constructors.
	 */
	@Test
	public void testConstructors() {
		PersistenceWorker<Customer> pw = Factory.createPw(Customer.class);		
		
		CrudCmd<Customer> cmd1 = new CrudCmd<Customer>(pw, true);
		Assert.assertEquals(pw, cmd1.pw);
		Assert.assertTrue(cmd1.ignorePnfeOnDelete);
		
		CrudCmd<Customer> cmd2 = new CrudCmd<Customer>(pw, false);
		Assert.assertEquals(pw, cmd2.pw);
		Assert.assertFalse(cmd2.ignorePnfeOnDelete);
	}

}
