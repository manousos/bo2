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

import gr.interamerican.bo2.utils.adapters.VoidOperation;
import gr.interamerican.bo2.utils.concurrent.ThreadUtils;
import gr.interamerican.bo2.utils.conditions.Condition;

/**
 * The {@link Monitor} monitors a monitored  
 * 
 * @param <T> 
 * 
 */
public class Monitor<T> 
implements Runnable {
	
	/**
	 * Object being monitored.
	 */
	T system;
	
	/**
	 * time interval.
	 */
	long interval = 0;
	
	/**
	 * Stop condition;
	 */
	Condition<T> mustStop;
	

	/**
	 * Action to execute each time.
	 */
	VoidOperation<T> action;
	
	/**
	 * Creates a new Monitor object. 
	 *
	 * @param system
	 *        System to monitor
	 * @param interval
	 *        Interval between two sub-sequent monitor calls in milliseconds.     
	 * @param mustStop
	 *        Condition that checks when the monitoring process must stop.
	 * @param action
	 *        Action to execute on every monitoring call.
	 */
	public Monitor(T system, long interval, final Condition<T> mustStop, VoidOperation<T> action) {
		super();
		this.system = system;
		this.interval = interval;
		this.mustStop = mustStop;
		this.action = action;
	}


	@Override
	public void run() {
		while(!mustStop.check(system)) {
			ThreadUtils.sleepMillis(interval);
			action.execute(system);
		}
		action.execute(system); //Execute one last time after it's finished.
	}	

}
