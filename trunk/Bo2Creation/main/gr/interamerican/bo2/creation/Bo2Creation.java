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
package gr.interamerican.bo2.creation;

import javassist.ClassPool;

/**
 * Utility class for the Bo2Creation module.
 */
public class Bo2Creation {
	
	/**
	 * {@link ClassPool}.
	 */
	private static ClassPool bo2ClassPool;
	
	static {
		bo2ClassPool = new ClassPool();
		bo2ClassPool.appendSystemPath();
	}
	
	/**
	 * Gets the bo2ClassPool.
	 *
	 * @return Returns the bo2ClassPool
	 */
	public static ClassPool getBo2ClassPool() {
		return bo2ClassPool;
	}

}
