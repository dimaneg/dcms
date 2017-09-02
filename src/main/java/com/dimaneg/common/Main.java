package com.dimaneg.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	
	private static final AtomicInteger threadCounter = new AtomicInteger();

	private static int c = 0;
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for(int i = 0; i<10; i++) {
			new Thread(new MyRunnable()).run();
			//executor.execute(new MyRunnable());
		}
	}
	
	private static class MyRunnable implements Runnable {

		@Override
		public void run() {
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Erro trying to sleep");
			}*/
			//System.out.println(threadCounter.incrementAndGet());
			System.out.println(c++);
		}
		
		
	}
}
