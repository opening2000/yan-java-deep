package javacore.thread.demo_11_7_2;

class CallMe{
	//此处不使用synchronized进行修饰，进行同步，在调用点使用同步块
	void call(String msg){
		System.out.print("["+msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("thread interrupted");
		}
		System.out.println("]");
	}
}

class Caller implements Runnable{

	String msg;
	CallMe target;
	Thread thread;
	
	public Caller(CallMe targ , String s){
		target = targ;
		msg = s;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		synchronized (target) {
			target.call(msg);
			
		}
	}
	
}


public class SynchDemo2 {

	
	public static void main(String[] args) {
		CallMe target = new CallMe();
		Caller ob1 = new Caller(target, "Hello");
		Caller ob2 = new Caller(target, "Synchronized");
		Caller ob3 = new Caller(target, "World");
		
		try {
			//wait for threads end
			ob1.thread.join();
			ob2.thread.join();
			ob3.thread.join();
			
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		}
	}

}
