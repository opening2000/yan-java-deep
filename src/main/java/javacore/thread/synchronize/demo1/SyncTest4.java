package javacore.thread.synchronize.demo1;

class Foo4 extends Thread {
	private String name;
	private String val;

	public Foo4(String name, String v) {
		this.name = name;
		val = v;
	}

	public void printVal() {
		synchronized (val) {
			while (true)
				System.out.println(name + val);
		}
	}

	public void run() {
		printVal();
	}
}

public class SyncTest4 {

	public static void main(String[] args) {
		Foo4 f1 = new Foo4("Foo 1:", "printVal");
		f1.start();
		Foo4 f2 = new Foo4("Foo 2:", "printVal");
		f2.start();

		/**
		 * 结果说明：
		 * 结果一直是Foo 1:printVal 或者 Foo 2:printVal
		 * 因为"printVal"是一个对象
		 * 
		 * 如果改为Foo4 f2 = new Foo4("Foo 2:", "printVal1");
		 * 则结果中会交叉出现两种情况
		 */
		
		/**
		 * 上面这个代码需要进行一些额外的说明，因为JVM有一种优化机制，
		 * 因为String类型的对象是不可变的，
		 * 因此当你使用""的形式引用字符串时，如果JVM发现内存已经有一个这样的对象，
		 * 那么它就使用那个对象而不再生成一个新的String对象，这样是为了减小内存的使用。
		 * 上面的main方法其实等同于：
			public static void main(String args[]) 
			{
			　String value="printVal";
			　Foo f1 = new Foo("Foo 1:",value); 
			　f1.start(); 
			　Foo f2 = new Foo("Foo 2:",value);
			　f2.start(); 
			} 
		 */
	}

}
