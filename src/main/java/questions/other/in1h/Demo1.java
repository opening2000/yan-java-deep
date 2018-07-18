package questions.other.in1h;

import java.util.Arrays;

/**
 * 1、编写一个能将给定非负整数列表中的数字排列成最大数字的函数。例如，给定[50，2，1,9]，最大数字为95021。
 * @author Yan
 *
 */
public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//实际上是变种的排序
		
		
		//int[] ary = new int[]{50,51,200,1,9};  //整体比较小，但是首位较大
		//int[] ary = new int[]{9,50,51,200,1};    //首位相同，第二位不同
		
		int[] ary = new int[]{9,50,50,51,200,1};  //有相同的数字
		
		//(每个数字的首位大小排序)首位相同，考虑第二位
		
		fun1(ary);
		System.out.println(Arrays.toString(ary));
		fun2(ary);
		System.out.println(Arrays.toString(ary));
	}

	public static void fun1(int[] ary){
		for(int i=0;i<ary.length;i++){
			int index = i;
			int maxFirst = 0;
			for(int j=i;j<ary.length;j++){
				int first = ary[j];    //first表示ary[i]的首位数字
				while(first > 10){
					first = first/10;
				}
				//System.out.println(first);
				
				if(first > maxFirst){
					maxFirst = first;
					index = j;
				}
			}
			int tmp = ary[i];
			ary[i] = ary[index];
			ary[index] = tmp;
			//System.out.println(Arrays.toString(ary));
		}
	}
	
	public static void fun2(int[] ary){
		for(int i=0;i<ary.length;i++){
			int index = i;
			for(int j=i;j<ary.length;j++){
				if(compare(ary[index], ary[j]) < 0){
					index = j;
				}
			}
			int tmp = ary[i];
			ary[i] = ary[index];
			ary[index] = tmp;
			//System.out.println(Arrays.toString(ary));
		}
	}
	
	/**
	 * 比较两个整数，比较原则，从第一位开始，大的整数大
	 * @param a
	 * @param b
	 * @return
	 */
	public static int compare(int a , int b){
		int result = 0;
		
		if(a == b){
			result = 0;
		}else{
			//int longLen = (a+"").length()>(b+"").length()?(a+"").length():(b+"").length();
			int[] ary1 = new int[(a+"").length()];
			int[] ary2 = new int[(b+"").length()];
			
			for(int i=ary1.length-1;i>=0;i--){
				ary1[i] = a%10;
				a = a/10;
			}
			
			for(int i=ary2.length-1;i>=0;i--){
				ary2[i] = b%10;
				b = b/10;
			}
			
			int i=0;
			while(i < ary1.length || i < ary2.length){
				if(i < ary1.length && i < ary2.length){
					if(ary1[i] > ary2[i]){
						result = 1;
						break;
					}else if(ary1[i] < ary2[i]){
						result = -1;
						break;
					}
				}else {
					//i >= ary1.length || i >= ary2.length
					if(ary1.length > ary2.length){
						result = 1;
						break;
					}else if(ary1.length < ary2.length){
						result = -1;
						break;
					}
				}
				i++;
			}
		}
		return result;
	}
}
