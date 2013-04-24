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
import gr.interamerican.bo2.samples.implopen.mocks.MockAbstractNamedStreamsManager;
import gr.interamerican.bo2.samples.implopen.mocks.MockNamedStream;

import java.util.Properties;
import java.util.Set;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for {@link SharedNamedStreamsRegistry}.
 */
public class TestSharedNamedStreamsRegistry {
	
	/**
	 * Clear the registry.
	 */
	@BeforeClass @AfterClass
	public static void cleanup() {
		SharedNamedStreamsRegistry.name2stream.clear();
		SharedNamedStreamsRegistry.stream2name.clear();
		SharedNamedStreamsRegistry.providersAccessingStream.clear();
	}
	
	static NamedStreamsProvider nsp1;
	static NamedStream<?> stream;
	String name = "name";

	/**
	 * Test registerStream
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testRegisterStream() {
		nsp1 = new MockAbstractNamedStreamsManager(new Properties());
		stream = new MockNamedStream(StreamType.INPUTSTREAM, StreamResource.FILE, null, name, 5);
		SharedNamedStreamsRegistry.register(name, stream, nsp1);
		Assert.assertEquals(stream, SharedNamedStreamsRegistry.name2stream.get(name));
		Assert.assertEquals(name, SharedNamedStreamsRegistry.stream2name.get(stream));
		Set<NamedStreamsProvider> providers = SharedNamedStreamsRegistry.providersAccessingStream.get(stream);
		Assert.assertNotNull(providers);
		Assert.assertTrue(providers.size()==1);
		Assert.assertEquals(nsp1, providers.iterator().next());
	}
	
	/**
	 * Test getStream
	 */
	@Test
	public void testGetStream() {
		NamedStreamsProvider nsp = new MockAbstractNamedStreamsManager(new Properties());
		Assert.assertEquals(stream, SharedNamedStreamsRegistry.getStream(name, nsp));
		Assert.assertTrue(SharedNamedStreamsRegistry.providersAccessingStream.get(stream).size()==2);
	}
	
	/**
	 * Test releaseSharedStreams
	 * 
	 * @throws DataException
	 */
	@Test
	public void testReleaseSharedStreams() throws DataException {
		SharedNamedStreamsRegistry.releaseSharedStreams(nsp1);
		Assert.assertTrue(SharedNamedStreamsRegistry.providersAccessingStream.get(stream).size()==1);
		Assert.assertEquals(stream, SharedNamedStreamsRegistry.name2stream.get(name));
		Assert.assertEquals(name, SharedNamedStreamsRegistry.stream2name.get(stream));
	}

}
