package javacore.thread.demo_11_7;


class CallMe{
	//ʹ��synchronized�������Σ�����ͬ��
	synchronized void call(String msg){
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
		target.call(msg);
		
	}
	
}

public class SynchDemo {
	
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
/**
 û��ͬ��ʱ�Ľ��
[Synchronized[ World[Hello]
]
]
ͬ����Ľ��
[Hello]
[Synchronized]
[World]
 */
}
