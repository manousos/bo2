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
package gr.interamerican.bo2.impl.open.operations;

import gr.interamerican.bo2.arch.PersistenceWorker;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.impl.open.runtime.AbstractBo2RuntimeCmd;
import gr.interamerican.bo2.impl.open.runtime.CrudCmd;
import gr.interamerican.bo2.impl.open.workers.AbstractResourceConsumer;
import gr.interamerican.bo2.test.def.posamples.Invoice;
import gr.interamerican.bo2.test.def.posamples.InvoiceKey;
import gr.interamerican.bo2.test.def.posamples.SamplesFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This test works because the object graph that is to be
 * copied does not include many to one references.
 */
public class TestCopyToOtherSystemOperation {
	/**
	 * Invoice No.
	 */
	private static final String INVOICENO = "INV45678"; //$NON-NLS-1$
	
	/**
	 * From manager.
	 */
	private static final String FROM = "LOCALDB"; //$NON-NLS-1$
	
	/**
	 * To manager.
	 */
	private static final String TO = "OTHERDB"; //$NON-NLS-1$
	
	/**
	 * CRUD command for setup and teardown.
	 */
	CrudCmd<Invoice> crudFrom;
	/**
	 * CRUD command for setup and teardown.
	 */
	CrudCmd<Invoice> crudTo;	
	
	
	/**
	 * Test sample.
	 */
	Invoice sample;
	
	
	
	/**
	 * Creates a new TestCopyToOtherSystemOperation object. 
	 *
	 */
	public TestCopyToOtherSystemOperation() {
		super();
		PersistenceWorker<Invoice> from = Factory.createPw(Invoice.class);
		AbstractResourceConsumer rcFrom = (AbstractResourceConsumer) from;
		rcFrom.setManagerName(FROM);
		crudFrom = new CrudCmd<Invoice>(from,true);
		
		PersistenceWorker<Invoice> to = Factory.createPw(Invoice.class);
		AbstractResourceConsumer rcTo = (AbstractResourceConsumer) to;
		rcTo.setManagerName(TO);
		crudTo = new CrudCmd<Invoice>(to,true);
		
		SamplesFactory factory = SamplesFactory.getBo2Factory();
		sample = factory.sampleInvoiceFull(3);
		sample.setInvoiceNo(INVOICENO);
	}

	/**
	 * Test setup.
	 * @throws LogicException 
	 * @throws DataException 
	 * @throws UnexpectedException 
	 */
	@Before
	public void setup() throws UnexpectedException, DataException, LogicException {		
		crudFrom.delete(sample);
		crudTo.delete(sample);
		crudFrom.store(sample);
	}
	
	/**
	 * Test tear down.
	 * @throws LogicException 
	 * @throws DataException 
	 * @throws UnexpectedException 
	 */
	@After
	public void teardown() throws UnexpectedException, DataException, LogicException {
		crudFrom.delete(sample);
		crudTo.delete(sample);
	}	
	
	/**
	 * Unit test for execute.
	 * @throws LogicException 
	 * @throws DataException 
	 * @throws UnexpectedException 
	 */
	@Test
	public void testExecute() 
	throws UnexpectedException, DataException, LogicException {
		new AbstractBo2RuntimeCmd() {			
			@Override
			public void work() throws LogicException, DataException,
			InitializationException, UnexpectedException {				
				CopyToOtherSystemOperation<Invoice, InvoiceKey> op =
					new CopyToOtherSystemOperation<Invoice, InvoiceKey>(Invoice.class, FROM, TO);
				op.setKey(sample.getKey());
				op.init(getProvider());
				op.open();
				op.execute();
				op.close();
			}
		}.execute();
		
		Invoice copied = crudTo.read(sample);
		Assert.assertEquals(sample, copied);
	}

}