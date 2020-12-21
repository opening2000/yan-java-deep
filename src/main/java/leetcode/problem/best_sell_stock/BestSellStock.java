package leetcode.problem.best_sell_stock;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * @author Yankj
 *
 */
public class BestSellStock {

	public static void main(String[] args) {
		/*
		输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
		输出: 8
		解释: 能够达到的最大利润:  
		在此处买入 prices[0] = 1
		在此处卖出 prices[3] = 8
		在此处买入 prices[4] = 4
		在此处卖出 prices[5] = 9
		总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
		
		来源：力扣（LeetCode）
		链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
		著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
		
		备注，没有在prices[1] = 3卖出，prices[2] = 2买入
		
		
		 */
		int[] prices = new int[] {1, 3, 2, 8, 4, 9};
		int fee = 2;
		
		Solution solution = new Solution();
		System.out.println(solution.maxProfit(prices, fee));
	}

}


class Solution {

    public int maxProfit(int[] prices, int fee) {
    	int income = 0;
    	Stack<Integer> buyIndexStack = new Stack<Integer>();
    	Stack<Integer> sellIndexStack = new Stack<Integer>();
    	
    	/**
    	 * 先不说最优方案，最简单的方案是判断 a[n] < a[n+1]+fee  时（需要考虑费用），n时买入，n+1时卖出
    	 * 如果a[n+1] < a[n+2] 则n+2再卖出
    	 * 
    	 * 如果还没有买入，但是发现 a[n] > a[n+1] 则 n+1再买入
    	 */
    	if(prices.length > 0) {
    		buyIndexStack.push(prices[0]);
    		for(int i=1;i<prices.length;i++) {
    			if(buyIndexStack.size() > sellIndexStack.size()) {
    				int buyPrice = buyIndexStack.peek();
    				if(prices[i] < buyPrice) {
    					// 如果买入的次数大于卖出的次数，并且当前价格比最近的买入点低，则说明应该在这个点买入
    					buyIndexStack.pop();
    					buyIndexStack.push(prices[i]);
    					
    					buyPrice = prices[i];
    				}else if (prices[i] >= (buyPrice+fee)) {
    					// a[n] < a[n+1]+fee  时（需要考虑费用），n时买入，n+1时卖出，这个点可以考虑卖出
    					sellIndexStack.push(prices[i]);
    				}
    			}else if(buyIndexStack.size() == sellIndexStack.size() && sellIndexStack.size() > 0) {
    				// 如果卖出栈不为空，并且买入的次数、卖出的次数相等，可以对比一下是否有更合适的卖出价格
    				int sellPrice = sellIndexStack.peek();
    				if(prices[i] > sellPrice) {
    					// 如果当前价格比卖出价格高，则考虑在这个价位卖出
    					sellIndexStack.pop();
    					sellIndexStack.push(prices[i]);
    					
    					sellPrice = prices[i];
    				}else if (prices[i] <= (sellPrice-fee)) {
    					// 如果当前价格比上一次卖出价格低，则考虑买入
    					// 但是当前价格需要比上次卖出价格低fee才值得买入
    					// 因为买入总是考虑后续价格上涨卖出，如果价差不足fee，还不如持有，等待后续更高的价格卖出
    					buyIndexStack.push(prices[i]);
    				}
    			}
    		}
    		
    		// 特殊情况，只有买入点，没有合适卖出点的情况
    		while(buyIndexStack.size() > sellIndexStack.size()) {
    			buyIndexStack.pop();
    		}
    		// 只是逻辑上处理一下，业务上不会出现卖出点比买入点多的情况
    		while(buyIndexStack.size() < sellIndexStack.size()) {
    			sellIndexStack.pop();
    		}
    		
    		while(sellIndexStack.size() > 0) {
    			int sellPrice = sellIndexStack.pop();
    			int buyPrice = buyIndexStack.pop();
    			
    			income += sellPrice - buyPrice - fee;
    		}
    	}
    	
    	return income;
    }
}