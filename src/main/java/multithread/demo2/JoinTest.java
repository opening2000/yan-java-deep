package multithread.demo2;

import designpattern.producer_consumer.demo1.Test;

public class JoinTest implements Runnable{
    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " start-----");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            Thread test = new Thread(new JoinTest());
            test.start();
            
            try {
				test.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("Finished~~~");
    }
}