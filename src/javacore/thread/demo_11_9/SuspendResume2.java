package javacore.thread.demo_11_9;


class NewThread2 implements Runnable {

	String name;
	Thread t;
	boolean suspendFlag;
	
	NewThread2 (String threadName){
		name = threadName;
		t = new Thread(this , name);
		System.out.println("New Thread : " + t);
		suspendFlag = false;
		t.start();
	}
	
	@Override
	public void run() {
		try {
			for(int i=15;i>0;i--){
				System.out.println(name + " : " + i );
				Thread.sleep(200);
				synchronized (this) {
					while (suspendFlag) {
						wait();
						
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(name + " interrupted.");
		}
		System.out.println(name + " exit.");
	}
	
	void mysuspend(){
		suspendFlag = true;
	}
	
	synchronized void myresume(){
		suspendFlag = false;
		notify();
	}
}

public class SuspendResume2 {

	public static void main(String[] args) {
		NewThread2 ob1 = new NewThread2 ("One");
		NewThread2 ob2 = new NewThread2("Two");
		
		try {
			Thread.sleep(1000);
			ob1.mysuspend();
			System.out.println("Suspend thread One.");
			Thread.sleep(1000);
			ob1.myresume();
			System.out.println("Resume thread One.");
			ob2.mysuspend();
			System.out.println("Suspend thread Two.");
			Thread.sleep(1000);
			ob2.myresume();
			System.out.println("Resume thread Two.");
		} catch (InterruptedException e) {
			System.out.println("Main Thread Interrupted.");
		}
		
		try {
			System.out.println("Waiting for threads to finish.");
			ob1.t.join();
			ob2.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main Thread Interrupted.");
		}
		System.out.println("Main Thread exit.");
		
	}

	/*
			New Thread : Thread[One,5,main]
			New Thread : Thread[Two,5,main]
			One : 15
			Two : 15
			Two : 14
			One : 14
			One : 13
			Two : 13
			One : 12
			Two : 12
			One : 11
			Two : 11
			One : 10
			Two : 10
			Suspend thread One.
			Two : 9
			Two : 8
			Two : 7
			Two : 6
			One : 9
			Two : 5
			Resume thread One.
			Suspend thread Two.
			One : 8
			One : 7
			One : 6
			One : 5
			One : 4
			Resume thread Two.
			Waiting for threads to finish.
			Two : 4
			One : 3
			Two : 3
			One : 2
			Two : 2
			One : 1
			Two : 1
			One exit.
			Two exit.
			Main Thread exit.
*/
}

