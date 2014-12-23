package com.joy.threadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThreadPool {
	
	private IQueue<Runnable> taskQueue = null;
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();
	private boolean isInActive = false;
	private final int THREAD_POOL_SIZE;
	
	/**
	 * Dynamic number of tasks can be added to
	 * Task Queue.
	 * @param noOfWorkerThreads
	 */
	public ThreadPool(int THREAD_POOL_SIZE){
		
		this.THREAD_POOL_SIZE = THREAD_POOL_SIZE; 
		this.taskQueue = new TaskQueue<Runnable>();
		init();
	}
	
	/**
	 * Fixed sized task queue.
	 * @param noOfWorkerThreads
	 * @param noOfTasks
	 */
	public ThreadPool(int THREAD_POOL_SIZE,int noOfTasks){
		
		this.THREAD_POOL_SIZE = THREAD_POOL_SIZE;
		this.taskQueue = new TaskQueue<Runnable>(noOfTasks);
		init();
	}
	
	/**
	 * Initialize method
	 */
	private void init(){
			
		//creating workers in the pool.
		for(int i = 0; i< this.THREAD_POOL_SIZE;i++){
			threads.add(new WorkerThread(this.taskQueue,Integer.toString(i)));
		}
				
		//starting the worker threads in pool.
		for(WorkerThread w:threads){
			w.start();
		}

	}
	
	/**
	 * Add task to Thread
	 * @param task
	 * @throws Exception 
	 */
	public synchronized void execute(Runnable task) throws Exception{
		
		if(this.isInActive) throw
				new IllegalStateException("THREAD POOL INACTIVE");
		
		enqueue(task);
	}
	
	/*TODO add capability for batch job addition.*/
	public synchronized void execute(Collection<Runnable> tasks) throws Exception{
		
		if(this.isInActive) throw
				new IllegalStateException("THREAD POOL INACTIVE");
		
		enqueue(tasks);
	}
	
	/**
	 * add task to queue.
	 * @param task
	 * @throws Exception 
	 */
	private synchronized void enqueue(Runnable task) throws Exception{
		
			this.taskQueue.enqueue(task);
	}
	
	/**
	 * add job batch to queue.
	 * @param tasks batch of jobs
	 * @throws Exception 
	 */
	private synchronized void enqueue(Collection<Runnable> tasks) throws Exception{
		
			this.taskQueue.enqueue(tasks);
	}
	
	/**
	 * 
	 * @return Runnable instance of a task.
	 */
	public synchronized Runnable dequeue(){
		
		return this.taskQueue.dequeue();
	}
	
	/**
	 * Deactivate the thread pool.
	 */
	public synchronized boolean deactivate(){
		this.isInActive = true;
		
		for(WorkerThread t:threads)
			t.stopThread();
		
		return this.isInActive;
	}
}
