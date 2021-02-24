package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

	static AtomicInteger num = new AtomicInteger(0);
	
	static int n = 0;
	
	public static void main(String[] args) {
		test2();
	}

	public static void test1() {
		for(int i=0;i<3;i++) {
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					synchronized (TestAtomicInteger.class) {
						while(n < 1000) {
							System.out.println("thread name:" + Thread.currentThread().getName() + ":" + n++);
						}
					}
				}
			});
			thread.start();
		}
	}
	
	public static void test2() {
		for(int i=0;i<3;i++) {
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					while(num.get() < 1000) {
						System.out.println("thread name:" + Thread.currentThread().getName() + ":" + num.getAndIncrement());
					}
				}
			});
			thread.start();
		}
	}
}
