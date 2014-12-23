package com.joy.threadpool.samples;

import java.util.Collection;
import java.util.Random;

import com.joy.threadpool.ThreadPool;
import com.joy.threadpool.helpers.PrintThread;

public class BatchTask{
		
		private Collection<Runnable> tasks;
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
			
			if(this.noOfTasks == 0){
				this.tp = new ThreadPool(this.THREAD_POOL_SIZE);
				this.noOfTasks = new Random().nextInt() + 1;					//minimum 1 task in the batch.
			}
			else{
				this.tp = new ThreadPool(this.THREAD_POOL_SIZE,this.noOfTasks);
			}
			
			for(int i = 0;i<this.noOfTasks;i++){
				this.tasks.add(new PrintThread("Thread "+Integer.toString(i),Integer.toString(i)));
			}
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
}
