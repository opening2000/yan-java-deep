package algorithm.divideconquer;

/**
 * 
 * 例2  二分法求方程近似解：求方程f(x) = x^3 + x^2 - 1 = 0在[0,1]上的近似解，精确度为0.01。
 * 算法分析：二分法求方程近似解的基本思想：将方程的有解区间平分为两个小区间，然后判断解在哪个小区间；
 * 继续把有解的区间一分为二进行判断，如此周而复始，直到求出满足精确要求的近似解。
 * 
 * 二分法求方程近似解的算法步骤：
 * ⑴确定区间[a,b]，验证f(a).f(b) < 0，给定精确度e
 * ⑵求区间(a, b)的中点mid
 * ⑶计算f(mid)
 * 若f(mid) = 0，则mid就是函数的零点
 * 若f(a).f(mid) < 0，则令b = mid(此时零点a < x0 < mid)
 * 若f(mid).f(b) < 0，则令a = mid(此时零点mid < x0 < b)
 * ⑷判断是否达到精确度e：即若|a-b| < e，则得到零点近似值a（或b）；否则重复⑵-⑷。
 * 
 * @author Yan
 *
 */
public class DivideEquation {

	public static void main(String[] args) {
		divideAndSolve(0 , 1 , 0.01);

	}
	
	/**
	 * f(x) = x^3 + x^2 - 1
	 * @param x
	 * @return
	 */
	public static double f(double x) {
		
		return x*x*x + x*x -1;
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param precision
	 */
	public static void divideAndSolve(double start , double end , double precision){
		/**
		 * 二分法的定义：
		 * 对于区间[a，b]上连续不断，且f（a）·f（b）＜0的函数y=f（x），
		 * 通过不断把函数f（x）的零点所在的区间一分为二，使区间的两个端点逐步逼近零点，
		 * 进而得到零点近似解的方法叫做二分法。
		 * 
		 * 给定精确度ξ，用二分法求函数f（x）的零点的近似值的步骤：
		 * （1）确定区间[a，b]，验证f（a）·f（b）＜0，给定精确度ξ；
		 * （2）求区间（a，b）的中点x1； 
		 * （3）计算f（x1）， 
		 * ①若f（x1）=0，则就是函数的零点； 
		 * ②若f（a）·f（x1）＜0，则令b=x1（此时零点x0∈（a，x1））； 
		 * ③若f（x1）·f（b）＜0，则令a=x1（此时零点x0∈（x1，b））； 
		 * （4）判断是否达到精确度ξ，即若|a-b|＜ξ，则达到零点近似值a（或b）；否则重复（2）-（4）。
		 * 
		 */
		if(f(start) * f(end) < 0){
			double mid = (start + end)/2;
			if(f(mid) == 0 ){
				System.out.println(mid);
			}
			
			if( (end - start) > precision){
				divideAndSolve(start, mid, precision);
				divideAndSolve(mid, end, precision);
			}else{
				System.out.println(start + "," + end);
			}
			
		}
	}
}
