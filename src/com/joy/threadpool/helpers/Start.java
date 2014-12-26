package com.joy.threadpool.helpers;

import com.joy.threadpool.ThreadPool;
import com.joy.threadpool.samples.BatchTask;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting main");
		
//		ThreadPool tp = new ThreadPool(5,5000);
//		
//		for(int i=0;i<5050;i++){
//			try {
//				tp.execute(new PrintThread(("Thread "+Integer.toString(i)),Integer.toString(i)));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println(e.getMessage());
//				break;
//			}
//		}
//		
//		try {
//			
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		System.out.println("2nd Batch of jobs.");
//		for(int i=5002;i<10000;i++){
//			try {
//				tp.execute(new PrintThread(("Thread "+Integer.toString(i)),Integer.toString(i)));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println(e.getMessage());
//				break;
//			}
//		}
//		
//		try {
//			
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		tp.deactivate();
		
		BatchTask bt = new BatchTask(5);
		bt.startBatchTask();

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		bt.stopBatch();
		
        System.out.println("Ending main");
	}

}
