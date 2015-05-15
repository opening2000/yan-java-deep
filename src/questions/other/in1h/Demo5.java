package questions.other.in1h;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5、编写一个在1，2，…，9（顺序不能变）数字之间插入+或-或什么都不插入， 使得计算结果总是100的程序，并输出所有的可能性。 例如：1 + 2 +
 * 34 – 5 + 67 – 8 + 9 = 100。
 * 
 * 
 * @author Yan
 * 
 */
public class Demo5 {
	
	/**
	 * 缓存1和-1序列
	 */
	public static Map<Integer , List<int[]>> weightArrayMap = new HashMap<Integer, List<int[]>>(0);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ary = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ifTreeAndDouble(ary , 0 , 1 , ary.length);
		
		if1three();
		
		// 有3个两位数
		// 有4个两位数
		// 有5个两位数
		// 有1个三位数，0个两位数
		
		// 有1个三位数，1个两位数
		// 有1个三位数，2个两位数
		// 有1个三位数，3个两位数
	}
	
	/**
	 * 1和-1组成的数组
	 * @param len
	 */
	public static List<int[]> weightArray(int len){
		List<int[]> was = null;
		
		was = weightArrayMap.get(len);
		if(was == null){
			was = new ArrayList<int[]>();
			int[] wa = new int[len];
			putnext(wa , 0 , was);
			weightArrayMap.put(len, was);
		}
		return was;
	}
	
	public static void putnext(int[] ary , int pos , List<int[]> list){
		if(pos < ary.length){
			for(int i=0;i<2;i++){
				if(i==0){
					//+1
					ary[pos] = 1;
				}else if(i==1){
					//-1
					ary[pos] = -1;
				}
				
				if(pos < ary.length -1){
					//如果要赋值的这一位不是最后一位，那么接着对后面的一位处理
					putnext(ary , pos+1 , list);
				}else if (pos == ary.length-1) {
					//如果要赋值的这一位是最后一位，那么将这个数组保存
					//System.out.println(Arrays.toString(ary));
					int[] newAry = new int[ary.length];
					System.arraycopy(ary, 0, newAry, 0, ary.length);
					list.add(newAry);
				}
			}
		}
	}
	
	public static void findSumEql100(int[] ary1){
		//因为第一位一定是1，所以权重序列的长度为ary1.length - 1
		List<int[]> weightList = weightArray(ary1.length - 1);
		for(int[] a : weightList){
			int[] newAry = new int[ary1.length];
			int sum = ary1[0];
			newAry[0] = ary1[0];
			for(int j=0;j<a.length;j++){
				sum += ary1[j+1] * a[j];
				newAry[j+1] = ary1[j+1] * a[j];
			}
			//System.out.println(Arrays.toString(newAry) + ":" + sum);
			if(sum == 100){
				System.out.println(Arrays.toString(newAry));
			}
		}
	}
		
	public static void if1three() {
		// 1,2,3,4,5,6,7,8,9
		// 先把数分好，再计算是否等于100
		int[] ary = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// 有1个三位数
		// 三位数的起始数字的下表从0到6
		for (int index = 0; index < (ary.length - 2); index++) {
			int[] ary1 = new int[(ary.length - 2)];
			for (int i = 0; i < ary1.length; i++) {
				if (i < index) {
					ary1[i] = ary[i];
				} else if (i == index) {
					ary1[i] = ary[i] * 100 + ary[i + 1] * 10 + ary[i + 2];
				} else {
					ary1[i] = ary[i + 2];
				}
			}
			//System.out.println(Arrays.toString(ary1));

			//findSumEql100(ary1);
			//boolean[] isEntered = new boolean[];
			ifTreeAndDouble(ary1 , 0 , 0 , ary1.length);
		}
	}
	
	public static void ifTreeAndDouble(int[] ary , int thisDoubleStartPos , int doubleNum , int totalNum) {
		// 两位数的起始数字的下表从0到7
		for (int index = thisDoubleStartPos; index < (ary.length - 1); index++) {
			//注意这个循环，index指的是两位数的下标，如果说两位数的个数为0的话，那么下标变化是无意义的，因为下标变化不变化产生的数组是相同的
			if(doubleNum == 0 && index > 0){
				break;
			}
			if(doubleNum > 0){
				if (ary[index] >= 10 || ary[index + 1] >= 10) {
					continue;
				}
			}
			int[] ary1 = null;
			if(doubleNum == 0){
				ary1 = new int[(ary.length)];
			}else if(doubleNum >= 1){
				ary1 = new int[(ary.length - 1)];
			}
			
			for (int i = 0; i < ary1.length; i++) {
				if (i < index || doubleNum == 0) {
					ary1[i] = ary[i];
				} else if (i == index) {
					if(doubleNum >= 1){
						if(ary[i] < 10 && ary[i + 1] < 10){
							ary1[i] = ary[i] * 10 + ary[i + 1];
						}else{
							ary1[i] = ary[i];
						}
					}
				} else {
					ary1[i] = ary[i + 1];
				}
			}
			//System.out.println(Arrays.toString(ary1));
			findSumEql100(ary1);
			
			if((doubleNum+1)*2 <= (totalNum-1)){
				if(doubleNum == 0){
					ifTreeAndDouble(ary1 , index, doubleNum+1 , totalNum);
				}else if(doubleNum > 0){
					ifTreeAndDouble(ary1 , index+1, doubleNum+1 , totalNum);
				}
			}
		}
	}
	
}