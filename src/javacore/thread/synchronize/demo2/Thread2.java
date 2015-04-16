package javacore.thread.synchronize.demo2;

public class Thread2 {
	public void m4t1() {
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out
						.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
				}
			}
		}
	}

	public synchronized void m4t2() {
//		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
				}
			}
//		}
	}

	public static void main(String[] args) {
		final Thread2 myt2 = new Thread2();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				myt2.m4t1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				myt2.m4t2();
			}
		}, "t2");
		t1.start();
		t2.start();
		
		/**
		 * Thread2.m4t2()方法中是没有synchronized代码块的
		 * 二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，
		 * 另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
		 * 
		 * Thread2.m4t2()方法中增加synchronized代码块的
		 * 三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，
		 * 其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
		 * 
		 * Thread2.m4t2()方法中去掉synchronized代码块的，将方法变为synchronized的
		 * 四、第三个例子同样适用其它同步代码块。
		 * 也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，
		 * 它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
		 * 
		 */
	}
}
