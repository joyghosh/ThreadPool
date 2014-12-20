package com.joy.threadpool.helpers;

public class PrintThread extends Thread{
	
		private String msg = null;
		
		public PrintThread(String threadName,String msg){
			this.setName(threadName);
			this.msg = msg;
		}
		
                @Override
		public void run(){
			
                    for(int i=0;i<100;i++){
                    		System.out.println("Hi!");
                            System.out.println("Thread:"+this.getName()+", message: "+this.msg);
                    }
		}
}
