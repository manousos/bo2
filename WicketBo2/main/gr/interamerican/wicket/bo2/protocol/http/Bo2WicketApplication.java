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
package gr.interamerican.wicket.bo2.protocol.http;

import gr.interamerican.bo2.arch.ext.TranslatableEntry;

import java.util.Collection;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;

/**
 * Base class for Wicket applications using Bo2.
 * 
 * @param <I> Type of user identifier.
 * @param <A> Type of authorization identifier.
 * @param <L> Type of language id.
 */
public abstract class Bo2WicketApplication<A, L extends Comparable<? super L>> 
extends WebApplication {

	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		return new Bo2WicketRequestCycle(this, (WebRequest)request, (WebResponse)response);
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new Bo2WicketSession<A, L>(request);
	}
	
	/**
	 * Default language ids.
	 * 
	 * The login screen, should be drawn according to this language id.
	 * 
	 * @return Returns the id of the default language.
	 */
	public abstract L defaultLanguageId(); 
	
	/**
	 * Collection with the languages supported by the system.
	 * 
	 * @return Returns the available languages.
	 */
	public abstract Collection<TranslatableEntry<L, ?, L>> supportedLanguages();

}
