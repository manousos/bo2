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
package gr.interamerican.bo2.utils.sql.types;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Short {@link Type}.
 */
public class ShortType 
extends NonQuotedType<Short> {
	/**
	 * Instance.
	 */
	public static final ShortType INSTANCE = new ShortType();
	
	/**
	 * Creates a new ShortType object. 
	 *
	 */
	private ShortType() {
		super();
	}
	
	public Short get(ResultSet rs, String columnIndex) throws SQLException {		
		return rs.getShort(columnIndex);
	}
	
	public Short get(ResultSet rs, int columnIndex) throws SQLException {		
		return rs.getShort(columnIndex);
	}
	
	public Class<Short> getJavaType() {		
		return Short.class;
	}

}
