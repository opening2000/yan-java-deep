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
public class QueueTest {
    public static void main(String[] args) {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("poll="+queue.poll()); //返回第一个元素，并在队列中删除
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element="+queue.element()); //返回第一个元素 
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek="+queue.peek()); //返回第一个元素 
        for(String q : queue){
            System.out.println(q);
        }
        
    }
}