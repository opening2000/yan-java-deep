package leetcode.lru.demo2;

public interface MyLruInterface<K, V> {

	/**
	 * LRU对外提供的读取的方法
	 * @param k
	 * @return
	 */
	public V get(K k);
	
	/**
	 * LRU对外提供的写入的方法
	 * @param k
	 * @param v
	 */
	public void set(K k, V v);

	/**
	 * 将缓存移动到头部，一般用于缓存列表中一段时间没被读到的缓存被新读到时
	 * 可以由removeNode(DataNode<K, V> node)和addNode(DataNode<K, V> node)两个方法组合而成
	 * LRU维护缓存列表的方法，不对外调用
	 * @param node
	 */
	public void moveToHead(DataNode<K, V> node);
	
	/**
	 * 移除尾部的缓存，一般用于缓存列表满了但是插入新数据的时候
	 * LRU维护缓存列表的方法，不对外调用
	 * @param node
	 */
	public DataNode<K, V> popTail();
	
	/**
	 * 向缓存列表头部插入一个新的缓存
	 * LRU维护缓存列表的方法，不对外调用
	 * @param node
	 */
	public void addNode(DataNode<K, V> node);
	
	/**
	 * 移除缓存列表中的缓存
	 * LRU维护缓存列表的方法，不对外调用
	 * @param node
	 */
	public void removeNode(DataNode<K, V> node);
}
