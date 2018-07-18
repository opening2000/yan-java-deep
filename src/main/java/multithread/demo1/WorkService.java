package multithread.demo1;



public class WorkService {
	
	public static final int NUM = 1000000000;

	public long caculateA(long x , long y){
		long a = 0;
		for(int i=0;i<NUM;i++){
			a += x+y;
		}
		return a;
	}
	
	public long caculateB(long x , long z){
		long b = 0;
		for(int i=0;i<NUM;i++){
			b += x + z;
		}
		return b;
	}
}
