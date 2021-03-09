package leetcode.problem.q1047_remove_duplicates;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 * @author Yankj
 *
 */
public class Solution {

	public static void main(String[] args) {
		String S = "abbaca";
		System.out.println(new Solution().removeDuplicates(S));
	}

	
    public String removeDuplicates(String S) {
    	char[] charAry = S.toCharArray();
    	Stack<Character> stack = new Stack<Character>();
    	
    	for(int i=0;i<charAry.length;i++) {
    		char popChar = '\0';
    		if(!stack.isEmpty()) {
    			popChar = stack.peek();
    		}
    		
    		if(popChar == charAry[i]) {
    			stack.pop();
    		}else {
    			stack.push(charAry[i]);
    		}
    		
    	}
    	char[] target = new char[stack.size()];
    	if(!stack.isEmpty()) {
    		for(int i=stack.size()-1;i>=0;i--) {
    			target[i] = stack.pop();
    		}
    	}
    	return new String(target);
    }
    
    
    public String removeDuplicates2(String S) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }

}
