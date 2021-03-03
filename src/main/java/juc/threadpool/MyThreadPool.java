package juc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 手写线程池
 * TODO 未完成
 * @author Yankj
 *
 */
public class MyThreadPool {

	private int minSize = 10;
	
	private int maxSize = 20;
	
	private MyTask[] threads;
	
	BlockingQueue<MyTaskAble> queue = new LinkedBlockingQueue<MyTaskAble>();
	
	public MyThreadPool() {
		threads = new MyTask[minSize];
	}
	public void execute() {
		for(int i=0;i<minSize;i++) {
			MyTaskAble myTaskAble = queue.poll();
			threads[i] = new MyTask(myTaskAble);
			threads[i].start();
		}
	}
	
	public MyThreadPool(int minSize, int maxSize) {
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
	
	public void exec() {
		
	}
	
	public static void main(String[] args) {
		
	}
}

class MyTask extends Thread{

	// 用来标记和控制这个线程是否应该结束
	boolean runing = true;
	
	MyTaskAble myTaskAble;

	MyTaskAble nextTaskAble;

	public MyTask(MyTaskAble myTaskAble) {
		this.myTaskAble = myTaskAble;
	}
	
	/**
	 * 当置为false表示这个线程应该结束
	 * @param runing
	 */
	public synchronized void setRuning(boolean runing) {
		this.runing = runing;
	}
	
	@Override
	public void run() {
		while(runing) {
			if(myTaskAble != null) {
				myTaskAble.doTask();
				// 执行完成后
				myTaskAble = null;
				// TODO 需要让管理器来添加任务
				
			}else {
				// 如果队列为空的话，等待x时间
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}

interface MyTaskAble{
	void doTask();
}
