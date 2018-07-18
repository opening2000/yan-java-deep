package questions.other.in1h;

/**
 * 4、使用for循环、while循环和递归写出3个函数来计算给定数列的总和。
 * @author Yan
 *
 */
public class Demo4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[] ary = new double[]{1, 5, 7, 9 , 15};
		double result1 = sumfor(ary);
		System.out.println(result1);
		
		double result2 = sumwhile(ary);
		System.out.println(result2);
		
		double result3 = fun(ary);
		System.out.println(result3);
		
		double result4 = fun(ary , 0);
		System.out.println(result4);
		
	}
	
	public static double sumfor(double[] ary){
		double sum = 0;
		for(int i=0;i<ary.length;i++){
			sum += ary[i];
		}
		return sum;
	}

	public static double sumwhile(double[] ary){
		double sum = 0;
		int i=0;
		while(i<ary.length){
			sum += ary[i];
			i++;
		}
		return sum;
	}
	
	public static double fun(double ary[]){
		if(ary.length == 1){
			return ary[0];
		}else{
			double[] ary1 = new double[ary.length-1];
			System.arraycopy(ary, 1, ary1, 0, ary1.length);
			return ary[0] + fun(ary1);
		}
	}
	
	public static double fun(double ary[] , int start ){
		if(start == (ary.length-1)){
			return ary[start];
		}else{
			return ary[start] + fun(ary , start + 1);
		}
	}
}
