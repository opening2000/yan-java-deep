package javacore.thread.demo_11_8;


class Q{
	int n;

	public int get() {
		System.out.println("Got:" + n);
		return n;
	}

	public void put(int n) {
		System.out.println("Put:" + n);
		this.n = n;
	}
	
}

class Producer implements Runnable{
	Q q;

	public Producer(Q q) {
		this.q = q;
		new Thread(this , "Pproducer").start();
	}
	
	@Override
	public void run() {
		int i = 0;
		while (true) {
			q.put(i++);
			
		}
	}
}

class Consumer implements Runnable{
	Q q;

	public Consumer(Q q) {
		this.q = q;
		new Thread(this , "Consumer").start();
	}
	
	@Override
	public void run() {
		while (true) {
			q.get();
			
		}
		
	}
	
}

public class PC {

	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
		System.out.println("Press Ctrl + C to stop.");
		
		/**
		 * 生产这生产一个产品后，消费者对这件产品进行了多次消费，而生产者再次生产之后的产品却不一定会被消费
		 * java正确的写法应该是使用wati和notify对两个方向进行标示
		 */
	}

}
