package juc;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {

	static ThreadLocal<String> keyLocal;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		map.get("");
		
		keyLocal.set("aaa");
	}

}

class MyThread extends Thread{
	
	ThreadLocal<String> tl1;
	ThreadLocal<Integer> tl2;
	
	@Override
	public void run() {
		tl1.set("aaa");
		tl2.set(2);
		// TODO 业务内容

	}
}