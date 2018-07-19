package nowcoder.question.algorithm.greedy.min_diff_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 任意2n个整数，从其中选出n个整数，使得选出的n个整数和同剩下的n个整数之和的差最小。
 * 
 * https://www.nowcoder.com/profile/152074/myFollowings/detail/36060
 * 
 * 
 * 贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，他所做出的是在某种意义上的局部最优解。
 * 贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，选择的贪心策略必须具备无后效性，即某个状态以前的过程不会影响以后的状态，只与当前状态有关。
 * 
 * @author Yan
 *
 */
public class MinDifferenceBetweenTwoArray {

	public static void main(String[] args) {
		int n = 5;
		Integer[] ary = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
		List<Integer> list = Arrays.asList(ary);
		solve(list, n);
	}

	public static void solve(List<Integer> list, int n){
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		
		for (int i = 0; i < list.size(); i++) {
			if(i%2 == 0){
				list1.add(list.get(i));
			}else{
				list2.add(list.get(i));
			}
		}
		
		// 上次的最小差
		int preMinDiff = 0;
		
		// 用于统计循环了几轮
		int count = 1;
		while(true){
			
			// 计算两个list的和
			int sum1 = sumOfList(list1);
			int sum2 = sumOfList(list2);
			
			int difference = Math.abs(sum2 - sum1);
			
			// 和起始的差值比较下哪种交换差值最小
			int minDifferenceAfterExchange = difference;
			Integer[] minSwapCase = null;
			for(int i=0;i<list1.size();i++){
				for(int j=0;j<list2.size();j++){
					// 计算交换lisr1[i] 和 list2[j] 后的差
					int newDifference = Math.abs( (sum2 - list2.get(j) + list1.get(i)) - (sum1 - list1.get(i) + list2.get(j)) );
					if(newDifference < minDifferenceAfterExchange){
						minDifferenceAfterExchange = newDifference;
						minSwapCase = new Integer[]{newDifference, i, j};
					}
				}
			}
			
			// minSwapCase为null说明不需要交换
			if(minSwapCase != null){
				// 交换list1和list2中元素
				int index1 = minSwapCase[1];
				int index2 = minSwapCase[2];
				
				int tmp = list1.get(index1);
				list1.set(index1, list2.get(index2));
				list2.set(index2, tmp);
			}
			
			// 如果和上次的最小diff相比没有变化，则退出循环
			if(minDifferenceAfterExchange == 0 || minDifferenceAfterExchange == preMinDiff){
				
				System.out.println("两个数组和的差：" + minDifferenceAfterExchange);
				break;
			}else{
				preMinDiff = minDifferenceAfterExchange;
			}
			
			System.out.println("次数：" + count);
			count++;
		}
		
		System.out.println(Arrays.toString(list1.toArray()));
		System.out.println(sumOfList(list1));
		System.out.println(Arrays.toString(list2.toArray()));
		System.out.println(sumOfList(list2));
		
	}
	
	public static int sumOfList(List<Integer> list){
		int sum = 0;
		for(int i=0;i<list.size();i++){
			sum+=list.get(i);
		}
		return sum;
	}
}
