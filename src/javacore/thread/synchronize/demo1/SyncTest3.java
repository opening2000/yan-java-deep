package javacore.thread.synchronize.demo1;

class Foo3 extends Thread {
	private int val;
	private static Object lock = new Object();

	public Foo3(int v) {
		val = v;
	}

	public void printVal(int v) {
		synchronized (lock) {
			while (true)
				System.out.println(v);
		}
	}

	public void run() {
		printVal(val);
	}
}

public class SyncTest3 {
	public static void main(String args[]) {
		Foo3 f1 = new Foo3(1);
		f1.start();
		Foo3 f2 = new Foo3(3);
		f2.start();
		
		/**
		 * 
		 * 给出一个参考实现，给出同步公共对象的两种通常方法，如上
		 * 
		 * 　上面的这个例子比原文给出的例子要好一些，
		 * 因为原文中的加锁是针对类定义的，一个类只能有一个类定义，
		 * 而同步的一般原理是应该尽量减小同步的粒度以到达更好的性能。
		 * 笔者给出的范例的同步粒度比原文的要小。
		 * 
		 * 
		 * 
		 */
	}
}