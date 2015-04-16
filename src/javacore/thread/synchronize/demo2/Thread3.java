package javacore.thread.synchronize.demo2;

public class Thread3 {
	class Inner { 
		private void m4t1() { 
			int i = 5; 
			while(i-- > 0) { 
				System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i); 
				try { 
					Thread.sleep(500); 
				} catch(InterruptedException ie) { 
				} 
			} 
		} 
		
		private void m4t2() { 
			int i = 5; 
			while(i-- > 0) { 
				System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i); 
				try { 
					Thread.sleep(500); 
				} catch(InterruptedException ie) { 
				} 
			} 
		} 
	} 
	
	private void m4t1(Inner inner) { 
		synchronized(inner) { //使用对象锁 
			inner.m4t1();
		}
	} 

	private void m4t2(Inner inner) { 
//		synchronized(inner) { //使用对象锁 
			inner.m4t2(); 
//		}
	} 
	
	public static void main(String[] args) { 
		final Thread3 myt3 = new Thread3(); 
		final Inner inner = myt3.new Inner(); 
		Thread t1 = new Thread(
				new Runnable() {
					public void run() { 
						myt3.m4t1(inner);
					} 
				}, "t1"); 
		Thread t2 = new Thread( 
				new Runnable() {
					public void run() { 
						myt3.m4t2(inner);
						} 
					}, "t2"); 
		t1.start(); 
		t2.start(); 
		
		/**
		 * 其他对象锁
		 * 
		 * 尽管线程t1获得了对Inner的对象锁，
		 * 但由于线程t2访问的是同一个Inner中的非同步部分。所以两个线程互不干扰。
		 * 
		 * 现在在Inner.m4t2()前面加上synchronized
		 * 尽管线程t1与t2访问了同一个Inner对象中两个毫不相关的部分,但因为t1先获得了对Inner的对象锁，
		 * 所以t2对Inner.m4t2()的访问也被阻塞，因为m4t2()是Inner中的一个同步方法。
		 * （yan注：正因为是同一个inner对象，不同线程访问才会被锁住，
		 * 因为同步的是对象，如果是不同对象的话，是不会锁住的）
		 * 
		 */
	} 
}
