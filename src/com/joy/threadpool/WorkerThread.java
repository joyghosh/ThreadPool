package com.joy.threadpool;

import java.util.logging.Logger;
import java.util.logging.Level;

public class WorkerThread extends Thread{
	
	private Logger logger = Logger.getLogger(WorkerThread.class.getName());
	
	private IQueue<Runnable> taskQueue;
	private boolean isStopped = false;
	private String threadId;
	
	public WorkerThread(IQueue<Runnable> taskQueue,String threadId){
		this.taskQueue = taskQueue;
		this.threadId = threadId;
	}
	
	public void run(){
		while(!this.isStopped){
			try{
				Runnable runnable = (Runnable)this.taskQueue.dequeue();
				logger.log(Level.INFO,"Fetched task: "+runnable.toString());
				
				if(!runnable.equals(null))
					runnable.run();
				else
					logger.log(Level.INFO,"No tasks in the queue.");
				
			}catch(Exception e){
				
			}
		}
	}
	
	/**
	 * Stops the worker thread.
	 */
	public synchronized void stopThread(){
		this.isStopped = true;
		this.interrupt();
	}
	
	/**
	 * @return status of the worker thread.
	 */
	public synchronized boolean isStopped(){
		
		return this.isStopped;
	}
	
	/**
	 * @return assigned thread id.
	 */
	public String getThreadId(){
		return this.threadId;
	}
}
