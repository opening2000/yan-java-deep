package leetcode.problem.two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
* https://leetcode-cn.com/problems/two-sum/
*/
public class TwoSum {

	public static void main(String[] args) {
		int[] nums= new int[] { 2,5,5,11 };
		int target = 10;
		System.out.println(Arrays.toString(new Solution().twoSum(nums, target)));

	}

}


class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
    	
    	if(nums.length == 2 && nums[0] + nums[1] == target) {
    		result[0] = 0;
    		result[1] = 1;
    		return result;
    	}
    	for(int i=0;i<nums.length;i++) {
    		for(int j=0;j<nums.length;j++) {
    			if(i != j && nums[i] + nums[j] == target) {
    				result[0] = i;
    	    		result[1] = j;
    	    		return result;
    			}
    		}
    	}
    	
    	return result;
    }
}

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i=0;i<nums.length;i++) {
    		map.put(nums[i], i);
    		
    		if(map.containsKey(target - nums[i]) 
    				&& !map.get(target - nums[i]).equals(Integer.valueOf(i))) {
    			return new int[] {map.get(target - nums[i]),i};
    		}
    	}
    	
    	for(int i=0;i<nums.length;i++) {
    		if(map.containsKey(target - nums[i]) 
    				&& !map.get(target - nums[i]).equals(Integer.valueOf(i))) {
    			return new int[] {map.get(target - nums[i]),i};
    		}
    	}
    	
    	return new int[0];
    }
}

