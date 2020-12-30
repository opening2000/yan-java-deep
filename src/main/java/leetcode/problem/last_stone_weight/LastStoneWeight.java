package leetcode.problem.last_stone_weight;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
* https://leetcode-cn.com/problems/last-stone-weight/
* 优先队列 https://www.w3cschool.cn/java/java-priority-queues.html
*/
public class LastStoneWeight {

	public static void main(String[] args) {
		int[] stones = new int[] {2,7,4,1,8,1};
		System.out.println(new Solution2().lastStoneWeight(stones));
	}

}


class Solution {
    /**
    * 被粉碎的是x的2倍，理解的是x越大粉碎的总量越大,从大到小开始粉碎
    **/
    public int lastStoneWeight(int[] stones) {
        // 剩余的重量
    	int count = stones.length;
        while(count > 1) {
        	if(count  == stones.length) {
        		Arrays.sort(stones);
        	}else {
        		Arrays.sort(stones, 0, count);
        	}
        	int y = stones[count-1];
        	int x = stones[count-2];
        	
        	if(y-x>0) {
        		stones[count-2] = y-x;
        		stones[count-1] = 0;
        		count = count - 1;
        	}else {
        		stones[count-2] = 0;
        		stones[count-1] = 0;
        		count = count - 2;
        	}
        }
    	return stones[0];
    }
}


class Solution2 {

	/**
    * 优先级队列
    **/
    public int lastStoneWeight(int[] stones) {
        
    	PriorityQueue<Integer> stoneQueue = new PriorityQueue<Integer>((a, b) -> (b-a));
    	for(int i=0;i<stones.length;i++) {
    		stoneQueue.offer(stones[i]);
    	}
    	
    	while(stoneQueue.size() > 1) {
    		int y = stoneQueue.poll();
    		int x = stoneQueue.poll();
    		
    		if(y-x > 0) {
    			stoneQueue.offer(y-x);
    		}
    	}
    	return stoneQueue.size() == 0 ? 0 : stoneQueue.poll();
    }
}

