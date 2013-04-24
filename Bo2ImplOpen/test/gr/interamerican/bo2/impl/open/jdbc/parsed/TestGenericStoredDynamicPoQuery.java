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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gr.interamerican.bo2.arch.Provider;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.impl.open.utils.Bo2;
import gr.interamerican.bo2.impl.open.workers.WorkerUtils;
import gr.interamerican.bo2.samples.archutil.po.User;
import gr.interamerican.bo2.test.utils.UtilityForBo2Test;

import java.util.List;

import org.junit.Test;

/**
 * Unit tests for {@link StoredDynamicPoQuery}.
 */
public class TestGenericStoredDynamicPoQuery {
	
	/**
	 * name.
	 */
	private static final String NAME = "George Using"; //$NON-NLS-1$
	
	/**
	 * Provider.
	 */
	Provider provider;
	
	/**
	 * @throws DataException 
	 * @throws InitializationException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings("nls")
	@Test
	public void testLifeCycle() throws DataException, InitializationException {
		UserQueryCriteria criteria = new UserQueryCriteria();
		criteria.setName(NAME);
		
		provider = Bo2.getDeployment(UtilityForBo2Test.BATCH_NO_TRAN).getProvider();		
		String path = "/gr/interamerican/rsrc/sql/SelectIdAndNameFromUsers.sql";
		GenericStoredDynamicEntitiesQuery<UserQueryCriteria> wrappedQ = 
			new GenericStoredDynamicEntitiesQuery<UserQueryCriteria>(path, criteria);
		GenericStoredDynamicPoQuery<User, UserQueryCriteria> q = 
			new GenericStoredDynamicPoQuery<User, UserQueryCriteria>(wrappedQ, User.class);
		
		q.setCriteria(criteria);
		
		q.setManagerName("LOCALDB");
		q.init(provider);
		q.open();
		q.execute();
		
		List<User> users = WorkerUtils.queryResultsAsList(q);
		assertTrue(users.size()>0);
		
		q.close();
		provider.close();
		
		User subject = users.get(0);
		assertEquals(subject.getName().trim(), NAME);
		assertNotNull(subject.getId());
	}
	
	
	
	/**
	 * Criteria for searching a user.
	 */
	private class UserQueryCriteria {
		/**
		 * name
		 */
		private String name;

		/**
		 * Gets the name.
		 *
		 * @return Returns the name
		 */
		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		/**
		 * Assigns a new value to the name.
		 *
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

}
