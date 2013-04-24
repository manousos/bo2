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
package gr.interamerican.wicket.util.resource;

import java.io.IOException;

import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ByteArrayAsResourceStream}.
 */
public class TestByteArrayAsResourceStream {
	
	/**
	 * tests the one arg constructor.
	 */
	@Test
	public void testConstructor() {
		byte[] array = new byte[10];
		ByteArrayAsResourceStream resource = new ByteArrayAsResourceStream(array);
		Assert.assertEquals(array, resource.fileData);
	}
	
	/**
	 * tests the getInputStream. 
	 * @throws ResourceStreamNotFoundException 
	 */
	@Test
	public void testGetInputStream() 
	throws ResourceStreamNotFoundException  {
		byte[] array = new byte[10];
		ByteArrayAsResourceStream resource = new ByteArrayAsResourceStream(array);
		Assert.assertNotNull(resource.getInputStream());
	}
	
	/**
	 * tests close. 
	 * @throws IOException 
	 */
	@Test
	public void testClose() throws IOException  {
		byte[] array = new byte[10];
		ByteArrayAsResourceStream resource = new ByteArrayAsResourceStream(array);
		resource.close();
	}
	
}
