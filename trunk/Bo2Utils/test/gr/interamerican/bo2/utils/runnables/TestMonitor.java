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
package gr.interamerican.bo2.utils.runnables;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import gr.interamerican.bo2.utils.Debug;
import gr.interamerican.bo2.utils.adapters.VoidOperation;
import gr.interamerican.bo2.utils.conditions.Condition;

import org.junit.Test;

/**
 * Unit test for {@link Monitor}.
 */
public class TestMonitor {
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testConstructor() {
		Object o = new Object();
		Condition<Object> stop = mock(Condition.class);
		VoidOperation<Object> action = mock(VoidOperation.class);
		int i = 1000;
		Monitor<Object> monitor = new Monitor<Object>(o, i, stop, action);
		assertEquals(o, monitor.system);
		assertEquals(i, monitor.interval);
		assertEquals(stop, monitor.mustStop);
		assertEquals(action, monitor.action);
	}
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testRun() {
		Object o = new Object();
		Condition<Object> stop = mock(Condition.class);
		final String counter = "Monitor";		 //$NON-NLS-1$
		VoidOperation<Object> action = new VoidOperation<Object>() {			 
			@Override
			public void execute(Object a) {
				Debug.increaseCounter(counter);					
			}
		};		
		when(stop.check(o)).thenReturn(false,false,false,true);
		int i = 2;
		Debug.resetCounter(counter);
		Monitor<Object> monitor = new Monitor<Object>(o, i, stop, action);
		monitor.run();
		assertEquals(4, Debug.getCounter(counter));
	}

}
