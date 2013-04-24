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
package gr.interamerican.wicket.factories;

import gr.interamerican.wicket.markup.html.panel.service.ServicePanel;
import gr.interamerican.wicket.markup.html.panel.service.ServicePanelDef;

/**
 * Interface for factories of {@link ServicePanel}
 */
public interface ServicePanelFactory {
	
	/**
	 * Creates the service panel that matches to a service panel definition.
	 * 
	 * @param definition
	 *        ServicePanel definition.
	 * @param <P>
	 *        Type of panel.
	 * 
	 * @return Returns a service panel.
	 */
	public <P extends ServicePanel> P createPanel(ServicePanelDef definition);

}
