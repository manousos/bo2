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
package gr.interamerican.bo2.gui.batch;

import gr.interamerican.bo2.arch.batch.MultiThreadedLongProcess;
import gr.interamerican.bo2.gui.frames.BFrame;
import gr.interamerican.bo2.gui.layout.Layout;
import gr.interamerican.bo2.impl.open.runtime.concurrent.BatchProcess;
import gr.interamerican.bo2.impl.open.runtime.concurrent.BatchProcessInput;
import gr.interamerican.bo2.impl.open.runtime.concurrent.BatchProcessParm;
import gr.interamerican.bo2.impl.open.runtime.concurrent.BatchProcessParmFactoryImpl;
import gr.interamerican.bo2.impl.open.runtime.concurrent.IsFinished;
import gr.interamerican.bo2.impl.open.runtime.concurrent.LongProcessMail;
import gr.interamerican.bo2.utils.ReflectionUtils;
import gr.interamerican.bo2.utils.StringUtils;
import gr.interamerican.bo2.utils.TimeUtils;
import gr.interamerican.bo2.utils.concurrent.ThreadUtils;
import gr.interamerican.bo2.utils.conditions.Condition;
import gr.interamerican.bo2.utils.runnables.Monitor;

import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * BatchProcessFrameForJob.
 */
public class BatchProcessFrameForJob 
extends BFrame {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Input panel.
	 */
	BatchProcessInputPanel inputPanel;
	
	/**
	 * Process panel.
	 */
	MultiThreadedLongProcessPanel processPanel;
	
	/**
	 * Batch process.
	 * This variable takes value when the batch process starts. 
	 * Before that it is null, indicating that processing didn't
	 * start yet.
	 */
	BatchProcess<?> batch;
	
	/**
	 * Creates a new BatchProcessFrameForJob object. 
	 * @param input 
	 * @param criteria 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BatchProcessFrameForJob(BatchProcessInput input, Object criteria) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BatchProcessParm<?> parameters = new BatchProcessParmFactoryImpl().createParameter(input, criteria);
		batch = new BatchProcess(parameters);
		
		Properties model = createPropertiesWithInput(input, criteria);
		inputPanel = new BatchProcessInputPanel(model, false);
		inputPanel.addButton("start", null, this); //$NON-NLS-1$
		Layout.layAsStackOfLabeledFields(inputPanel, 5, 5);
		setPanel(inputPanel);
	}
	
	/**
	 * Starts the batch process.
	 */
	public void start() {
		new Thread(batch).start();		
		long interval = batch.getInitialThreads() * 10;
		ThreadUtils.sleepMillis(interval);
		processPanel = new MultiThreadedLongProcessPanel(batch);
		setPanel(processPanel);
		startMonitoringThread();
	}
	
	/**
	 * Starts the monitoring thread.
	 * 
	 * TODO: duplicate of {@link BatchProcessFrame}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void startMonitoringThread() {		
		BatchProcessParm bpi = batch.getParameters();
		int interval = bpi.getMonitoringMailInterval();
		String recipients = bpi.getMonitoringMailRecipients();
		boolean start = (interval>0) && (!StringUtils.isNullOrBlank(recipients));
		if (start) {			 
			Condition<MultiThreadedLongProcess> stop = 
				new IsFinished<MultiThreadedLongProcess>();
			LongProcessMail mail = new LongProcessMail();
			mail.setStatusMessageRecipients(recipients);
			long millis = TimeUtils.minutes2millis(interval);
			Monitor<MultiThreadedLongProcess> monitor = 
				new Monitor(batch, millis, stop, mail);
			new Thread(monitor).start();
		}
	}
	
	/**
	 * Creates a Properties object with the job input. This is used as a preview
	 * for the operator that launches the job.
	 * 
	 * @param input
	 * @param criteria
	 * 
	 * @return Properties with the job input.
	 */
	Properties createPropertiesWithInput(BatchProcessInput input, Object criteria) {
		Properties p = new Properties();
		Map<String, Object> mapBpi = ReflectionUtils.getProperties(input);
		Map<String, Object> mapCriteria = ReflectionUtils.getProperties(criteria);
		copyMapToProperties(mapBpi, p);
		copyMapToProperties(mapCriteria, p);
		return p;
	}
	
	/**
	 * Copies a map to a properties object.
	 * @param map
	 * @param p
	 */
	void copyMapToProperties(Map<String,Object> map, Properties p) {
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			p.setProperty(entry.getKey(), StringUtils.toString(entry.getValue()));
		}
	}

}
