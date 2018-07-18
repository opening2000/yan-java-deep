package cache.distributedcache.server;

import java.util.ArrayList;
import java.util.List;

/**
 * CacheServer需要是个单例
 * @author Yan
 *
 */
public class CacheServer {

	private static CacheServer cacheServer = new CacheServer();
	
	public static CacheServer getInstance(){
		
		return cacheServer;
	}
	
	private List<CacheMgrThread> cacheMgrThreads = new ArrayList<CacheMgrThread>(3);
	private List<Boolean> threadStatus = new ArrayList<Boolean>(3);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("CacheServer开始启动...");
		System.out.println("CacheServer启动完成...");
		
		CacheServer cacheServer = CacheServer.getInstance();
		cacheServer.work();
		
		System.out.println("CacheServer停止服务...");
	}
	
	private void work(){
		//启动多个缓存线程
		
		while(!isThreadAllDead()){
			
		}
	}
	
	/**
	 * 判断线程是否都已经挂掉了
	 * 如果线程都挂掉了，返回true，如果还有存活的，返回false
	 * @return
	 */
	public boolean isThreadAllDead(){
		for(Boolean flag : threadStatus){
			if(flag){
				return false;
			}
		}
//		for(CacheMgrThread thread : cacheMgrThreads){
//			if(thread.){
//				
//			}
//		}
		return true;
	}
}
