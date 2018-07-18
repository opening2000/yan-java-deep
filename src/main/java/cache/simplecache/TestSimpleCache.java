package cache.simplecache;

public class TestSimpleCache {

	public static void main(String[] args){
		CacheMgr cm = CacheMgr.getInstance();
		cm.addCache("jim", "123456");
		cm.addCache("tom", "7890");
		
		System.out.println("jim:" + cm.getCache("jim").toString());
		System.out.println("tom:" + cm.getCache("tom").toString());
		
		cm.removeCache("tom");
		try {
			System.out.println("tom:" + cm.getCache("tom").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(90 * 1000);    //2分钟后
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("jim:" + cm.getCache("jim").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
