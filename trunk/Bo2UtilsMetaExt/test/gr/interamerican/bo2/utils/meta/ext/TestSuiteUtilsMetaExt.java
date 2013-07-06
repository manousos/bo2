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
package gr.interamerican.bo2.utils.meta.ext;

import gr.interamerican.bo2.utils.meta.ext.descriptors.TestSuiteUtilsMetaExtDescriptors;
import gr.interamerican.bo2.utils.meta.ext.factories.TestSuiteUtilsMetaExtFactories;
import gr.interamerican.bo2.utils.meta.ext.formatters.TestSuiteUtilsMetaExtFormaters;
import gr.interamerican.bo2.utils.meta.ext.formatters.nf.TestSuiteUtilsMetaExtFormatersNf;
import gr.interamerican.bo2.utils.meta.ext.formatters.nr.TestSuiteUtilsMetaExtFormatersNr;
import gr.interamerican.bo2.utils.meta.ext.parsers.TestSuiteUtilsMetaExtParsers;
import gr.interamerican.bo2.utils.meta.ext.validators.TestSuiteUtilsMetaExtValidators;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for package <code>gr.interamerican.bo2.impl.open.po.collections</code>.
 * 
 */
@RunWith(Suite.class)
@SuiteClasses(
	{

		TestSuiteUtilsMetaExtDescriptors.class,
		TestSuiteUtilsMetaExtValidators.class,
		TestSuiteUtilsMetaExtFormaters.class,
		TestSuiteUtilsMetaExtFormatersNf.class,
		TestSuiteUtilsMetaExtFormatersNr.class,
		TestSuiteUtilsMetaExtFactories.class,
		TestSuiteUtilsMetaExtParsers.class
	}
)
public class TestSuiteUtilsMetaExt {
	/* empty */
}