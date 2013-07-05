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
package gr.interamerican.wicket.bo2.markup.html.form;

import gr.interamerican.bo2.utils.meta.descriptors.BoPropertyDescriptor;
import gr.interamerican.wicket.bo2.utils.SelfDrawnUtils;
import gr.interamerican.wicket.utils.MarkupConstants;

import java.io.Serializable;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Base class for self drawn TextField components.
 * 
 * @param <T>
 *        Type of model object.
 */
public abstract class AbstractSelfDrawnTextField<T extends Serializable> extends TextField<T>{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * BoPropertyDescriptor.
     */
    protected BoPropertyDescriptor<T> descriptor;
      
    /**
     * Creates a new SelfDrawnTextField object.
     *
     * @param id
     * @param descriptor
     * @param model
     */
    public AbstractSelfDrawnTextField(String id, IModel<T> model, BoPropertyDescriptor<T> descriptor) {
        super(id, model);
        this.descriptor = descriptor;
        SelfDrawnUtils.<T>standardSelfDrawnFormComponentStuff(this, descriptor);
    }
   
    /**
     * Creates a new SelfDrawnTextField object.
     *
     * @param id
     * @param descriptor
     */
    public AbstractSelfDrawnTextField(String id, BoPropertyDescriptor<T> descriptor) {
        super(id, new Model<T>());
        this.descriptor = descriptor;
        SelfDrawnUtils.<T>standardSelfDrawnFormComponentStuff(this, descriptor);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        tag.setName(MarkupConstants.INPUT);
        tag.put(MarkupConstants.TYPE, MarkupConstants.TEXT);
        tag.put(MarkupConstants.STYLE, MarkupConstants.WIDTH);
        super.onComponentTag(tag);
     }
    
}