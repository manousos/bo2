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
package gr.interamerican.bo2.samples.archutil.beans;

/**
 * Mock user, always authorized.
 * 
 * @param <A> 
 */
@SuppressWarnings("nls")
public class MockAuthorizedUser<A>  
extends AbstractMockUser<A>{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new MockAuthorizedUser object. 
	 *
	 * @param userId
	 */
	public MockAuthorizedUser(String userId) {
		super(userId);
	}
	
	public boolean isAuthorized(A authorizationId) {		
		return true;
	}	
	
	public String getAuthorizationToken(A authorizationId) {		
		return "1";
	}
	
	public String getEmailAddress() {	
		return getUserId() + "@bo2.org";
	}

}
