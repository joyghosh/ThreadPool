package com.joy.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ThreadPool {
	
	private BlockingQueue<Runnable> taskQueue = null;
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();
	private boolean isInActive = false;
	private boolean isTaskQueueDynamic = false;
	private int counter = 0;
	
	/**
	 * Dynamic number of tasks can be added to
	 * Task Queue.
	 * @param noOfWorkerThreads
	 */
	public ThreadPool(int noOfWorkerThreads){
		this.isTaskQueueDynamic = true;
		this.taskQueue = new PriorityBlockingQueue<Runnable>();
		
		//creating workers in the pool.
		for(int i = 0; i< noOfWorkerThreads;i++){
			threads.add(new WorkerThread());
		}
		
		//starting the worker threads in pool.
		for(WorkerThread w:threads){
			w.start();
		}
	}
	
	/**
	 * Fixed sized task queue.
	 * @param noOfWorkerThreads
	 * @param noOfTasks
	 */
	public ThreadPool(int noOfWorkerThreads,int noOfTasks){
		this.taskQueue = new ArrayBlockingQueue<Runnable>(noOfTasks);
		this.counter = noOfTasks;
	}
	
	/**
	 * add task to queue.
	 * @param task
	 */
	public synchronized void addTask(Runnable task){
		
		if(this.isTaskQueueDynamic){
		
			this.taskQueue.add(task);
			
		}else if(this.counter > 0){
			
			this.taskQueue.add(task);
			--this.counter;
		}
	}
}
