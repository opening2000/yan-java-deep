package leetcode.problem.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

	public static void main(String[] args) {
		//{-1, 0, 1, 2, -1, -4}
		//{0, 0, 0}
		//[3,0,-2,-1,1,2]
		int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
		System.out.println(new Solution().threeSum(nums));
	}

}


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> list = new ArrayList<>();
    	if(nums.length<3) {
    		return list;
    	}
    	
    	int leftIndex = 0;
    	int rightIndex = 0;
    	Arrays.sort(nums);
    	
    	Set<String> numsSet = new HashSet<String>();
    	
    	for(int i=1;i<nums.length-1;i++) {
    		leftIndex = i-1;
    		rightIndex = i+1;
    		
    		while(leftIndex >= 0 && rightIndex < nums.length) {
    			
    			if((nums[leftIndex]+nums[i]+nums[rightIndex]) > 0) {
					leftIndex--;
				}else if((nums[leftIndex]+nums[i]+nums[rightIndex]) < 0) {
					rightIndex++;
				}else if((nums[leftIndex]+nums[i]+nums[rightIndex]) == 0) {
					String key = nums[leftIndex] + "," + nums[i] + "," + nums[rightIndex];
					
					if(!numsSet.contains(key)) {
						List<Integer> subList = new ArrayList<Integer>();
						subList.add(nums[leftIndex]);
						subList.add(nums[i]);
						subList.add(nums[rightIndex]);
						list.add(subList);
						numsSet.add(key);
					}
					// 找到一对之后，不能break
					// 避免-2,-1,0,1,2的情况
					leftIndex--;
					rightIndex++;
				}
    		}
    	}
    	
    	return list;
    }
}