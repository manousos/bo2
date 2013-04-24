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
package gr.interamerican.bo2.impl.open.hibernate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import gr.interamerican.bo2.arch.exceptions.InitializationException;

import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * Unit test for {@link HibernateConfigurations}.
 */
public class TestHibernateConfigurations {
	
	/**
	 * Unit test for createSessionFactory.
	 * 
	 * @throws InitializationException
	 */
	@Test
	public void testCreateSessionFactory_succeed() throws InitializationException {
		String pathToCfg = "/gr/interamerican/rsrc/hibernate.cfg.xml"; //$NON-NLS-1$
		SessionFactory factory = HibernateConfigurations.createSessionFactory(pathToCfg, "TEST"); //$NON-NLS-1$
		assertNotNull(factory);
	}
	
	/**
	 * Unit test for createSessionFactory.
	 * 
	 * @throws InitializationException
	 */
	@Test(expected=InitializationException.class)
	public void testCreateSessionFactory_fail() throws InitializationException {
		String pathToCfg = "/com/foo.bar"; //$NON-NLS-1$
		HibernateConfigurations.createSessionFactory(pathToCfg, "TEST"); //$NON-NLS-1$
	}
	
	/**
	 * Unit test for createSessionFactory.
	 * 
	 * @throws InitializationException
	 */
	@Test
	public void testGetSessionFactory() throws InitializationException {
		String pathToCfg = "/gr/interamerican/rsrc/hibernate.cfg.xml"; //$NON-NLS-1$
		SessionFactory factory1 = HibernateConfigurations.getSessionFactory(pathToCfg, "TEST"); //$NON-NLS-1$
		assertNotNull(factory1);
		SessionFactory factory2 = HibernateConfigurations.getSessionFactory(pathToCfg, "TEST"); //$NON-NLS-1$
		assertSame(factory1, factory2);
	}
	

}
