package questions.other.in1h;

import java.util.Arrays;

/**
 * 3、编写一个交错合并列表元素的函数。例如：给定的两个列表为[a，B，C]和[1，2，3]，函数返回[a，1，B，2，C，3]。
 */
public class Demo3 {

	public static void main(String[] args) {
//		char[] ary1 = new char[]{ 'a' , 'b' , 'c' , 'd'};
//		char[] ary2 = new char[]{ '1' , '2' , '3' , '4'};
		
//		char[] ary1 = new char[]{ 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g'};//len = 7
//		char[] ary2 = new char[]{ '1' , '2' , '3' , '4'};//len = 4
		
		char[] ary1 = new char[]{ 'a' , 'b' , 'c' , 'd'};
		char[] ary2 = new char[]{ '1' , '2' , '3' , '4' , '5' , '6' , '7'};
		
		int shortLen = ary1.length<ary2.length?ary1.length:ary2.length;
		
		char[] ary = new char[ary1.length + ary2.length];
		
		for (int i = 0; i < ary.length; i++) {
			if(i < 2*shortLen){
			//if(i/2 < ary1.length && i/2 < ary2.length){
				if(i%2==0){
					ary[i] = ary1[i/2];
				}else if(i%2==1){
					ary[i] = ary2[i/2];
				}
			}else if(ary1.length<ary2.length){
				ary[i] = ary2[i-shortLen];
			}else if(ary1.length>ary2.length){
				ary[i] = ary1[i-shortLen];
			}
			
		}
		
		System.out.println(Arrays.toString(ary));
	}

}
