package leetcode.problem.assign_cookies;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/assign-cookies/
 * @author Yankj
 *
 */
public class AssignCookies {

	public static void main(String[] args) {
		int[] g = new int[] {1,2,3};
		int[] s = new int[] {1,1};
		Solution solution = new Solution();
		System.out.println(solution.findContentChildren(g, s));

	}

}

class Solution {
    public int findContentChildren(int[] g, int[] s) {
    	// 先排序胃口和饼干
    	// 胃口从大到小挨个拿，从s中从大到小拿能匹配到的
    	// 标记拿到的s的下标
    	
    	// 胃口最大的拿大的饼干
    	
    	// 排序胃口
    	Arrays.sort(g);
    	// 排序饼干
    	Arrays.sort(s);
    	
    	int count = 0;

    	int si = s.length-1;
    	for(int i=g.length-1;i>=0;i--) {
    		for(int j=si;j>=0;j--) {
    			if(g[i] <= s[j]) {
    				count++;
    				si = j-1;
    				break;
    			}
    		}
    	}
    	
    	return count;
    }
}