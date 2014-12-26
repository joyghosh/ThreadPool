package com.joy.threadpool.helpers;

import java.util.logging.Logger;
import java.util.logging.Level;

public class PrintThread extends Thread{
	
		private Logger logger = Logger.getLogger(PrintThread.class.getName());
		private String msg = null;
		
		public PrintThread(String threadName,String msg){
			this.setName(threadName);
			this.msg = msg;
		}
		
        @Override
		public void run(){
			
                    for(int i=0;i<100;i++){
                            logger.log(Level.INFO,"Thread:"+this.getName()+", message: "+this.msg);
                    }
		}
        
        @Override
        public String toString(){
			
        	return "Task ID: "+this.getName();
        }
}
