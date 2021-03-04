package leetcode.problem.q141_linked_list_cycle;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author Yankj
 *
 */
public class Solution {

	public static void main(String[] args) {
		int[] ary = {3,2,0,-4};
		int pos = 1;
		ListNode head = generateLinkList(ary, pos);
		
		System.out.println(new Solution().hasCycle(head));
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
	
	public boolean hasCycle(ListNode head) {
		ListNode slow = head;
		
		if(slow == null) {
			return false;
		}
		ListNode fast = head.next;
		
		boolean hasCycle = false;
		while (fast != null && fast.next != null) {
			if(slow == fast){
				hasCycle = true;
				break;
			}
			
			slow = slow.next;
			fast = fast.next.next;
		}
		
        return hasCycle;
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

