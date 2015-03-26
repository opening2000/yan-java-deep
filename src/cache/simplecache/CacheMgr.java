package cache.simplecache;

import java.util.*;

public class CacheMgr {
	
	/**
	 * 顺便说一句，缓存的管理不是靠时间久来计算的，是靠最大不活动间隔计算的
	 */
	
	private static Map cacheMap = new HashMap();
	private static Map cacheConfMap = new HashMap();

	private CacheMgr() {

	}

	private static CacheMgr cm = null;

	public static CacheMgr getInstance() {
		if (cm == null) {
			cm = new CacheMgr();
			Thread t = new ClearCache();
			t.start();
		}
		return cm;
	}

	/**
	 * 根据key查找缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(Object key){
		return cacheMap.get(key);
	}
	
	/**
	 * 增加缓存
	 * 
	 * @param key
	 * @param value
	 * @param ccm
	 *            缓存对象
	 * @return
	 */
	public boolean addCache(Object key, Object value) {
		boolean flag = false;
		cacheMap.put(key, value);
		CacheConfModel ccm = new CacheConfModel(1); //默认缓存生效时间为1分钟
		cacheConfMap.put(key, ccm);

		System.out.println("now addcache==" + cacheMap.size());
		return true;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeCache(Object key) {
		cacheMap.remove(key);
		cacheConfMap.remove(key);

		System.out.println("now removeCache==" + cacheMap.size());

		return true;
	}

	/**
	 * 清除缓存的类
	 * 
	 * @author wanglj 继承Thread线程类
	 */
	private static class ClearCache extends Thread {
		public void run() {
			while (true) {
				Set tempSet = new HashSet();
				Set set = cacheConfMap.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					CacheConfModel ccm = (CacheConfModel) cacheConfMap.get(key);
					// 比较是否需要清除
					if (!ccm.isForever()) {
						if ((new Date().getTime() - ccm.getBeginTime()) >= ccm
								.getDurableTime() * 60 * 1000) {
							// 可以清除，先记录下来
							tempSet.add(key);
						}
					}
				}
				// 真正清除
				Iterator tempIt = tempSet.iterator();
				while (tempIt.hasNext()) {
					Object key = tempIt.next();
					cacheMap.remove(key);
					cacheConfMap.remove(key);

				}
				System.out.println("now thread================>"
						+ cacheMap.size());
				// 休息
				try {
					Thread.sleep(60 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
