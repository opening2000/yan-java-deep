package leetcode.problem.palindrome_linked_list_lcci;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
 * @author Yankj
 *
 */
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public boolean isPalindrome(ListNode head) {
    	
    	if(head == null) {
    		return true;
    	}
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	ListNode current = head;
    	while(current != null) {
    		stack.push(current.val);
    		current = current.next;
    	}
    	
    	current = head;
    	while(current != null) {
    		Integer top = stack.pop();
    		if(!top.equals(current.val)) {
    			return false;
    		}
    		current = current.next;
    	}
    	
    	return true;
    }
	
    
    public boolean isPalindrome2(ListNode head) {
    	
    	if(head == null) {
    		return true;
    	}
    	
    	List<Integer> list = new ArrayList<Integer>();
    	
    	ListNode current = head;
    	while(current != null) {
    		list.add(current.val);
    		current = current.next;
    	}
    	
    	int frontIndex = 0;
    	int backIndex = list.size()-1;
    	while(frontIndex < backIndex) {
    		if(!list.get(frontIndex).equals(list.get(backIndex))) {
    			return false;
    		}
    		frontIndex++;
    		backIndex--;
    	}
    	
    	return true;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
