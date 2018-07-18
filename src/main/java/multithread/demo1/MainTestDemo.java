package multithread.demo1;

public class MainTestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long x = 1L;
		long y = 2L;
		long z = 4L;
		
		long c = 0L;
		
		
		long start = System.currentTimeMillis();
		SingleThreadDemo std = new SingleThreadDemo();
		c = std.work(x, y, z);
		System.out.println("c : " + c);
		long end = System.currentTimeMillis();
		System.out.println("single thread time cost:" + (end - start));
		
		start = System.currentTimeMillis();
		MultyThreadDemo mtd = new MultyThreadDemo();
		c = mtd.work(x, y, z);
		System.out.println("c : " + c);
		end = System.currentTimeMillis();
		System.out.println("multiple thread time cost:" + (end - start));
		
		

	}

}
