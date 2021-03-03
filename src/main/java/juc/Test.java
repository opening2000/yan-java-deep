package juc;

public class Test {
	private static final int COUNT_BITS = Integer.SIZE - 3;
	
	private static final int RUNNING    = -1 << COUNT_BITS;  // -536870912
	
	private static final int SHUTDOWN   =  0 << COUNT_BITS;  // 0
	// 前3位是    000    SHUTDOWN   
	private static final int STOP       =  1 << COUNT_BITS;  // 536870912
	// 前3位是    001    STOP
	private static final int TIDYING    =  2 << COUNT_BITS;  // 1073741824
	// 前3位是    010    TIDYING    
	private static final int TERMINATED =  3 << COUNT_BITS;  // 1610612736
	// 前3位是    011    TERMINATED 
	
	public static void main(String[] args) {
		System.out.println(COUNT_BITS);    // 29
		System.out.println(RUNNING);       // -536870912
		System.out.println(SHUTDOWN);      // 0
		System.out.println(STOP);          // 536870912
		System.out.println(TIDYING);       // 1073741824
		System.out.println(TERMINATED);    // 1610612736
		

	}

}
