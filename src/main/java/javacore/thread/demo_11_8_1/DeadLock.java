package javacore.thread.demo_11_8_1;


class A{

	synchronized public void foo(B b){
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered A.foo");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("A interrupted.");
		}
		
		System.out.println(name + " trying to call B.last");
		b.last();
	}
	
	synchronized public void last(){
		System.out.println("Insert A.last");
	}
	
}


class B{

	synchronized public void bar(A a){
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered B.bar");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("B interrupted.");
		}
		
		System.out.println(name + " trying to call A.last");
		a.last();
	}
	
	synchronized public void last(){
		System.out.println("Insert A.last");
	}
}

public class DeadLock implements Runnable{

	A a = new A();
	B b = new B();
	
	DeadLock(){
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this , "RacingThread");
		t.start();
		a.foo(b);    // get lock on a in this thread
		System.out.println("Back in main thread");
		
	}
	
	public static void main(String[] args) {
		new DeadLock();
		

	}

	@Override
	public void run() {
		b.bar(a);    // get o lock on b in other thread
		System.out.println("Back in other thread");
		
	}

	/***
Main Thread entered A.foo
RacingThread entered B.bar
Main Thread trying to call B.last
RacingThread trying to call A.last
	 */
	
}
