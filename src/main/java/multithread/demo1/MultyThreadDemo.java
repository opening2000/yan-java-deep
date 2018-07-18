package multithread.demo1;

public class MultyThreadDemo {

	private long x;
	private long y;
	private long z;
	
	private long a;
	private long b;
	
	private boolean isAEnd = false;
	private boolean isBEnd = false;
	
	public long work(long x , long y , long z){
		long c = 0L;
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		new ThreadA(this).run();
		new ThreadB(this).run();
		
		//calculation end
		while(true){
			//both A and B are end is true end
			if(isAEnd && isBEnd){
				break;
			}
		}
		
		c = a + b;
		
		return c;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return z;
	}

	public void setZ(long z) {
		this.z = z;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public boolean isAEnd() {
		return isAEnd;
	}

	public void setAEnd(boolean isAEnd) {
		this.isAEnd = isAEnd;
	}

	public boolean isBEnd() {
		return isBEnd;
	}

	public void setBEnd(boolean isBEnd) {
		this.isBEnd = isBEnd;
	}
	
}


class ThreadA implements Runnable{

	private MultyThreadDemo mtd;
	private WorkService ws;
	
	ThreadA(MultyThreadDemo mtd){
		this.mtd = mtd;
		ws = new WorkService();
		//��MulityThreadDemo�еı�־�ֶ�isAEnd��a�Ѿ�������Ϊfalse
		mtd.setAEnd(false);
	}
	
	public void run() {
		long a = ws.caculateA(mtd.getX(), mtd.getY());
		mtd.setA(a);
		mtd.setAEnd(true);
	}
	
}


class ThreadB implements Runnable{
	private MultyThreadDemo mtd;
	private WorkService ws;
	
	ThreadB(MultyThreadDemo mtd){
		this.mtd = mtd;
		ws = new WorkService();
		//��MulityThreadDemo�еı�־�ֶ�isBEnd��b�Ѿ�������Ϊfalse
		mtd.setBEnd(false);
	}
	
	public void run() {
		long b = ws.caculateB(mtd.getX(), mtd.getZ());
		mtd.setB(b);
		mtd.setBEnd(true);
	}
	
}
