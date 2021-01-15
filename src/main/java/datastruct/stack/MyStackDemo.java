package datastruct.stack;

public class MyStackDemo {

	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack();
		for(int i=0;i<35;i++) {
			stack.push(i);
		}
		
		while(stack.size() > 0) {
			int val = stack.pop();
			System.out.println(val);
		}
	}
}


class MyStack<T>{

	Object[] theArray;
	int topOfStack = -1;
	
	// 初始容量
	private static final int INITIAL_CAPACITY = 10;

	// 栈目前的容量
	int capacity = INITIAL_CAPACITY;
	
	public MyStack() {
		this.theArray = new Object[capacity];
	}
	
	/**
	 * 移除并返回栈顶元素
	 * @return
	 */
	public T pop() {
		T t = (T)theArray[topOfStack];
		theArray[topOfStack] = null;
		topOfStack--;
		
		// 如果栈缩减到容量的1/4，则进行缩容
		if(topOfStack <= capacity / 4 && capacity > INITIAL_CAPACITY) {
			int newCapacity = capacity / 2;
			if(newCapacity <= INITIAL_CAPACITY) {
				newCapacity = INITIAL_CAPACITY;
			}
			this.resize(newCapacity);
		}
		
		return t;
	}
	
	/**
	 * 返回栈顶元素，但不移除
	 * @return
	 */
	public T peek() {
		return (T)theArray[topOfStack];
	}

	public void push(T t) {
		topOfStack++;
		
		// 如果栈已经达到容量3/4则进行扩容
		if(topOfStack >= capacity * 3 / 4) {
			int newCapacity = 2 * capacity;
			this.resize(newCapacity);
		}
		
		theArray[topOfStack] = t;
	}
	
	/**
	 * 返回栈中元素的个数
	 * @return
	 */
	public int size() {
		return this.topOfStack;
	}
	
	/**
	 * 对栈进行扩容或者缩容
	 * @param newSize 新的容量
	 */
	private void resize(int newCapacity) {
		// resize是不需要调整topOfStack的，只需要调整数组，调整capacity
		// 扩容
		Object[] newArray = new Object[newCapacity];
		// 拷贝
		for(int i=0;i<=topOfStack;i++) {
			newArray[i] = this.theArray[i];
		}
		this.theArray = newArray;
		capacity = newCapacity;
	}
	
}