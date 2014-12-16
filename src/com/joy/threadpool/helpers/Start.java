package com.joy.threadpool.helpers;

import com.joy.threadpool.ThreadPool;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ThreadPool tp = new ThreadPool(5,1000);
		
		System.out.println("Starting main");
		
		for(int i=0;i<1000;i++){
			tp.execute(new PrintThread(("Thread "+Integer.toString(i)),Integer.toString(i)));
		}
        
		tp.deactivate();
		
		try {
			
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("Ending main");
	}

}
