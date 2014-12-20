package com.joy.threadpool;

public interface IQueue<T> {
	
		public void enqueue(T object) throws Exception;
		public T dequeue();
}
