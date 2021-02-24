package leetcode.lru.demo2;

public class DataNode<K, V> {

	public K key;
	
	public V value;
	
	// 上一个节点
	public DataNode pre;
	
	// 下一个节点
	public DataNode next;
	
}
