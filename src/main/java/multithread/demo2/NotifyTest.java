package multithread.demo2;


/**

有了对wait方法原理的理解，notify方法和notifyAll方法就很容易理解了。既然wait方式是通过对象的monitor对象来实现的，所以只要在同一对象上去调用notify/notifyAll方法，就可以唤醒对应对象monitor上等待的线程了。
notify和notifyAll的区别在于前者只能唤醒monitor上的一个线程，对其他线程没有影响，而notifyAll则唤醒所有的线程

 */
public class NotifyTest {
    public synchronized void testWait(){
        System.out.println(Thread.currentThread().getName() +" Start-----");
        try {
            wait(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" End-------");
    }

    public static void main(String[] args) throws InterruptedException {
        final NotifyTest test = new NotifyTest();
        for(int i=0;i<5;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.testWait();
                }
            }).start();
        }

        //test.notifyAll();只能唤醒test对象上的所有线程，并不能唤醒其他对象上的线程
//        final NotifyTest test2 = new NotifyTest();
//        for(int i=0;i<5;i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    test2.testWait();
//                }
//            }).start();
//        }
        
        synchronized (test) {
            test.notify();
        }
        Thread.sleep(3000);
        System.out.println("-----------分割线-------------");

        synchronized (test) {
            test.notifyAll();
        }
    }
}