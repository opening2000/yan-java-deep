package javacore.thread.synchronize.demo1;

class Account2 {
    String name;
    float amount;
    
    
    public Account2(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * 储蓄，存款
     * @param amt
     */
    public synchronized void deposit(float amt) {
        float tmp = amount;
        tmp += amt;
        
        try {
            Thread.sleep(1);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }
        
        amount = tmp;
    }

    /**
     * 拿走，退出
     * @param amt
     */
    public synchronized void withdraw(float amt) {
        float tmp = amount;
        tmp -= amt;

        try {
            Thread.sleep(1);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }        

        amount = tmp;
    }

    /**
     * 结平账目
     * @return
     */
    public float getBalance() {
        return amount;
    }
}



public class AccountTest2{
    private static int NUM_OF_THREAD = 1000;
    static Thread[] threads = new Thread[NUM_OF_THREAD];
    
    public static void main(String[] args){
        final Account2 acc = new Account2("John", 1000.0f);
        for (int i = 0; i< NUM_OF_THREAD; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                        acc.deposit(100.0f);
                        acc.withdraw(100.0f);
                }
            });
            threads[i].start();
        }

        for (int i=0; i<NUM_OF_THREAD; i++){
            try {
                threads[i].join(); //等待所有线程运行结束
            } catch (InterruptedException e) {
                // ignore
            }
        }
        System.out.println("Finally, John's balance is:" + acc.getBalance());
    }

}