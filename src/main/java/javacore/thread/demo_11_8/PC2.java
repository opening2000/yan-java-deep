package javacore.thread.demo_11_8;


class Q2{
	int n;
	
	boolean valueSet = false;    //有没有商品生产出来
	
	synchronized public int get() {
		//如果没有商品生产出来，就进行等待，生产出来再进行消费
		if(!valueSet){
			try {
				wait(5000);
			} catch (Exception e) {
				System.out.println("interrupted exception caught");
			}
		}
		System.out.println("Got:" + n);
		valueSet = false;
		notify();
		return n;
	}

	synchronized public void put(int n) {
		//如果已经有商品生产出来还没有被消费，那么就暂停生产，等待消费后再次生产，避免浪费
		if(valueSet){
			try {
				wait(5000);
			} catch (Exception e) {
				System.out.println("interrupted exception caught");
			}
		}
		this.n = n;
		valueSet = true;
		System.out.println("Put:" + n);
		notify();
	}
	
}

class Producer2 implements Runnable{
	Q2 q;

	public Producer2(Q2 q) {
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

class Consumer2 implements Runnable{
	Q2 q;

	public Consumer2(Q2 q) {
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

public class PC2 {

	public static void main(String[] args) {
		Q2 q = new Q2();
		new Producer2(q);
		new Consumer2(q);
		System.out.println("Press Ctrl + C to stop.");
		
		/**
		 * 生产这生产一个产品后，消费者对这件产品进行了多次消费，而生产者再次生产之后的产品却不一定会被消费
		 * java正确的写法应该是使用wati和notify对两个方向进行标示
		 */
		/**
		 * 
			Got:34224
			Put:34225
			Got:34225
			Put:34226
			Got:34226
			
			
		 */
	}

}
