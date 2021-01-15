package datastruct.stack;

import java.util.Stack;

public class PostfixStackDemo {

	public static void main(String[] args) {
		String str = "a+b*c+(d*e+f)*g";
		// abc*+de*f+g*+
		
		// 操作符栈
		Stack<Character> operatorStack = new Stack<Character>();
		
		char[] charAry = str.toCharArray();
		
		// 操作数立即输出。操作符不立即输出，先存放在某个地方。
		// 正确的做法是将已经见到过但是还未输出的操作符放入栈中。当遇到左圆括号时我们也要将其推入栈中。
		// 如果见到一个右括号，那么就将栈元素弹出，将弹出的符号写出直到遇到一个（对应的）左括号，但是这个左括号只被弹出，并不输出
		// 如果我们见到任何其他的符号+*(，那么我们从栈中弹出栈元素直到发现优先级更低的元素为止。
		// 有一个例外：除非是在处理一个)时，否则我们决不从栈中移走(。对于这种操作，+的优先级最低，而(的优先级最高。
		// 当从栈弹出元素的工作做完后，我们再将操作符压入栈中
		
		// 这个算法的想法是，当看到一个操作符的时候，把它放到栈中。栈代表挂起的操作符。
		// 然而，栈中有些具有高优先级的操作符现在知道当他们不再被挂起时要完成使用，应该被弹出。
		// 这样，在把当前操作符放入栈中之前，那些在栈中并在当前操作符之前要完成使用的操作符被弹出。
		// 圆括号增加了额外的复杂因素。当左括号是一个输入符号时我们可以把它看成是一个高优先级的操作符
		// （使得挂起的操作符仍然是挂起的）,而当它在栈中时把它看成是低优先级的操作符（从而不会被操作符意外地删除）。
		// 右括号被处理成特殊的情况。
		for(char ch:charAry) {
			// 如果是操作符压入栈中，如果是数字输出
			if(isOperator(ch)) {
				if(operatorStack.isEmpty()) {
					operatorStack.push(ch);
				}else {
					if(ch == ')') {
						// 有一个例外：除非是在处理一个)时，否则我们决不从栈中移走(。对于这种操作，+的优先级最低，而(的优先级最高。
						while(!operatorStack.isEmpty()) {
							char topChar = operatorStack.pop();
							if(topChar == '(') {
								break;
							}
							System.out.print(topChar);
						}
						
					}else {
						
						while(!operatorStack.isEmpty()) {
							char topChar = operatorStack.peek();
							// 栈顶元素是(，应该将输入操作符压入栈中
							if(topChar == '(') {
								break;
							}else {
								// 如果栈顶元素优先级高于输入操作符，则将栈中元素弹出，直到遇到优先级更低的运算符
								if(operatorPriority(topChar, ch) < 0) {
									break;
								}else {
									topChar = operatorStack.pop();
									System.out.print(topChar);
								}
							}
						}
						operatorStack.push(ch);
					}
				}
			}else {
				System.out.print(ch);
			}
		}
		
		while(operatorStack.size() > 0) {
			char topChar = operatorStack.pop();
			System.out.print(topChar);
		}
	}
	
	/**
	 * 判断是否为操作符
	 * @param a
	 * @return
	 */
	public static boolean isOperator(char a) {
		if(a == '+' || a == '-' || a == '*' || a == '/' || a == '(' || a == ')') {
			return true;
		}
		return false;
	}
	
	/**
	 * 比较操作符优先级
	 * @param a 栈中操作符
	 * @param b 输入操作符
	 * @return
	 */
	public static int operatorPriority(char a, char b) {
		// 这个算法的想法是，当看到一个操作符的时候，把它放到栈中。栈代表挂起的操作符。
		// 然而，栈中有些具有高优先级的操作符现在知道当他们不再被挂起时要完成使用，应该被弹出。
		// 这样，在把当前操作符放入栈中之前，那些在栈中并在当前操作符之前要完成使用的操作符被弹出。
		// 圆括号增加了额外的复杂因素。当左括号是一个输入符号时我们可以把它看成是一个高优先级的操作符
		// （使得挂起的操作符仍然是挂起的）,而当它在栈中时把它看成是低优先级的操作符（从而不会被操作符意外地删除）。
		// 右括号被处理成特殊的情况。
		
		if(b == '(') {
			return -1;
		}else if(b == ')') {
			return 1;
		}else if(b == '*' || b == '/') {
			if(a == '+' || a == '-') {
				return -1;
			}else if(a == '*' || a == '/') {
				return 0;
			}else {
				return -1;
			}
		}else if(b == '+' || b == '-') {
			if(a == '+' || a == '-') {
				return 0;
			}else if(a == '*' || a == '/') {
				return 1;
			}else {
				return 1;
			}
		}else {
			throw new RuntimeException("未能处理的操作符");
		}
	}
}
