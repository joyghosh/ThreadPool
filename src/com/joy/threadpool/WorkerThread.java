package com.joy.threadpool;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{
	private BlockingQueue taskQueue = null;
	private boolean isStopped = false;
	
	public WorkerThread(){
		
	}
	
	private synchronized Runnable dequeue(){
		
		return null;	
	}
}
