package javacore.thread.demo_11_5;

public class IsAliveAndJoinDemo {

	public static void main(String[] args) {
		NewThread ob1 = new NewThread("One");
		NewThread ob2 = new NewThread("Two");
		NewThread ob3 = new NewThread("Three");
		
		System.out.println("NewThread One is alive " + ob1.thread.isAlive());
		System.out.println("NewThread Two is alive " + ob2.thread.isAlive());
		System.out.println("NewThread Three is alive " + ob3.thread.isAlive());
		
		try {
			System.out.println("Waiting for threads to finish.");
			ob1.thread.join();
			ob2.thread.join();
			ob3.thread.join();
			//join 该方法等待所调用县城结束。
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		
		System.out.println("NewThread One is alive " + ob1.thread.isAlive());
		System.out.println("NewThread Two is alive " + ob2.thread.isAlive());
		System.out.println("NewThread Three is alive " + ob3.thread.isAlive());
		
		System.out.println("Main thread exiting.");
	}

}

class NewThread implements Runnable{
	String name;
	Thread thread;
	
	NewThread(String threadName){
		name = threadName;
		thread = new Thread(this, name);
		System.out.println("New Thread : " + thread);
		thread.start();
	}
	
	@Override
	public void run() {
		try {
			for(int i=5;i>0;i--){
				System.out.println(name + ":" + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + " exiting.");
	}
	
}