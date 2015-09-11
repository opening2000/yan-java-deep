package datastruct.queue.demo1;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 
 * Queue使用时要尽量避免Collection的add()和remove()方法，而是要使用offer()来加入元素，
 * 使用poll()来获取并移出元素。它们的优点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。 
 * 如果要使用前端而不移出该元素，使用element()或者peek()方法。
 * 值得注意的是LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
 * 
 * @author Yan
 *
 */
public class QueueTest2 {
	
	
    public static void main(String[] args) {
    	Queue<String> queue = new LinkedList<String>();
    	ProducerThread pt = new ProducerThread(queue);
    	pt.run();
    	ConsumerThread ct = new ConsumerThread(queue);
    	ct.run();
    }
}

class ProducerThread implements Runnable{

	private Queue<String> queue;
	
	public ProducerThread(){
		
	}
	
	public ProducerThread(Queue<String> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		
		for(int i=0;i<10000;i++){
			//向队列中添加i
			String str = i + "";
			queue.offer(str);
			System.out.println("offer : " + str);
		}
		
	}
	
}

class ConsumerThread implements Runnable{

	private Queue<String> queue;
	
	public ConsumerThread(){
		
	}
	
	public ConsumerThread(Queue<String> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(!queue.isEmpty()){
			//向队列中添加i
			String str = queue.poll();
			System.out.println("poll : " + str);
		}
		
	}
	
}