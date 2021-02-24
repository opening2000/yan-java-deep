package leetcode.lru.demo2;

public interface MyLruInterface<K, V> {

	public V get(K k);
	
	public void set(K k, V v);
	
	public void moveToHead(DataNode<K, V> node);
	
	public DataNode<K, V> popTail();
	
	public void addNode(DataNode<K, V> node);
	
	public void removeNode(DataNode<K, V> node);
}
