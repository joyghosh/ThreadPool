package com.joy.threadpool;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue<T> implements IQueue<T>{
	
	private Queue<T> taskQueue; 
	private int TASK_QUEUE_SIZE;
	private boolean isBounded = false;
	
	/**
	 * Unbounded task queue.
	 */
	public TaskQueue(){
		this.taskQueue = new ConcurrentLinkedQueue<T>();
	}
	
	/**
	 * Bounded task queue.
	 * @param TASK_QUEUE_SIZE
	 */
	public TaskQueue(int TASK_QUEUE_SIZE){
		this.TASK_QUEUE_SIZE = TASK_QUEUE_SIZE;
		this.isBounded = true;
		this.taskQueue = new ConcurrentLinkedQueue<T>();
	}
	
	
	@Override
	public synchronized void enqueue(T task) throws Exception {
		
		if(this.isBounded){
			if(this.TASK_QUEUE_SIZE > 0){
				this.taskQueue.offer(task);
				--this.TASK_QUEUE_SIZE;
			}else{
				throw new Exception("BOUNDED TASK QUEUE");
			}
				return;
		}
	
		this.taskQueue.add(task);
		notifyAll();
	}

	
	@Override
	public synchronized void enqueue(Collection<T> tasks) throws Exception {
		
		if(this.isBounded){
			if(tasks.size()<=this.TASK_QUEUE_SIZE){
				this.taskQueue.addAll(tasks);	//TODO: seriously? Think about it.
				this.TASK_QUEUE_SIZE = this.TASK_QUEUE_SIZE - tasks.size();
			}else{
				throw new Exception("BOUNDED TASK QUEUE");
			}
				return;
		}
		
//		System.out.println("Collection: "+tasks);
		this.taskQueue.addAll(tasks);
		notifyAll();
	}
	
	
	@Override
	public synchronized T dequeue() {
		
		if(this.isBounded){
				
			//all jobs done.
			if(this.taskQueue.isEmpty())
				return null;
			else
				return this.taskQueue.poll();
		}else{
			
			//wait for more jobs to be queued up.
			while(this.taskQueue.isEmpty()){
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return this.taskQueue.poll();
		}
	}
}
