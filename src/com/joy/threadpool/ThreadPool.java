package com.joy.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
	
	private BlockingQueue<Runnable> taskQueue = null;
	private List<WorkerThread> threads = new ArrayList<WorkerThread>();
	private boolean isInActive = false;
	private boolean isTaskQueueDynamic = false;
	
	/**
	 * Dynamic number of tasks can be added to
	 * Task Queue.
	 * @param noOfWorkerThreads
	 */
	public ThreadPool(int noOfWorkerThreads){
		this.isTaskQueueDynamic = true;
		
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
		taskQueue = new ArrayBlockingQueue<Runnable>(noOfTasks);
	}
	
	public void addTask(Runnable task){
	}
}
