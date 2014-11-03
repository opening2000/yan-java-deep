package javacore.thread.demo_11_9;


class NewThread implements Runnable{

	String name;
	Thread t;
	
	public NewThread(String threadName) {
		name = threadName;
		t = new Thread(this , name);
		System.out.println("New Thread : " + t);
		t.start();
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 15 ; i++){
				System.out.println("name : " + i);
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			System.out.println(name + " interrupted.");
		}
		System.out.println(name + " exiting.");
	}
	
}

public class SuspendResume {

	public static void main(String[] args) {
		NewThread ob1 = new NewThread("One");
		NewThread ob2 = new NewThread("Two");
		
		try {
			Thread.sleep(1000);
			ob1.t.suspend();
			System.out.println("Suspend thread One.");
			Thread.sleep(1000);
			ob1.t.resume();
			System.out.println("Resume thread One.");
			ob2.t.suspend();
			System.out.println("Suspend thread Two.");
			Thread.sleep(1000);
			ob2.t.resume();
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
		System.out.println("Main Thread exiting.");
	}
			/*  result:
			New Thread : Thread[One,5,main]
			New Thread : Thread[Two,5,main]
			name : 0
			name : 0
			name : 1
			name : 1
			name : 2
			name : 2
			name : 3
			name : 3
			name : 4
			name : 4
			name : 5
			name : 5
			Suspend thread One.
			name : 6
			name : 7
			name : 8
			name : 9
			name : 10
			name : 6
			Resume thread One.
			Suspend thread Two.
			name : 7
			name : 8
			name : 9
			name : 10
			Resume thread Two.
			name : 11
			name : 11
			Waiting for threads to finish.
			name : 12
			name : 12
			name : 13
			name : 13
			name : 14
			name : 14
			Two exiting.
			One exiting.
			Main Thread exiting.
			*/
}
