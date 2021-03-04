package leetcode.problem.q142_linked_list_cycle_ii;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author Yankj
 *
 */
public class Solution {

	public static void main(String[] args) {
		int[] ary = {-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5};
		int pos = 24;
		ListNode head = generateLinkList(ary, pos);
		
		System.out.println(new Solution().detectCycle(head));
	}

	/**
	 * 生成链表
	 * @param ary
	 * @param pos
	 * @return
	 */
	public static ListNode generateLinkList(int[] ary, int pos) {
		ListNode head = null;
		ListNode tail = null;
		ListNode posNode = null;
		
		if(ary != null && ary.length > 0) {
			
			ListNode pre = null;
			for(int i=0;i<ary.length;i++) {
				ListNode current = new ListNode(ary[i]);
				if(i == 0) {
					head = current;
				}else {
					pre.next = current;
				}
				
				if(i == pos) {
					posNode = current;
				}
				
				pre = current;
				
				if(i == ary.length-1) {
					tail = current;
					tail.next = posNode;
				}
			}
		}
		
		return head;
	}
	
    public ListNode detectCycle(ListNode head) {
    	
    	// 要点1： slow和fast最开始其实的位置一定要是同一个位置，
    	// 否则第二次快指针按照满指针速度从head开始移动时，他们因为距离相差1的原因会永远相遇不了
    	ListNode slow = head;
    	ListNode fast = head;
    	
    	if(head == null || head.next == null) {
    		return null;
    	}
    	
    	
    	boolean isCycle = false;
    	while(fast != null && fast.next != null) {
    		// 要点2：判断是否闭环的while中，需要先移动快慢指针再判断两个指针是否相等，否则会在head直接相遇
    		slow = slow.next;
    		fast = fast.next.next;
    		if(fast == slow) {
    			isCycle = true;
    			
    			// 快指针按照慢指针的速度从头开始走
    			fast = head;
    			//slow = slow.next;
    			break;
    		}
    		
    	}
    	
    	while(isCycle && slow != null) {
    		if(fast == slow) {
    			return slow;
    		}
    		slow = slow.next;
    		fast = fast.next;
    	}
    	
    	return null;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}