package com.joy.threadpool.helpers;

public class PrintThread extends Thread{
	
		private String msg = null;
		
		public PrintThread(String threadName,String msg){
			this.setName(threadName);
			this.msg = msg;
		}
		
		public void run(){
			
			System.out.println("Thread:"+this.getName()+", message: "+this.msg);
		}
}
