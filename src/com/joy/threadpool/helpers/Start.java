package com.joy.threadpool.helpers;

import com.joy.threadpool.ThreadPool;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ThreadPool tp = new ThreadPool(5,10);
		
		System.out.print("Starting main");
		
		for(int i=0;i<10;i++){
			tp.execute(new PrintThread(new String("Thread "+Integer.toString(i)),Integer.toString(i)));
		}
	}

}
