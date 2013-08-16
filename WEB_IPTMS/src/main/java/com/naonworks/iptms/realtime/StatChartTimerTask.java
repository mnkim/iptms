package com.naonworks.iptms.realtime;

import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.naonworks.iptms.common.LoofTimerTask;
import com.naonworks.iptms.constants.GlobalStored;
import com.naonworks.iptms.service.StatChartService;
import com.naonworks.iptms.vo.SbcIdleDBBean;

public class StatChartTimerTask extends LoofTimerTask {
	private Thread	thisThread;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(StatChartTimerTask.class);

	@Autowired
	StatChartService statChartService;
	
	@Override
	public void stopTask() {
		Thread th = this.thisThread;
		this.thisThread = null;
		th.interrupt();
	}

	@Override
	public void run() {
		this.thisThread = Thread.currentThread();
		try {
			Thread.sleep(delay);
		} catch ( InterruptedException ie ) {}
		
		do {
			try {
				JSONArray retVal = statChartService.getCurrStatChartData();
				
				if (retVal.size() > 0) {
					GlobalStored.getInstance().chartData = retVal; 
					logger.debug(GlobalStored.getInstance().chartData.toString());
				}
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e1) {}
				
			} catch ( Exception e ) {
				logger.error(e.getStackTrace().toString());
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e1) {}
			}
			
		} while( infinitely && thisThread != null );	
	}
}