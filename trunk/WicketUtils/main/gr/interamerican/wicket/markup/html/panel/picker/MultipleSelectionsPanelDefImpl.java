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
package gr.interamerican.wicket.markup.html.panel.picker;

import gr.interamerican.bo2.utils.adapters.Flag;
import gr.interamerican.wicket.callback.CallbackAction;
import gr.interamerican.wicket.markup.html.panel.listTable.ListTablePanelDefImpl;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.wicket.model.IModel;

/**
 * Implementation of {@link MultipleSelectionsPanelDef}.
 * 
 * @param <B> 
 */
public class MultipleSelectionsPanelDefImpl<B extends Serializable>
extends ListTablePanelDefImpl<B>
implements MultipleSelectionsPanelDef<B> {
	
	/** serial id. */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * item selected {@link CallbackAction}
	 */ 
	private CallbackAction itemsSelectedAction;
	
	/**
	 * itemSelectedAction authorization flag.
	 */
	private Flag itemsSelectedActionFlag;
	
	/**
	 * Model.
	 */
	private IModel<ArrayList<B>> selectionsModel;
	
	/**
	 * Select button label.
	 */
	private IModel<String> selectLabelModel;
	
	/**
	 * Select/Deselect all checkbox label
	 */
	private IModel<String> checkGroupSelectorLabelModel;

	public CallbackAction getItemsSelectedAction() {
		return this.itemsSelectedAction;
	}

	public void setItemsSelectedAction(CallbackAction itemsSelectedAction) {
		this.itemsSelectedAction = itemsSelectedAction;		
	}

	public IModel<String> getSelectLabelModel() {
		return selectLabelModel;
	}

	public void setSelectLabelModel(IModel<String> label) {
		this.selectLabelModel = label;
	}

	public Flag getItemsSelectedActionFlag() {
		return itemsSelectedActionFlag;
	}

	public void setItemsSelectedActionFlag(Flag itemsSelectedActionFlag) {
		this.itemsSelectedActionFlag = itemsSelectedActionFlag;
	}

	public IModel<ArrayList<B>> getSelectionsModel() {
		return selectionsModel;
	}

	public void setSelectionsModel(IModel<ArrayList<B>> selectionsModel) {
		this.selectionsModel = selectionsModel;
	}

	public IModel<String> getCheckGroupSelectorLabelModel() {
		return checkGroupSelectorLabelModel;
	}

	public void setCheckGroupSelectorLabelModel(IModel<String> label) {
		this.checkGroupSelectorLabelModel = label;
	}

}
