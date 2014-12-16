package com.joy.threadpool;

public class WorkerThread extends Thread{
	private ThreadPool tp = null;
	private boolean isStopped = false;
	
	public WorkerThread(ThreadPool tp){
		this.tp = tp;
	}
	
	public void run(){
		while(!this.isStopped){
			try{
				
				Runnable runnable = (Runnable)dequeue();
				runnable.run();
			}catch(Exception e){
				
			}
		}
	}
	
	/**
	 * 
	 * @return Runnable instance of a task.
	 */
	private synchronized Runnable dequeue(){
		
		return
		   this.tp.fetchTask();
	}
	
	/**
	 * Stops the worker thread.
	 */
	public synchronized void stopThread(){
		this.isStopped = true;
		this.interrupt();
	}
	
	/**
	 * 
	 * @return the status of the worker thread.
	 */
	public synchronized boolean isStopped(){
		
		return 
		    this.isStopped;
	}
}
