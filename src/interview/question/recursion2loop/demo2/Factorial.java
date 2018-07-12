package interview.question.recursion2loop.demo2;

import java.util.Stack;

/**
 * 使用循环和递归求阶乘
 * 
 * 虽然这个例子中使用循环和使用循环加栈的方式看上去很类似，但实际上是使用栈存储了中间值，
 * 当前例子中中间值可能比较简单，案例比较复杂的时候栈的方式和单纯循环可能就显现出差异了
 * 
 */
public class Factorial {

	public static void main(String[] args) {
		System.out.println(recurisionFactorial(5));
		System.out.println(loopAndStackFactorial(5));
		System.out.println(loopFactorial(5));

	}
	
	/**
	 * 使用递归的方式去求阶乘
	 * 
	 * @param n
	 * @return
	 */
	public static int recurisionFactorial(int n){
		
		if(n == 1){
			return 1;
		}else{
			return n * recurisionFactorial(n - 1);
		}
	}

	/**
	 * 使用循环加栈的方式求阶乘，代替递归的方式
	 * 
	 * @param n
	 * @return
	 */
	public static int loopAndStackFactorial(int n){
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(n);
		
		int result = 1;
		
		while (!stack.empty()) {
			int currentValue = stack.pop();
			result = result * currentValue;
			
			if(currentValue > 1){
				stack.push(currentValue - 1);
			}
		}
		
		return result;
	}
	
	/**
	 * 使用循环的方式求阶乘
	 * 
	 * @param n
	 * @return
	 */
	public static int loopFactorial(int n){
		int result = 1;
		
		while (n > 1) {
			result *= n;
			
			n = n - 1;
		}
		
		return result;
	}
	
}
