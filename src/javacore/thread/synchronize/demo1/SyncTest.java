package javacore.thread.synchronize.demo1;

class Foo extends Thread {
	private int val;

	public Foo(int v) {
		val = v;
	}

	public synchronized void printVal(int v) {
		while (true)
			System.out.println(v);
	}

	public void run() {
		printVal(val);
	}
}

public class SyncTest {
	public static void main(String args[]) {
		Foo f1 = new Foo(1);
		f1.start();
		Foo f2 = new Foo(3);
		f2.start();
		/**
		 * 执行结果：
		 * 运行SyncTest产生的输出是1和3交叉的。
		 * 如果printVal是断面，你看到的输出只能是1或者只能是3而不能是两者同时出现。
		 * 程序运行的结果证明两个线程都在并发的执行printVal方法，
		 * 即使该方法是同步的并且由于是一个无限循环而没有终止。
		 * 
		 * 
		 */
	}
}