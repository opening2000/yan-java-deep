package nowcoder.question.algorithm;

public class ConsecutiveIntegerSum {

	public static void main(String[] args) {
		devideIntoConsecutiveIntegerSum1(10001);
		//devideIntoConsecutiveIntegerSum2(10001);
		int[] arr = new int[10001];
		devideRec(arr ,  10000 , 10000);
	}
	
	public static void devideIntoConsecutiveIntegerSum1(int num){
		int a1 = 1;
		int n = 2;
		for(n = 2 ; n < num ;n++){
			for(a1 = 1; a1 < num ;a1++){
				if((n*n + (2*a1 - 1)*n )  == 2 *num){
					for(int i=1;i<=n;i++){
						System.out.print((a1 + (i-1)) + ",");
					}
					System.out.println();
				}
			}
		}
	}
	
	public static void devideIntoConsecutiveIntegerSum2(int num){
		int a1 = 1;
		int n = 2;
		
		//递增的等差数列
		//等差数列首元素肯定不会超过num的一半
		//此题中首元素和步长确定唯一的等差数列
		for(a1 = num/2; a1 > 0;a1--){
			//等差数列个数还是可以超过num的一半的，比如3=1+2
			for(n = 2 ; n < num ;n++){
				if((n*n + (2*a1 - 1)*n )  == 2 *num){
					//输出正整数序列
					for(int i=1;i<=n;i++){
						System.out.print((a1 + (i-1)) + ",");
					}
					System.out.println();
					//此题中首元素和步长确定唯一的等差数列，所以应该break了
					break;
				}
			}
		}
	}
	
	public static void devideRec(int[] arr, int num, int thr){
	    if (thr > num) {
	        thr = num;
	    }
	    for (int i = 1; i <= thr; ++i) {
	        devide(arr, num, i);
	    }
	}
	
	public static void devide (int[] arr, int num, int thr) {
	    arr[num] = thr;
	    num -= thr;
	    if (num != 0) {
	        for(int i=0;arr[i] !=0 && i<arr.length;i++){
	        	System.out.print(arr[i] + " , ");
	        }
	        System.out.println();
	        
	        return;
	    }
	    devideRec (arr, num, thr);
	}
}
