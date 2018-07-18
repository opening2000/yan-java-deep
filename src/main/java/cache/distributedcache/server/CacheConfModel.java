package cache.distributedcache.server;

public class CacheConfModel implements java.io.Serializable {
	/**
	 * 缓存开始生效的时间
	 */
	private long beginTime;
	private boolean isForever = false;
	
	/**
	 *  缓存失效的时间（单位为分钟）
	 */
	private int durableTime;

	public CacheConfModel(){
		
	}
	
	public CacheConfModel(int durableTime){
		this.beginTime = System.currentTimeMillis();
		this.durableTime = durableTime;
	}
	
	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public boolean isForever() {
		return isForever;
	}

	public void setForever(boolean isForever) {
		this.isForever = isForever;
	}

	public int getDurableTime() {
		return durableTime;
	}

	public void setDurableTime(int durableTime) {
		this.durableTime = durableTime;
	}
}
