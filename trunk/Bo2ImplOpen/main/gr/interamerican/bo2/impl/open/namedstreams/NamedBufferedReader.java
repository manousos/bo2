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

import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.DataOperationNotSupportedException;
import gr.interamerican.bo2.impl.open.utils.Exceptions;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Implementation of NamedStream for BufferedReader.
 * 
 *
 */
public class NamedBufferedReader extends AbstractNamedStream<BufferedReader> {
	
	
	/**
	 * Creates a new NamedBufferedReader object.
	 * 
	 * @param resourceType
	 * @param stream
	 * @param name
	 * @param resource 
	 */
	NamedBufferedReader(
			StreamResource resourceType, BufferedReader stream, 
			String name, Object resource) {
		super(StreamType.BUFFEREDREADER, resourceType, stream, name, 0, resource);
	}

	public boolean find(byte[] key) 
	throws DataException, DataOperationNotSupportedException {
		throw Exceptions.dataOperationNotSupported(getStream());
	}

	public byte[] readRecord() 
	throws DataException {
		String record=readString();
		if (record==null) {
			return null;
		}
		return record.getBytes();
	}
	
	public String readString() 
	throws DataException {
		try {
			String record=stream.readLine();
			return record;
		} catch (IOException e) {
			throw new DataException(e);
		}        
	}
	
	public void writeRecord(byte[] record) 
	throws DataException, DataOperationNotSupportedException {
		throw Exceptions.dataOperationNotSupported(stream);		
	}

	public void writeString(String string) 
	throws DataException, DataOperationNotSupportedException {
		throw Exceptions.dataOperationNotSupported(stream);
	}

	public void close() throws DataException {
		try {
			stream.close();
		} catch (IOException e) {
			throw new DataException(e);
		}
	}

}
