package multithread.demo2;
/**

Start-----
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
    at java.lang.Object.wait(Native Method)
    at com.paddx.test.concurrent.WaitTest.testWait(WaitTest.java:8)
    at com.paddx.test.concurrent.WaitTest$1.run(WaitTest.java:20)
    at java.lang.Thread.run(Thread.java:745)

这段程序并没有按我们的预期输出相应结果，而是抛出了一个异常。

Thrown to indicate that a thread has attempted to wait on an object's monitor or to notify other threads waiting on an object's monitor without owning the specified monitor.
这句话的意思大概就是：线程试图等待对象的监视器或者试图通知其他正在等待对象监视器的线程，但本身没有对应的监视器的所有权。
其实这个问题在《【68期】面试官：对并发熟悉吗？说说Synchronized及实现原理》一文中有提到过，wait方法是一个本地方法，其底层是通过一个叫做监视器锁的对象来完成的。所以上面之所以会抛出异常，是因为在调用wait方式时没有获取到monitor对象的所有权，那如何获取monitor对象所有权？
Java中只能通过Synchronized关键字来完成

 */
public class WaitTest {

    public synchronized void testWait(){//增加Synchronized关键字
        System.out.println("Start-----");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End-------");
    }

    public static void main(String[] args) {
        final WaitTest test = new WaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.testWait();
            }
        }).start();
    }
}