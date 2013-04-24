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

import gr.interamerican.bo2.utils.Bo2UtilsEnvironment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Factory for NamedStreams.
 */
public class NamedStreamFactory {
	
	/**
	 * Creates a new NamedInputStream that wraps a file.
	 * 
	 * The specified file provides the underlying resource of the NamedStream.
	 * 
	 * @param file
	 *        File. 
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedInputStream.
	 * @throws FileNotFoundException 
	 */
	public static NamedInputStream input(File file, String name, int recordLength) 
	throws FileNotFoundException {
		InputStream in = new FileInputStream(file);
		return new NamedInputStream(StreamResource.FILE, in, name, recordLength, file);
	}
	
	/**
	 * Creates a new NamedInputStream that wraps an input stream.
	 * 
	 * The specified stream provides the underlying resource of the NamedStream.
	 * 
	 * @param in
	 *        Stream. 
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedInputStream.
	 */
	public static NamedInputStream input(InputStream in, String name, int recordLength) {
		return new NamedInputStream(StreamResource.OBJECT, in, name, recordLength, in);
	}
	
	/**
	 * Creates a new NamedInputStream that wraps an input stream that reads
	 * a byte array.
	 * 
	 * The specified byte array provides the underlying resource of the NamedStream.
	 * 
	 * @param buffer
	 *        Byte array providing data to the stream.
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedInputStream.
	 */
	public static NamedInputStream input(byte[] buffer, String name, int recordLength) {
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		return new NamedInputStream(StreamResource.BYTES, in, name, recordLength, buffer);
	}
	
	/**
	 * Creates a new NamedOutputStream that wraps a file.
	 * 
	 * The specified file provides the underlying resource of the NamedStream.
	 * 
	 * @param file
	 *        File. 
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedOutputStream.
	 * 
	 * @throws FileNotFoundException 
	 */
	public static NamedOutputStream output(File file, String name, int recordLength) 
	throws FileNotFoundException {
		OutputStream out = new FileOutputStream(file);
		return new NamedOutputStream(StreamResource.FILE, out, name, recordLength, file);
	}
	
	/**
	 * Creates a new NamedOutputStream that wraps an output stream.
	 * 
	 * The specified output stream provides the underlying resource of the NamedStream.
	 * 
	 * @param out
	 *        out. 
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedOutputStream.
	 */
	public static NamedOutputStream output(OutputStream out, String name, int recordLength) {		
		return new NamedOutputStream(StreamResource.OBJECT, out, name, recordLength, out);
	}
	
	/**
	 * Creates a new NamedOutputStream that wraps an output stream that
	 * writes in memory.
	 * 
	 * The output stream provides the underlying resource of the NamedStream.
	 *
	 * @param name
	 *        Stream name.
	 * @param recordLength 
	 *        Record length.
	 * 
	 * @return Returns the NamedOutputStream.
	 */
	public static NamedOutputStream output(String name, int recordLength) {	
		OutputStream out = new ByteArrayOutputStream();
		return new NamedOutputStream(StreamResource.BYTES, out, name, recordLength, out);
	}	
	
	/**
	 * Creates a new NamedBufferedReader that wraps a file.
	 * 
	 * The specified file provides the underlying resource of the NamedStream.
	 * 
	 * @param file
	 *        File. 
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedBufferedReader.
	 * 
	 * @throws IOException 
	 */
	public static NamedBufferedReader reader(File file, String name) 
	throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader insr = new InputStreamReader(fis, Bo2UtilsEnvironment.getDefaultTextCharset());
		BufferedReader br = new BufferedReader(insr);
		
		return new NamedBufferedReader(StreamResource.FILE, br, name, file);
	}
	
	/**
	 * Creates a new NamedBufferedReader that wraps a buffered reader.
	 * 
	 * The specified reader provides the underlying resource of the NamedStream.
	 * 
	 * @param br
	 *        Stream. 
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedBufferedReader.
	 */
	public static NamedBufferedReader reader(BufferedReader br, String name) {
		return new NamedBufferedReader(StreamResource.OBJECT, br, name, br);
	}
	
	/**
	 * Creates a new NamedInputStream that wraps an input stream that reads
	 * a byte array.
	 * 
	 * The specified byte array provides the underlying resource of the NamedStream.
	 * 
	 * @param buffer
	 *        Byte array providing data to the stream.
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedInputStream.
	 */
	public static NamedBufferedReader reader(byte[] buffer, String name) {
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inr);
		return new NamedBufferedReader(StreamResource.BYTES, br, name, buffer);
	}
	
	/**
	 * Creates a new NamedPrintStream that wraps a file.
	 * 
	 * The specified file provides the underlying resource of the NamedStream.
	 * 
	 * @param file
	 *        File. 
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedOutputStream.
	 * 
	 * @throws IOException 
	 */
	public static NamedPrintStream print(File file, String name) 
	throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream out = new PrintStream(fos, false, Bo2UtilsEnvironment.getDefaultTextCharset().name());
		return new NamedPrintStream(StreamResource.FILE, out, name, file);
	}
	
	/**
	 * Creates a new NamedPrintStream that wraps an output stream.
	 * 
	 * The specified output stream provides the underlying resource of the NamedStream.
	 * 
	 * @param out
	 *        out. 
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedPrintStream.
	 */
	public static NamedPrintStream print(PrintStream out, String name) {		
		return new NamedPrintStream(StreamResource.OBJECT, out, name, out);
	}
	
	/**
	 * Creates a new NamedPrintStream that wraps an output stream that
	 * writes in memory.
	 * 
	 * The output stream provides the underlying resource of the NamedStream.
	 *
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedPrintStream.
	 */
	public static NamedPrintStream print(String name) {	
		OutputStream out = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(out);
		return new NamedPrintStream(StreamResource.BYTES, print, name, out);
	}	
	
	/**
	 * Creates a new NamedPrintStream that wraps System.out.
	 * 
	 * System.out is the underlying resource of the NamedStream.
	 *
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedPrintStream.
	 */
	public static NamedPrintStream sysout(String name) {
		return systemStream(name, System.out);
	}	
	
	/**
	 * Creates a new NamedPrintStream that wraps System.err.
	 * 
	 * System.err is the underlying resource of the NamedStream.
	 *
	 * @param name
	 *        Stream name.
	 * 
	 * @return Returns the NamedPrintStream.
	 */
	public static NamedPrintStream syserr(String name) {
		return systemStream(name, System.err);
	}	
	
	/**
	 * Creates a new NamedPrintStream that wraps System.err.
	 * 
	 * System.err is the underlying resource of the NamedStream.
	 *
	 * @param name
	 *        Stream name.
	 * @param stream 
	 *        Stream
	 * 
	 * @return Returns the NamedPrintStream.
	 */
	public static NamedPrintStream systemStream(String name, PrintStream stream) {
		return new NamedPrintStream(StreamResource.SYSTEM, stream, name, stream);
	}	
	
	
	
	

}
