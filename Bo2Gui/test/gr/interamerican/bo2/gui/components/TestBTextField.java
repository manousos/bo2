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
package gr.interamerican.bo2.gui.components;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link BTextField}.
 */
public class TestBTextField {
	/**
	 * Sample name.
	 */
	String name = "sample"; //$NON-NLS-1$
	
	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor() {
		BTextField sample = new BTextField(name);
		Assert.assertEquals(name, sample.componentName);		
	}
	
	/**
	 * Tests getName().
	 */
	@Test
	public void testGetComponentName() {
		BTextField sample = new BTextField("X"); //$NON-NLS-1$
		Assert.assertEquals(sample.componentName,sample.getComponentName());		
	}
	
	/**
	 * Tests getValue().
	 */
	@Test
	public void testGetValue() {
		BTextField sample = new BTextField(name);
		String text = "text"; //$NON-NLS-1$
		sample.setText(text);
		Assert.assertEquals(text,sample.getValue());		
	}
	
	/**
	 * Tests getValue().
	 */
	@Test
	public void testSetValue() {
		BTextField sample = new BTextField(name);
		String text = "text"; //$NON-NLS-1$
		sample.setValue(text);
		Assert.assertEquals(text,sample.getText());		
	}
	
	

}
