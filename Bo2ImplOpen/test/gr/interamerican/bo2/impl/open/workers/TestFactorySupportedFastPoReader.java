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

import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.impl.open.runtime.AbstractBo2RuntimeCmd;
import gr.interamerican.bo2.samples.archutil.po.User;
import gr.interamerican.bo2.samples.archutil.po.UserKey;
import gr.interamerican.bo2.test.utils.UtilityForBo2Test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link FactorySupportedFastPoReader}.
 */
public class TestFactorySupportedFastPoReader {

	
	/**
	 * query to test
	 */
	FactorySupportedFastPoReader<UserKey, User> fastReader = 
		new FactorySupportedFastPoReader<UserKey, User>(User.class);
	
	
	
	/**
	 * Tests execute
	 * @throws UnexpectedException
	 * @throws DataException
	 * @throws LogicException
	 */
	@Test
	public void testGet() throws UnexpectedException, DataException, LogicException {
		/*
		 * setup data for this test.
		 */
		new AbstractBo2RuntimeCmd() { 			
			@Override public void work() throws LogicException, 
			DataException, InitializationException, UnexpectedException {
				fastReader.init(getProvider());
				fastReader.open();
				
				int userid = UtilityForBo2Test.getExistingUserId();
				UserKey key = new UserKey();
				key.setId(userid);
				User user = fastReader.get(key);
				Assert.assertNotNull(user);
				Assert.assertTrue(fastReader.cache.containsKey(key));
				Assert.assertTrue(fastReader.cache.containsValue(user));
				fastReader.close();
			}
		}.execute();
		
		
	}
	
	
	

	
	
}
