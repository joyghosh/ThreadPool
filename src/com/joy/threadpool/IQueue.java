package com.joy.threadpool;

import java.util.Collection;

public interface IQueue<T> {
	
		public void enqueue(T object) throws Exception;
		public void enqueue(Collection<? extends T> objects) throws Exception; 
		public T dequeue();
}
