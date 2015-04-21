package nowcoder.question.algorithm;

public class ConsecutiveIntegerSum {

	public static void main(String[] args) {
		devideIntoConsecutiveIntegerSum1(10000);
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
}
