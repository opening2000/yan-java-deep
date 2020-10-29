package multithread.ThreadPool.demo2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参考 https://www.cnblogs.com/dafanjoy/p/9729358.html
 * @author Yankj
 *
 */
public class TestThreadPoolMain {

	private static ExecutorService pool;
	
	public static void main(String[] args) {

		//test1();
		//test2();
		//test3();
		//test4();
		//test5();
		test6();
	}
	
	/**
	 * 当任务队列为SynchronousQueue，创建的线程数大于maximumPoolSize时，直接执行了拒绝策略抛出异常。
	 * 
	 * 可以看到，当任务队列为SynchronousQueue，创建的线程数大于maximumPoolSize时，直接执行了拒绝策略抛出异常。
	 * 使用SynchronousQueue队列，提交的任务不会被保存，总是会马上提交执行。
	 * 如果用于执行任务的线程数量小于maximumPoolSize，则尝试创建新的进程，
	 * 如果达到maximumPoolSize设置的最大值，则根据你设置的handler执行拒绝策略。
	 * 因此这种方式你提交的任务不会被缓存起来，而是会被马上执行，在这种情况下，你需要对你程序的并发量有个准确的评估，
	 * 才能设置合适的maximumPoolSize数量，否则很容易就会执行拒绝策略；
	 */
	public static void test1() {
		/*
    	public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
		 * */
		//maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, 
									new SynchronousQueue<Runnable>(),
									Executors.defaultThreadFactory(),
									new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<3;i++) {
            pool.execute(new ThreadTask());
        }
	}
	
	/**
	 * 有界的任务队列：有界的任务队列可以使用ArrayBlockingQueue实现
	 * 
	 */
	public static void test2() {
		/*
    	public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
		 * */
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, 
									new ArrayBlockingQueue<Runnable>(10),
									Executors.defaultThreadFactory(),
									new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<3;i++) {
            pool.execute(new ThreadTask());
        }
	}
	
	
	/**
	 * 无界的任务队列：有界任务队列可以使用LinkedBlockingQueue实现
	 * 
	 */
	public static void test3() {
		/*
    	public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
		 * */
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, 
									new LinkedBlockingQueue<Runnable>(),
									Executors.defaultThreadFactory(),
									new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<3;i++) {
            pool.execute(new ThreadTask());
        }
	}
	
	/**
	 * 优先任务队列：优先任务队列通过PriorityBlockingQueue实现
	 * 
	 * 可以看到除了第一个任务直接创建线程执行外，其他的任务都被放入了优先任务队列，按优先级进行了重新排列执行，
	 * 且线程池的线程数一直为corePoolSize，也就是只有一个。
	 * 通过运行的代码我们可以看出PriorityBlockingQueue它其实是一个特殊的无界队列，
	 * 它其中无论添加了多少个任务，线程池创建的线程数也不会超过corePoolSize的数量，
	 * 只不过其他队列一般是按照先进先出的规则处理任务，而PriorityBlockingQueue队列可以自定义规则根据任务的优先级顺序先后执行。
	 * 
	 */
	public static void test4() {
		/*
    	public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
		 * */
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, 
									new PriorityBlockingQueue<Runnable>(),
									Executors.defaultThreadFactory(),
									new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<20;i++) {
            pool.execute(new ThreadTaskPriority(i));
        }
	}
	
	/**
	 * 自己扩展RejectedExecutionHandler接口，定义自己的拒绝策略
	 * 1、AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
	 * 2、CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
	 * 3、DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
	 * 4、DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
	 * 5、自定义拒绝策略
	 * */
	public static void test5() {
        //自定义拒绝策略
        pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+"执行了拒绝策略");
                
            }
        });
        for(int i=0;i<10;i++) {
            pool.execute(new ThreadTask());
        } 	
	}
	
	/**
	 * ThreadFactory自定义线程创建
	 * 线程池中线程就是通过ThreadPoolExecutor中的ThreadFactory，线程工厂创建的。
	 * 那么通过自定义ThreadFactory，可以按需要对线程池中创建的线程进行一些特殊的设置，如命名、优先级等，
	 * 下面代码我们通过ThreadFactory对线程池中创建的线程进行记录与命名
	 */
	public static void test6() {
        //自定义线程工厂
        pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
                new ThreadFactory() {
            public Thread newThread(Runnable r) {
                System.out.println("线程"+r.hashCode()+"创建");
                //线程命名
                Thread th = new Thread(r,"threadPool"+r.hashCode());
                return th;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());
          
        for(int i=0;i<10;i++) {
            pool.execute(new ThreadTask());
        }
	}
}



class ThreadTask implements Runnable{
	
	public ThreadTask() {
		
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}


class ThreadTaskPriority implements Runnable,Comparable<ThreadTaskPriority>{
    
    private int priority;
    
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadTaskPriority() {
        
    }
    
    public ThreadTaskPriority(int priority) {
        this.priority = priority;
    }

    //当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1,值越小优先级越高
    public int compareTo(ThreadTaskPriority o) {
         return  this.priority>o.priority?-1:1;
    }
    
    public void run() {
        try {
            //让线程阻塞，使后续任务进入缓存队列
            Thread.sleep(1000);
            System.out.println("priority:"+this.priority+",ThreadName:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
