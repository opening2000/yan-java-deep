package leetcode.problem.fibonacci_number;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 * @author Yankj
 *
 */
public class FibonacciNumber {

	public static void main(String[] args) {
		int n = 30;
		System.out.println(new Solution().fib(n));
		System.out.println(new Solution2().fib(n));

	}

}

/**
 * 递归
 * @author Yankj
 *
 */
class Solution {
    public int fib(int n) {
    	if(n == 0) {
    		return 0;
    	}else if(n == 1 || n == 2) {
    		return 1;
    	}else {
    		return fib(n-1) + fib(n-2);
    	}
    }
}

/**
 * 动态规划
 * @author Yankj
 *
 */
class Solution2 {
    public int fib(int n) {
    	int[] dp = new int[n+1];
    	for(int i=0;i<=n;i++) {
    		if(i == 0) {
        		dp[i] = 0;
        	}else if(i == 1 || i == 2) {
        		dp[i] = 1;
        	}else {
        		dp[i] = dp[i-1] + dp[i-2];
        	}
    	}
    	
    	return dp[n];
    }
}