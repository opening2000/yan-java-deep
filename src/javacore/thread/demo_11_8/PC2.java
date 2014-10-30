package javacore.thread.demo_11_8;


class Q2{
	int n;
	
	boolean valueSet = false;    //��û����Ʒ��������
	
	synchronized public int get() {
		//���û����Ʒ�����������ͽ��еȴ������������ٽ�������
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
		//����Ѿ�����Ʒ����������û�б����ѣ���ô����ͣ�������ȴ����Ѻ��ٴ������������˷�
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
		 * ����������һ����Ʒ�������߶������Ʒ�����˶�����ѣ����������ٴ�����֮��Ĳ�Ʒȴ��һ���ᱻ����
		 * java��ȷ��д��Ӧ����ʹ��wati��notify������������б�ʾ
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
