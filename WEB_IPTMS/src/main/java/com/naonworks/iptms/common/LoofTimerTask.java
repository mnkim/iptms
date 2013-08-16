package com.naonworks.iptms.common;

import java.util.TimerTask;

public abstract class LoofTimerTask extends TimerTask {

	protected int interval = 3000;
	protected boolean infinitely = true;
	protected int delay = 3000;
	
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public boolean isInfinitely() {
		return infinitely;
	}
	public void setInfinitely(boolean infinitely) {
		this.infinitely = infinitely;
	}
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public abstract void stopTask();

}
