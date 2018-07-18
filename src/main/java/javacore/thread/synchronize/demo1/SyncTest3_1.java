package javacore.thread.synchronize.demo1;

class Foo3_1 extends Thread {
	private int val;

	public Foo3_1(int v) {
		val = v;
	}

	public void printVal(int v) {
		synchronized (this) {
			while (true)
				System.out.println(v);
		}
	}

	public void run() {
		printVal(val);
	}
}

public class SyncTest3_1 {
	public static void main(String args[]) {
		Foo3_1 f1 = new Foo3_1(1);
		f1.start();
		Foo3_1 f2 = new Foo3_1(3);
		f2.start();
		
		/**
		 * 执行结果：
		 * 1和3交替出现
		 * 
		 * 对实例进行同步。
		 * 当一个线程占有这个实例的同步方法的时候，其他线程不能访问这个实例的同步方法，
		 * 可以访问其他实例的同步方法
		 * 
		 * synchronized(Foo.class) { 
		 * ...
		 * }
		 * 则是对类进行同步
		 * 当一个线程占有这个类的同步方法的时候，其他线程不能访问这个类的同步方法
		 * 参考javacore.thread.synchronize.demo1.SyncTest2
		 * 
		 */
	}
}