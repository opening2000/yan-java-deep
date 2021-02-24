package leetcode.lru.demo2;

import java.util.HashMap;
import java.util.Map;

public class MyLruCache<K, V> implements MyLruInterface<K, V>{

	public Map<K, DataNode<K, V>> cache = new HashMap<K, DataNode<K, V>>();
	
    private int count = 0;
    private int capacity  = 5;
    
    private DataNode<K, V> head;
    
    private DataNode<K, V> tail;
    
    public MyLruCache(int capacity){
    	this.count = 0;
    	this.capacity = capacity;
    	
    	head = new DataNode<K, V>();
    	tail = new DataNode<K, V>();
    	
    	head.next = tail;
    	head.pre = null;
    	
    	tail.pre = head;
    	tail.next = null;
    }
    
	@Override
	public V get(K k) {
		DataNode<K, V> node = cache.get(k);
		if(node == null) {
			return null;
		}else {
			this.moveToHead(node);
			return node.value;
		}
	}
	@Override
	public void set(K k, V v) {
		// 先判断k是否存在
		DataNode<K, V> node = cache.get(k);
		if(node == null) {
			// 需要判断lru容量是否满了
			count++;
			if(count > capacity) {
				DataNode<K, V> removedNode = this.popTail();
				cache.remove(removedNode.key);
				count--;
			}
			node = new DataNode<K, V>();
			node.key = k;
			node.value = v;
			
			this.addNode(node);
			cache.put(k, node);
			
		}else {
			node.key = k;
			node.value = v;
			this.moveToHead(node);
		}
		
	}
	@Override
	public void moveToHead(DataNode<K, V> node) {
		// 先把node从前后摘出来
		this.removeNode(node);
		
		// 把node加移动到头部
		this.addNode(node);
	}
	
	@Override
	public DataNode<K, V> popTail() {
		DataNode<K, V> removedNode = tail.pre;
		removedNode.pre.next = tail;
		tail.pre = removedNode.pre;
		
		return removedNode;
	}

	/**
	 * 添加节点并移动pre和post指针添加到头
	 */
	public void addNode(DataNode<K, V> node) {
		// 把node加移动到头部
		DataNode<K, V> firstNode = head.next;
		head.next = node;
		node.pre = head;
		node.next = firstNode;
		firstNode.pre = node;	
	}
	@Override
	public void removeNode(DataNode<K, V> node) {
		DataNode<K, V> preOld = node.pre;
		DataNode<K, V> nextOld = node.next;
		
		preOld.next = nextOld;
		nextOld.pre = preOld;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		
		DataNode<K, V> nextNode = head.next;
		while(nextNode != null) {
			
			builder.append(nextNode.value);
			
			nextNode = nextNode.next;
			if(nextNode == tail) {
				break;
			}
			if(nextNode != null) {
				builder.append(",");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	
}
