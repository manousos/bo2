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

import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.impl.open.hibernate.tuple.Bo2PojoEntityTuplizer;
import gr.interamerican.bo2.impl.open.hibernate.tuple.resolver.Bo2EntityNameResolver;
import gr.interamerican.bo2.utils.StringConstants;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionFactoryImpl;

/**
 * This class manages all hibernate configurations and their SessionFactory 
 * objects.
 */
public class HibernateConfigurations {
	
	/**
	 * Schema property on a hibernate config.
	 */
	static final String SCHEMA_PROPERTY = "hibernate.default_schema"; //$NON-NLS-1$
	
	/**
	 * Static map that stores all session factories, mapped to the path
	 * of their configuration files.
	 */
	static Map<String, SessionFactory> sessionFactories = 
		new HashMap<String, SessionFactory>();
	
	/**
	 * Gets the session factory that corresponds to the specified configuration
	 * file.
	 * 
	 * @param pathToCfg
	 *        Path to the configuration resource file.
	 * @param dbSchema
	 *        Db schema.
	 * 
	 * @return Returns the session factory.
	 * 
	 * @throws InitializationException
	 *         If the creation of the session factory fails.
	 */
	public static SessionFactory getSessionFactory(String pathToCfg, String dbSchema) 
	throws InitializationException {
		SessionFactory sessionFactory = sessionFactories.get(key(pathToCfg, dbSchema));
		if (sessionFactory==null) {
			sessionFactory = createSessionFactory(pathToCfg, dbSchema);
			sessionFactories.put(key(pathToCfg, dbSchema), sessionFactory);
		}
		return sessionFactory;
	}
	
	
	/**
	 * Creates a SessionFactory.
	 * 
	 * @param pathToCfg
	 *        Path to the hibernate configuration file.
	 * @param dbSchema
	 *        Db schema.
	 *        
	 * @return Returns the session factory.
	 * 
	 * @throws InitializationException 
	 *         If the creation of the SessionFactory fails.
	 */
	static SessionFactory createSessionFactory(String pathToCfg, String dbSchema) 
	throws InitializationException {
		try {
			Configuration conf = new Configuration();
			conf.setProperty(SCHEMA_PROPERTY, dbSchema);
			conf.configure(pathToCfg);
			conf.getEntityTuplizerFactory().registerDefaultTuplizerClass(EntityMode.POJO, Bo2PojoEntityTuplizer.class);
			SessionFactory sessionFactory = conf.buildSessionFactory();
			((SessionFactoryImpl) sessionFactory).registerEntityNameResolver(Bo2EntityNameResolver.INSTANCE, EntityMode.POJO);
			sessionFactory.getStatistics().setStatisticsEnabled(true);
			return sessionFactory;
		} catch (HibernateException e) {
			throw new InitializationException(e);
		}
	}
	
	/**
	 * @param pathToCfg
	 * @param dbSchema
	 * @return Returns a key for local caching.
	 */
	private static String key(String pathToCfg, String dbSchema) {
		return pathToCfg + StringConstants.UNDERSCORE + dbSchema;
	}
	
}
