package com.naonworks.iptms.common;

public class TaskThread extends Thread {
	private LoofTimerTask task;
	
	public TaskThread( LoofTimerTask task ) {
		super(task, task.getClass().getSimpleName());
		this.task = task;
	}
	
	public void stopThread() {
		this.task.stopTask();
	}
}
