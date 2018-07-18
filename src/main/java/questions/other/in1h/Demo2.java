package questions.other.in1h;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 2、编写一个计算前100位斐波那契数的函数。根据定义，斐波那契序列的前两位数字是0和1，
 * 随后的每个数字是前两个数字的和。例如，     前10位斐波那契数为：0，1，1，2，3，5，8，13，21，34。
 * 
 * 注意长度
 * @author Yan
 *
 */
public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigDecimal[] ary = new BigDecimal[100];
		ary[0] = new BigDecimal(0);
		ary[1] = new BigDecimal(1);
		
		for(int i=2;i<ary.length;i++){
			ary[i] = ary[i-2].add(ary[i-1]);
		}
		System.out.println(Arrays.toString(ary));
		
	}

}
