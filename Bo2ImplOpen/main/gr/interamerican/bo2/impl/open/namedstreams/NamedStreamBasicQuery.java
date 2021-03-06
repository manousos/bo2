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
package gr.interamerican.bo2.impl.open.namedstreams;

import gr.interamerican.bo2.arch.Provider;
import gr.interamerican.bo2.arch.Query;
import gr.interamerican.bo2.arch.exceptions.DataAccessException;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.records.Record;
import gr.interamerican.bo2.utils.Bo2UtilsEnvironment;

import java.nio.charset.Charset;

/**
 * Basic query that browses a sequential file.
 */
public abstract class NamedStreamBasicQuery 
extends AbstractStreamBasedQuery 
implements Query {
	
	/**
	 * current row.
	 */
	private int row=0;
	
	/**
	 * record.
	 */
	private Record record;
	
	/**
	 * Creates a new NamedStreamBasicQuery object. 
	 *
	 */
	public NamedStreamBasicQuery() {
		super();
		initializeRecord(); //backwards compatibility, now performed on init
	}
	
	@Override
	public void init(Provider parent) throws InitializationException {
		super.init(parent);
		initializeRecord();
	}
	
	/**
	 * Record definition.
	 * 
	 * @param charset 
	 *  
	 * @return Returns an empty record.
	 */
	protected abstract Record emptyRecord(Charset charset);
	
	public int getRow() throws DataAccessException {		
		return row;
	}
	
	public void execute() throws DataException {		
        row=0;
	}

	public boolean next() throws DataAccessException {
    	byte[] rec=null;
		try {
			rec = stream.readRecord();
		} catch (DataException e) {
			throw new DataAccessException(e);
		}	 
        if (rec!=null) {
            row++;
            record.setBytes(rec);
            return true;
        }
		return false;
	}
	
	/**
	 * Gets the current record.
	 * 
	 * @return Returns the current record.
	 */
	public Record getRecord() {
		return record;
	}
	
	/**
	 * Initializes the record. 
	 */
	void initializeRecord() {
		if(stream == null) { //backwards compatibility
			this.record = emptyRecord(Bo2UtilsEnvironment.getDefaultTextCharset()); 
		} else {
			this.record = emptyRecord(stream.getEncoding()); //normal behavior
		}
		
	}

}
