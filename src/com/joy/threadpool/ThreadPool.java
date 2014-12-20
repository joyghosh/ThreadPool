package com.joy.threadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {
	
	private IQueue<Runnable> taskQueue = null;
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();
	private boolean isInActive = false;
//	private boolean isTaskQueueDynamic = false;
//	private int counter = 0;
	private final int THREAD_POOL_SIZE;
	
	/**
	 * Dynamic number of tasks can be added to
	 * Task Queue.
	 * @param noOfWorkerThreads
	 */
	public ThreadPool(int THREAD_POOL_SIZE){
		
		this.THREAD_POOL_SIZE = THREAD_POOL_SIZE; 
//		this.isTaskQueueDynamic = true;
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
	public synchronized void execute(Collection<Runnable> collection){
		//TODO
	}
	
	/**
	 * add task to queue.
	 * @param task
	 * @throws Exception 
	 */
	private synchronized void enqueue(Runnable task) throws Exception{
		
//		if(this.isTaskQueueDynamic){
//			//Technically unlimited number of tasks.
//			this.taskQueue.enqueue(task);
//			
//		}else if(!this.isTaskQueueDynamic && this.counter > 0){
//			
//			//Bounded number of tasks.
//			this.taskQueue.offer(task);
//			--this.counter;
//		}else{
//			
//			throw new IllegalStateException("BOUNDED TASK QUEUE");
//		}
		
			this.taskQueue.enqueue(task);
	}
	
	/**
	 * 
	 * @return Runnable instance of a task.
	 */
	public synchronized Runnable dequeue(){
		
//		return this.taskQueue.poll();
		return this.taskQueue.dequeue();
	}
	
	/**
	 * Deactivate the thread pool.
	 */
	public synchronized boolean deactivate(){
		this.isInActive = true;
		
//		System.out.println(normal);
		
//		//Stop or suspend all the threads in the pool.
//		if(normal==true){	
//			
//			for(WorkerThread t:threads){
//				t.stop();
//				System.out.println("Thread: "+t.getName()+" stopped");
//			}
//		}else{
//			
//			if(this.isTaskQueueDynamic){
//				
//				for(WorkerThread t:threads){
//					t.stopThread();
//					threads.add(new WorkerThread(this));
//				}
//			}else{
//				
//				for(WorkerThread t:threads)
//					t.stopThread();
//			}
//		}
		
		for(WorkerThread t:threads)
			t.stopThread();
		
		return this.isInActive;
	}
}
