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
package gr.interamerican.bo2.samples.operations;

import gr.interamerican.bo2.arch.Operation;
import gr.interamerican.bo2.arch.Provider;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;

/**
 * Operation that is not doing anything.
 */
public class EmptyOperation implements Operation {
	
	public void open() throws DataException {
		/*empty*/		
	}
	
	public void close() throws DataException {
		/*empty*/		
	}
	
	public void init(Provider parent) throws InitializationException {
		/*empty*/		
	}
	
	public Provider getProvider() {
		return null;
	}
	
	public void execute() throws LogicException, DataException {
		/*empty*/		
	}

}
