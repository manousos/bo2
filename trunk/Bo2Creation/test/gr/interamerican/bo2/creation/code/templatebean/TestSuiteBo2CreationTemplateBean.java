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
package gr.interamerican.bo2.creation.code.templatebean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Test suite for package <code>gr.interamerican.bo2.impl.open.jdbc</code>.
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
	{			
		TestPropertyWithDirectAccessCodeTemplates.class,
		TestPropertyWithReflectiveAccessCodeTemplates.class,
		TestDelegatePropertyWithDirectAccessCodeTemplates.class,
		TestDelegatePropertyWithReflectiveAccessCodeTemplates.class,
		TestDelegateToOtherPropertyCodeTemplates.class,
		
		TestEmptyMethodCodeTemplates.class,
		TestDelegateMethodWithDirectAccessCodeTemplates.class,
		TestDelegateMethodWithReflectiveAccessCodeTemplates.class,
		TestFieldInitializationCodeTemplates.class,
		TestMethodsImplCodeTemplates.class,
	}
)
public class TestSuiteBo2CreationTemplateBean {
	/* empty */
}
