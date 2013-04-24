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
package gr.interamerican.bo2.arch.utils;

import static org.junit.Assert.assertEquals;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for class {@link Bo2ArchUtils}.
 */
public class TestBo2ArchUtils {
	
	/**
	 * tests SetLastModifiedByToPo
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testRuntimeException(){
		RuntimeException exception = new RuntimeException();
		assertEquals(exception,Bo2ArchUtils.runtimeException(exception));
		
		Throwable t = new Throwable();
		UnexpectedException unexpected = new UnexpectedException(t);
		Bo2ArchUtils.runtimeException(unexpected);
	}
	
	
	/**
	 * tests SetLastModifiedByToPo
	 */
	@Test
	public void testUnwrap(){
		Throwable t = new Throwable();
		RuntimeException wrapper = new RuntimeException(t);
		Throwable actual = Bo2ArchUtils.unwrap(wrapper);
		Assert.assertEquals(t, actual);
		
		UnexpectedException unexpected = new UnexpectedException(t);
		Bo2ArchUtils.unwrap(unexpected);
		actual = Bo2ArchUtils.unwrap(unexpected);
		Assert.assertEquals(unexpected, actual);
	}
	
	
	

	
	
}
