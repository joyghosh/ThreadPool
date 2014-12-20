package com.joy.threadpool;

public class WorkerThread extends Thread{
	//	private ThreadPool tp = null;
	private IQueue<Runnable> taskQueue;
	private boolean isStopped = false;
	private String threadId;
	
	public WorkerThread(IQueue taskQueue,String threadId){
//		this.tp = tp;
		this.taskQueue = taskQueue;
		this.threadId = threadId;
	}
	
	public void run(){
		while(!this.isStopped){
			try{
				Runnable runnable = (Runnable)this.taskQueue.dequeue();
				if(!runnable.equals(null))
					runnable.run();
				else{
					System.out.println("No tasks in the queue.");
				}
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
	 * 
	 * @return status of the worker thread.
	 */
	public synchronized boolean isStopped(){
		
		return this.isStopped;
	}
	
	/**
	 * 
	 * @return assigned thread id.
	 */
	public String getThreadId(){
		return this.threadId;
	}
}
