package javacore.thread.synchronize.demo1;

class Foo2 extends Thread {
	private int val;

	public Foo2(int v) {
		val = v;
	}

	public void printVal(int v) {
		synchronized(Foo.class) { 
			while (true)
				System.out.println(v);
		}
	}

	public void run() {
		printVal(val);
	}
}

public class SyncTest2 {
	public static void main(String args[]) {
		Foo2 f1 = new Foo2(1);
		f1.start();
		Foo2 f2 = new Foo2(3);
		f2.start();
		
		/**
		 * 执行结果：
		 * printVal是断面，你看到的输出只能是1或者只能是3而不能是两者同时出现。
		 * 
		 * 上面的类不再对个别的类实例同步而是对类进行同步。
		 * 对于类Foo而言，它只有唯一的类定义，两个线程在相同的锁上同步，
		 * 因此只有一个线程可以执行printVal方法。
		 * 这个代码也可以通过对公共对象加锁。
		 * 例如给Foo添加一个静态成员。两个方法都可以同步这个对象而达到线程安全。
		 * 
		 */
	}
}