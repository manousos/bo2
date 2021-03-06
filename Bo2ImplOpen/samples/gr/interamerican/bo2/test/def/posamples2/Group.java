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
package gr.interamerican.bo2.test.def.posamples2;

import gr.interamerican.bo2.arch.PersistentObject;

import java.util.Date;
import java.util.List;

/**
 * 
 */
public interface Group 
extends PersistentObject<GroupKey> {
	
	public Integer getGroupId();
	public void setGroupId(Integer groupId);
	
	public String getName();
	public void setName(String name);
	
	public Date getEstablishment();
	public void getEstablishment(Date establishment);
	
	public List<Person> getMembers();
	public void setMembers(List<Person> members);
	
	public Person getPresident();
	public void setPresident(Person president);

}
