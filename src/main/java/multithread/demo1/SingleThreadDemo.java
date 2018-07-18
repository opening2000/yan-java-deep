package multithread.demo1;


public class SingleThreadDemo {

	/**
	 * @param args
	 */
	public long work(long x , long y , long z) {
		WorkService ws = new WorkService();
		
		long a = 0L;
		long b = 0L;
		long c = 0L;

		a = ws.caculateA(x, y);
		b = ws.caculateB(x, z);
		
		c = a + b;
		return c;
	}

}
