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
package gr.interamerican.bo2.samples.abstractimpl;

import gr.interamerican.bo2.creation.annotations.MockMethods;
import gr.interamerican.bo2.creation.annotations.MockProperties;
import gr.interamerican.bo2.samples.interfaces.SmartCalc;

/**
 * Implementation that mocks some properties.
 */
@SuppressWarnings("serial")
@MockProperties({"beanId","beanName","numbersToAdd","left","right","alarmLimit","result"})
@MockMethods({"reset","add","subtract","addNumbers","contains"})
public abstract class AbstractBeanWithMockMethodsAndProperties 
implements SmartCalc {
	/* empty */	
}
