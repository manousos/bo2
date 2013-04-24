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
package gr.interamerican.bo2.impl.open.hibernate.types;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



/**
 * Test suite for package <code>gr.interamerican.bo2.impl.open.hibernate</code>.
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
	{	
		TestEntryUserTypeForLong.class,
		TestEntryListUserTypeForLong.class,
		TestOneBasedEnumUserType.class,
		TestUserTypeUtil.class,
		TestEntryUserType.class,
		TestEntryUserTypeForInteger.class,
		TestMonetaryAmountUserType.class,
		TestEntryListUserType.class,
		TestTwoWayConverterUserType.class
	}
)
public class TestSuiteBo2ImplOpenHibernateTypes {
	/* empty */
}
