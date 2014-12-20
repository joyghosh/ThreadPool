package com.joy.threadpool.helpers;

import com.joy.threadpool.ThreadPool;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ThreadPool tp = new ThreadPool(5,5000);
		
		System.out.println("Starting main");
		
		for(int i=0;i<5050;i++){
			try {
				tp.execute(new PrintThread(("Thread "+Integer.toString(i)),Integer.toString(i)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				break;
			}
		}
		
		try {
			
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("2nd Batch of jobs.");
		for(int i=5002;i<10000;i++){
			try {
				tp.execute(new PrintThread(("Thread "+Integer.toString(i)),Integer.toString(i)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				break;
			}
		}
		
		try {
			
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tp.deactivate();
		
        System.out.println("Ending main");
	}

}
