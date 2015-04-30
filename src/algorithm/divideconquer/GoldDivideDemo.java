package algorithm.divideconquer;

/**
 * @题目:
 * 例1  最大最小问题：老板有一袋金块，共n块。将有两名最优秀的雇员每人得到其中的一块，
 * 排名第一的得到最重的那块，排名第二的雇员得到袋子中最轻的金块。
 * 假设有一台比较重量的仪器，我们希望用最少的比较次数找出最重和最轻的金块。
 * 
 * @算法分析：
 * 这个问题可以转化一个具有n个元素的数组里，寻找最大和最小元素问题。
 * 一般算法是遍历数组，依次将每个元素和当前最大，最小值判断，直至遍历数组结束，返回最大，最小值。
 * @author Yan
 *
 */
public class GoldDivideDemo {

	public static void main(String[] args) {
		int[] ary = new int[]{5,8,6,9,7,1,3,13,10,4,2,11};
		int[] result = maxMin(ary);
		System.out.println("max = " + result[0] + ", min = " + result[1]);
		
		result[0] = 0;
		result[1] = 0;
		result = maxMin2(ary);
		System.out.println("max = " + result[0] + ", min = " + result[1]);
		
		
		/**
		 * 
		 * 很清楚，在输入规模为n时，这个算法所执行的元素比较次数是2n-2。
		 * 
		 * 用分治法可以较少比较次数地解决上述问题：
		 * 如果集合中只有1个元素，则它既是最大值也是最小值；
		 * 如果有2个元素，则一次Max(i,j)，一次Min(i,j)就可以得到最大值和最小值；
		 * 如果有多于2个元素，则把集合分成两个规模相当子集合，递归的应用这个算法分别求出两个子集合的最大值和最小值，
		 * 最后让子集合1的最大值跟子集合2的最大值比较得到整个集合的最大值；
		 * 让子集合1的最小值跟子集合2的最小值比较得到整个集合的最小值。
		 * 
		 * 使用分治法法后可以把元素比较次数从原来的2n-2减少为（3n/2）- 2。//这个是别人说的没有亲自计算
		 * 
		 */
	}

	/**
	 * 通过遍历的方法，找到数组中的最大值和最小值
	 * @param ary
	 * @return
	 */
	public static int[] maxMin(int[] ary) {
		int[] maxMin = new int[2];    //第一位表示max，第二位表示min
		maxMin[0] = ary[0];
		maxMin[1] = ary[0];
		
		for (int i = 0; i < ary.length; i++) {
			if(ary[i] > maxMin[0]){
				maxMin[0] = ary[i];
			}
			if(ary[i] < maxMin[1]){
				maxMin[1] = ary[i];
			}
		}
		return maxMin; 
	}
	
	/**
	 * 使用分治法来计算数组中的最大值和最小值
	 * @param ary
	 * @return
	 */
	public static int[] maxMin2(int[] ary) {
		//System.out.println(java.util.Arrays.toString(ary));
		int[] result = new int[2];    //第一位表示max，第二位表示min
		
		//终止拆分的条件
		if(ary.length == 1){
			result[0] = ary[0];
			result[1] = ary[0];
			return result;
		}else if(ary.length == 2){
			result[0] = ary[0];
			result[1] = ary[0];
			
			if (ary[1] > result[0]) {
				result[0] = ary[1];
			}
			if (ary[1] < result[1]) {
				result[1] = ary[1];
			}
			return result;
		}
		
		//拆分部分
		int[] array1 = new int[ary.length/2];
		System.arraycopy(ary, 0, array1, 0, array1.length);
		int[] result1 = maxMin2(array1);
		
		int[] array2 = new int[ary.length - ary.length/2];
		System.arraycopy(ary, ary.length/2, array2, 0, array2.length);
		int[] result2 = maxMin2(array2);
		
		//将拆分后分别计算出的结果合并
		result[0] = result1[0];
		result[1] = result1[1];
		if(result2[0] > result1[0]){
			result[0] = result2[0];
		}
		if(result2[1] < result1[1]){
			result[1] = result2[1];
		}
		//System.out.println(result[0] + "," + result[1]);
		return result; 
	}
}
