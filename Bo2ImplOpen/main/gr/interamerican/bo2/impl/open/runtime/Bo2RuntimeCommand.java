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
package gr.interamerican.bo2.impl.open.runtime;

import gr.interamerican.bo2.arch.Operation;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.utils.ReflectionUtils;

/**
 * {@link AbstractBo2RuntimeCmd} implementation used because there
 * is a yet to be identified bug with {@link RuntimeCommand} in
 * WAS deployments.
 * 
 * @param <O>
 *        Type of Operation to execute.
 *        
 * @deprecated Use {@link RuntimeCommand} instead
 */
@Deprecated
public class Bo2RuntimeCommand<O extends Operation> extends AbstractBo2RuntimeCmd {
	
	/**
	 * Operation of this command.
	 */
	private Class<O> opCls;
	
	/**
	 * O instance.
	 */
	private O op;
	
	/**
	 * Input properties.
	 */
	private String[] properties;
	
	/**
	 * Input values.
	 */
	private Object[] values;
	
	/**
	 * Creates a new Bo2RuntimeCmd object. 
	 *
	 * @param deploymentPath 
	 *        Path to the resource file that contains the deployment
	 *        properties.
	 * @param opCls
	 *        Operation to execute.
	 * @param properties
	 *        Input properties
	 * @param values 
	 *        Input values.
	 */
	public Bo2RuntimeCommand(String deploymentPath, Class<O> opCls, String[] properties, Object[] values) {
		super(deploymentPath);
		this.opCls = opCls;
		this.properties = properties;
		this.values = values;
		if(!validate()) {
			throw new RuntimeException("Bad properties - values input."); //$NON-NLS-1$
		}
	}
	
	/**
	 * Creates a new Bo2RuntimeCmd object. 
	 *
	 * @param opCls
	 *        Operation to execute.
	 * @param properties
	 *        Input properties
	 * @param values 
	 *        Input values.
	 */
	public Bo2RuntimeCommand(Class<O> opCls, String[] properties, Object[] values) {
		this(null, opCls, properties, values);
	}
	
	/**
	 * Creates a new Bo2RuntimeCmd object. 
	 *
	 * @param opCls
	 *        Operation to execute.
	 */
	public Bo2RuntimeCommand(Class<O> opCls) {
		this(null, opCls, null, null);
	}

	@Override
	public void work() throws LogicException, DataException, InitializationException, UnexpectedException {
		op = open(opCls);
		int i=0;
		if (properties!=null) {
			for(String property : properties) {
				ReflectionUtils.setProperty(property, values[i], op);
				i++;
			}
		}
		op.execute();
	}
	
	/**
	 * Validates input.
	 * @return True if input is OK.
	 */
	private boolean validate() {
		if(properties==null) {
			return values==null;
		}
		return values!=null && properties.length==values.length;
	}
	
	/**
	 * @return Returns the operation.
	 */
	public O getOperation() {
		return op;
	}

}
