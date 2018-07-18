package interview.question.recursion2loop.demo1;

import java.util.Stack;

/**
 * 计算1到n的和
 * 要求：
 * 1、使用递归实现
 * 2、使用循环加栈实现
 * 
 * @author Yan
 *
 */
public class RecursionToLoopDemo1 {

	public static void main(String[] args) {
		int n = 100;
		System.out.println(testRecursive(n));
		System.out.println(testRecursiveToLoop(n));
		
	}

	/**
	 * 使用递归实现1到n的和
	 * @param n
	 * @return
	 */
	public static int testRecursive(int n){
		int result = 0;
		if(n == 1){
			result = 1;
		}else{
			result = n + testRecursive(n - 1);
		}
		
		return result;
	}
	
	/**
	 * 使用栈加循环实现1到n的和
	 * 
	 * @param n
	 * @return
	 */
	public static int testRecursiveToLoop(int n){
		int result = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=n;i>=1;i--){
			stack.push(i);
		}
		
		while(!stack.empty()){
			result += stack.pop();
		}
		
		return result;
	}
	
}
