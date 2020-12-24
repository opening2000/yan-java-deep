package leetcode.problem.candy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/candy/solution/
 * @author Yankj
 *
 */
public class Candy {

	public static void main(String[] args) {
		//[1,0,2]
		//[1,2,2]
		//[10,10,10,10,10,10]
		int[] ratings = new int[] {10,10,10,10,10,10};
		Solution solution = new Solution();
		System.out.println(solution.candy(ratings));
	}
}

class Solution {
    public int candy(int[] ratings) {
    	
    	// 寻找局部最低点
    	// 先制作一个相邻元素不重复的队列
    	List<Integer> indexList = new ArrayList<Integer>();
    	indexList.add(0);
    	for(int i=1;i<ratings.length;i++) {
    		int len = indexList.size();
    		int lastValIndex = indexList.get(len-1);
    		if(ratings[i] != ratings[lastValIndex]) {
    			indexList.add(i);
    		}
    	}
    	
    	if(indexList.size() > 1) {
    		List<Integer> minSlopIndexList = new ArrayList<Integer>();
    		
    		for(int i=0;i<indexList.size();i++) {
    			int index = indexList.get(i);
    			int lastValIndex = 0;
    			int nextValIndex = 0;
    			
    			
    			if(i == 0) {
    				nextValIndex = indexList.get(i+1);
    				if(ratings[index] < ratings[nextValIndex]) {
    					minSlopIndexList.add(index);
    				}
    			}else if(i > 0 && i < indexList.size() - 1) {
    				lastValIndex = indexList.get(i-1);
    				nextValIndex = indexList.get(i+1);
    				
    				if(ratings[lastValIndex] > ratings[index] && ratings[index] < ratings[nextValIndex]) {
    					minSlopIndexList.add(index);
    				}
    			}else {
    				lastValIndex = indexList.get(i-1);
    				if(ratings[lastValIndex] > ratings[index]) {
    					minSlopIndexList.add(index);
    				}
    			}
    		}
    		
    		int[] candies = new int[ratings.length];
    		for(int i=0;i<minSlopIndexList.size();i++) {
    			int index = minSlopIndexList.get(i);
    			
    			int leftIndex = 0;
    			int rightIndex = ratings.length - 1;
    			if(i>0) {
    				leftIndex = minSlopIndexList.get(i-1);
    			}
    			if(i<minSlopIndexList.size()-1) {
    				rightIndex = minSlopIndexList.get(i+1);
    			}
    			
    			int count = 1;
    			for(int j=index;j<=rightIndex;j++) {
    				if(candies[j] < count) {
    					candies[j] = count;
    				}
    				if(j < (ratings.length - 1) && ratings[j] == ratings[j+1]) {
    					count = 1;
    				}else {
    					count++;
    				}
    				
    				if(j==ratings.length - 1) {
    					// 如果已经判断到ratings[]数组的最右边，则最右边不会有比ratings[j]小的值了
    				}else {
    					
    					// 找到一个局部最高点
    					if(ratings[j] > ratings[j+1]) {
    						break;
    					}
    				}
    			}
    			
    			count = 1;
    			for(int j=index;j>=leftIndex;j--) {
    				if(candies[j] < count) {
    					candies[j] = count;
    				}
    				if(j > 0 && ratings[j] == ratings[j-1]) {
    					count = 1;
    				}else {
    					count++;
    				}
    				
    				if(j==0) {
    					// 如果已经判断到ratings[]数组的最左边，则最坐边不会有比ratings[j]小的值了
    				}else {
    					// 找到一个局部最高点
    					if(ratings[j] > ratings[j-1]) {
    						break;
    					}
    				}
    			}
    		}
    		int sum = 0;
    		for(int i=0;i<candies.length;i++) {
    			sum+=candies[i];
    		}
    		
    		return sum;
    		
    	}else {
    		return ratings.length;
    	}
    }
}
