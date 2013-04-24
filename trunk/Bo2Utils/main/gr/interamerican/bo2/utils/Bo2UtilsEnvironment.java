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
package gr.interamerican.bo2.utils;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Runtime environment for Bo2Utils.
 */
public class Bo2UtilsEnvironment {
		
	/**
	 * Singleton instance.
	 */
	static final Bo2UtilsEnvironment SINGLETON = new Bo2UtilsEnvironment(); 
	
	/**
	 * Sets the environment parameters.
	 * 
	 * @param shortDf 
	 * @param isoDf 
	 * @param longDf 
	 * @param charset 	   
	 */
	public static void setEnvironment(String shortDf, String isoDf, String longDf, String charset) {
		SINGLETON.dfShort = new SimpleDateFormat(shortDf);
		SINGLETON.dfIso = new SimpleDateFormat(isoDf);
		SINGLETON.dfLong = new SimpleDateFormat(longDf);
		SINGLETON.textCharset = Charset.forName(charset);
	}
	
	/**
	 * Gets the defaultTextCharset.
	 *
	 * @return Returns the defaultTextCharset
	 */
	public static Charset getDefaultTextCharset() {
		return SINGLETON.textCharset;
	}
	
	/**
	 * Gets the short date format.
	 * 
	 * @return Returns the short date format.
	 */
	public static DateFormat getShortDateFormat() {
		return SINGLETON.dfShort;
	}
	
	/**
	 * Gets the short date format pattern.
	 * 
	 * @return Returns the short date format pattern.
	 */
	public static String getShortDateFormatPattern() {		
		return  SINGLETON.dfShort.toPattern();
	}
	
	/**
	 * Gets the iso date format.
	 * 
	 * @return Returns the iso date format.
	 */
	public static DateFormat getIsoDateFormat() {
		return SINGLETON.dfIso;
	}
	
	/**
	 * Gets the iso date format pattern.
	 * 
	 * @return Returns the iso date format pattern.
	 */
	public static String getIsoDateFormatPattern() {		
		return  SINGLETON.dfIso.toPattern();
	}
	
	/**
	 * Gets the long date format.
	 * 
	 * @return Returns the long date format.
	 */
	public static DateFormat getLongDateFormat() {
		return SINGLETON.dfLong;
	}
	
	/**
	 * Gets the long date format pattern.
	 * 
	 * @return Returns the long date format pattern.
	 */
	public static String getLongDateFormatPattern() {		
		return  SINGLETON.dfLong.toPattern();
	}
	
	/**
	 * Default date format (short format).
	 */
	private SimpleDateFormat dfShort = new SimpleDateFormat("dd/MM/yyyy");  //$NON-NLS-1$

	/**
	 * ISO date format (ISO format).
	 * 
	 */
	private SimpleDateFormat dfIso = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
	
	 /**
	 * long date format for calendar objets with time
	 */
	private SimpleDateFormat dfLong = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS"); //$NON-NLS-1$
	
	/**
	 * Default text charset. This is the charset that should be used when reading or writing
	 * text files. In any other case (in-memory conversions) it is recommended to use the 
	 * default platform charset. 
	 */
	private Charset textCharset = Charset.defaultCharset();
	
	/**
	 * Creates a new Bo2UtilsEnvironment object. 
	 * 
	 * Hidden constructor.
	 *
	 */
	private Bo2UtilsEnvironment() {
		super();
	}

	/**
	 * Gets the dfShort.
	 *
	 * @return Returns the dfShort
	 */
	SimpleDateFormat getDfShort() {
		return dfShort;
	}

	/**
	 * Gets the dfIso.
	 *
	 * @return Returns the dfIso
	 */
	SimpleDateFormat getDfIso() {
		return dfIso;
	}

	/**
	 * Gets the dfLong.
	 *
	 * @return Returns the dfLong
	 */
	SimpleDateFormat getDfLong() {
		return dfLong;
	}
	
	/**
	 * Gets the textCharset.
	 *
	 * @return Returns the textCharset
	 */
	Charset getTextCharset() {
		return textCharset;
	}	

}
