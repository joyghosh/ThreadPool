package com.joy.threadpool.samples;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.joy.threadpool.ThreadPool;
import com.joy.threadpool.helpers.PrintThread;

public class BatchTask{
		
		Logger logger = Logger.getLogger(BatchTask.class.getName());
	
		private Queue<Runnable> tasks;
		private ThreadPool tp;
		private int THREAD_POOL_SIZE;
		private int noOfTasks = 0;		
		
		public BatchTask(int THREAD_POOL_SIZE){
				this.THREAD_POOL_SIZE = THREAD_POOL_SIZE;
				init();
		} 
		
		public BatchTask(int THREAD_POOL_SIZE,int noOfTasks){
				this.THREAD_POOL_SIZE = THREAD_POOL_SIZE;
				this.noOfTasks = noOfTasks;										//overwrite the number of tasks.
				init();
		}
		
		public void init(){
			
			this.tasks = new ConcurrentLinkedQueue<Runnable>();
			
			if(this.noOfTasks == 0){
				
				logger.log(Level.INFO, "Unbounded task queue");
				this.tp = new ThreadPool(this.THREAD_POOL_SIZE);
				this.noOfTasks = new Random().nextInt(1001) + 1;					//minimum 1 task in the batch.
				logger.log(Level.INFO,"Random no of tasks: "+this.noOfTasks);
			}
			else{
				logger.log(Level.INFO, "Unbounded task queue");
				logger.log(Level.INFO,"Random no of tasks: "+this.noOfTasks);
				this.tp = new ThreadPool(this.THREAD_POOL_SIZE,this.noOfTasks);
			}
			
			for(int i = 0;i<this.noOfTasks;i++){
				this.tasks.offer(new PrintThread("Thread "+Integer.toString(i),Integer.toString(i)));
//				logger.log(Level.INFO,"Task added: "+this.tasks.peek());
			}
			
			logger.log(Level.INFO,"Task added: "+this.tasks.peek());
		}
		
		public void startBatchTask(){
			
			try {
				this.tp.execute(this.tasks);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Exception: "+e.getMessage());
			}
		}
		
		public void stopBatch(){
			
			this.tp.deactivate();
		}
}
