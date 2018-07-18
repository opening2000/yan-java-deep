package algorithm.institution;

import java.util.Random;

/**
 *���ܣ� ��18λ���֤�����һλ
 * @author cfd406635982
 *
 */
public class IdentityNum {

	private static int sexFlag;       //�Ա��־��1Ϊ���ԣ�0ΪŮ��
	private static int minAge;        //��С����
	private static int maxAge;        //�������
	
	static{
		sexFlag = 1;
		minAge = 25;
		maxAge = 35;
	}
	
    public static void main(String[] args) {
    	for(int i=0;i<50;i++){
    		System.out.println(getRandomId());
    	}
    }

    public static String getRandomId(){
    	String pre17 = getRandomBeiJingQuHao() + getRandomBirthStr() + getRandomShunXuMa();
    	return pre17 + getLastIDNum(pre17);
    }
    
    /** 
     * ��ƣ� ����18λ���֤�����һλ
     * ���� : ���ǰ17λ���֤�ţ������һλ
     * ���֤���һλ���㷨��
     * 1.�����֤�����ǰ17λ�����֣��ֱ����Ȩ�� �� 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 
     *      (���磺��һλ����7���ڶ�λ����9���Դ�����)
     * 2.�ٽ���������г˻����
     * 3.����õĺ�mod��11��%11�����õ�һ��С��11����0��11��
     * 4.Ȼ���1 0 X 9 8 7 6 5 4 3 2��λУ�������ҳ����һλ������
     *   ���õ�����0�����Ӧ��һλ��1,���õ�����1�����Ӧ�ڶ�λ��0
     *   ���õ�����2�����Ӧ����λ��X,���õ�����3�����Ӧ����λ��9,�Դ�����
     * 5.���õ��ľ������֤�����һλ
     */
    public static Character getLastIDNum(String preIds) {
        Character lastId = null;
        //��������ַ�û��17λ��ʱ�����޷����㣬ֱ�ӷ���
        if(preIds==null && preIds.length()<17) {
            return null;
        }
        int[] weightArray = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};//Ȩ������
        String vCode = "10X98765432";//У�����ַ�
        int sumNum = 0;//ǰ17Ϊ����ȨȻ����͵õ�����
        
        //ѭ������Ȩ�������
        for(int i=0;i<17;i++) {
            int index = Integer.parseInt(preIds.charAt(i)+"");
            sumNum = sumNum +index*weightArray[i];//����Ȩ�������
        }
        
        int modNum = sumNum%11;//��ģ
        lastId = vCode.charAt(modNum);//����֤�����ҳ���Ӧ����
        
        return lastId;
    }
    
    /**
    * ���֤15λ�������dddddd yymmdd xx p
    * dddddd��������
    * yymmdd: ����������
    * xx: ˳������룬�޷�ȷ��
    * p: �Ա�����Ϊ�У�ż��ΪŮ
    * <p />
    * ���֤18λ�������dddddd yyyymmdd xxx y
    * dddddd��������
    * yyyymmdd: ����������
    * xxx:˳������룬�޷�ȷ��������Ϊ�У�ż��ΪŮ
    * y: У���룬��λ��ֵ��ͨ��ǰ17λ������
    * <p />
    * 18λ�����Ȩ����Ϊ(���ҵ���) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]
    * ��֤λ Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
    * У��λ���㹫ʽ��Y_P = mod( ��(Ai��Wi),11 )
    * iΪ���֤�������������� 2...18 λ; Y_PΪ��ѾУ��������У��������λ��
    *
    */ 
    /**
     * ����漴�����֤ǰ17λ
     */
    public static String getRandomBeiJingQuHao(){
    	String[] quAry = new String[]{
    			"110000","110100","110101","110102","110103","110104","110105",
    			"110106","110107","110108","110109","110111","110112","110113",
    			"110114","110115","110116","110117","110200","110228","110229"};
    	Random ran = new Random();
    	return quAry[ran.nextInt(quAry.length)];
    }
    
    /**
     * ����漴������
     * @return
     */
    public static String getRandomBirthStr(){
    	//random.nextInt(n)   0 - n-1
    	Random ran = new Random();
    	int[] yearAry = new int[]{1900 , 1901 , 1902 , 1903 , 1904 , 1905 , 1906 , 1907 , 1908 , 1909 , 
    			1910 , 1911 , 1912 , 1913 , 1914 , 1915 , 1916 , 1917 , 1918 , 1919 , 
    			1920 , 1921 , 1922 , 1923 , 1924 , 1925 , 1926 , 1927 , 1928 , 1929 , 
    			1930 , 1931 , 1932 , 1933 , 1934 , 1935 , 1936 , 1937 , 1938 , 1939 , 
    			1940 , 1941 , 1942 , 1943 , 1944 , 1945 , 1946 , 1947 , 1948 , 1949 , 
    			1950 , 1951 , 1952 , 1953 , 1954 , 1955 , 1956 , 1957 , 1958 , 1959 , 
    			1960 , 1961 , 1962 , 1963 , 1964 , 1965 , 1966 , 1967 , 1968 , 1969 , 
    			1970 , 1971 , 1972 , 1973 , 1974 , 1975 , 1976 , 1977 , 1978 , 1979 , 
    			1980 , 1981 , 1982 , 1983 , 1984 , 1985 , 1986 , 1987 , 1988 , 1989 , 
    			1990 , 1991 , 1992 , 1993 , 1994 , 1995 , 1996 , 1997 , 1998 , 1999 , 
    			2000 , 2001 , 2002 , 2003 , 2004 , 2005 , 2006 , 2007 , 2008 , 2009 , 
    			2010 , 2011 , 2012 , 2013 , 2014 };
    	String[] monthAry = new String[]{"01" , "02" , "03" , "04" , "05" , "06" , "07" , "08" , "09" , "10" , "11" , "12"};
    	String[] dayAry = new String[]{"01" , "02" , "03" , "04" , "05" , "06" , "07" , "08" , "09" , "10" , 
    			"11" , "12" , "13" , "14" , "15" , "16" , "17" , "18" , "19" , "20" , 
    			"21" , "22" , "23" , "24" , "25" , "26" , "27" , "28" , "29" , "30" , "31" };
    	int year= yearAry[ran.nextInt(maxAge-minAge) + (yearAry.length - maxAge)];
    	String month = monthAry[ran.nextInt(12)];
    	String day = "";
    	if("02".equals(month)){
    		day = dayAry[ran.nextInt(28)+1];
    	}else if("01,03,05,07,08,10,12".indexOf(month)>-1){
    		day = dayAry[ran.nextInt(31)];
    	}else{
    		day = dayAry[ran.nextInt(30)];
    	}
    	return year+month+day;
    }
    
    public static String getRandomShunXuMa(){
    	//˳����Ϊż��ΪŮ�ԣ�����Ϊ����
    	//�����漴˳����Ҫ���sexFlag���漴
    	Random ran = new Random();
    	return ""+ran.nextInt(10)+ran.nextInt(10) + (ran.nextInt(5)*2 + sexFlag);
    }
}
