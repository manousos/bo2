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
package gr.interamerican.bo2.odftoolkit.span;

import org.odftoolkit.odfdom.dom.element.text.TextSpanElement;

/**
 * Span fragment that consists of a phrase.
 */
public class PhraseSpanFragment 
implements SpanFragment {
	/**
	 * Text.
	 */
	String text;

	/**
	 * Creates a new TextStringElement object. 
	 *
	 * @param text
	 */
	PhraseSpanFragment(String text) {
		super();
		this.text = text;
	}

	
	public void appendTo(TextSpanElement span) {
		span.newTextNode(text);		
	}

}
