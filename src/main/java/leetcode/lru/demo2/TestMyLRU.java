package leetcode.lru.demo2;

import java.util.Random;

public class TestMyLRU {

	public static void main(String[] args) {
		MyLruCache<Integer, Integer> lruCache = new MyLruCache<Integer, Integer>(5);
		Random random = new Random();
		for(int i=0;i<15;i++) {
			int v = random.nextInt(20);
			lruCache.set(v, v);
			System.out.println("set:" + v);
			System.out.println(lruCache.toString());
			
			v = random.nextInt(20);
			lruCache.get(v);
			System.out.println("get:" + v);
			System.out.println(lruCache.toString());
			
		}
	}

}
