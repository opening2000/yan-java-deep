package algorithm.application.bloomfilter.demo2;

import java.nio.charset.Charset;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class GuavaBloomFilterDemo {

    public static void main(String[] args) {
    	//testInt();
    	testString();
    }
    
    public static void testInt() {
    	//后边两个参数：预计包含的数据量，和允许的误差值
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100000, 0.01);
        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(i);
        }
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(3));
        System.out.println(bloomFilter.mightContain(100001));

        //bloomFilter.writeTo();
    }
    
    public static void testString() {
    	BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF8")), 100000000, 0.01);
    	for (int i = 0; i < 100000; i++) {
            bloomFilter.put("Str_"+i);
        }
    	System.out.println(bloomFilter.mightContain("Str_"+1));
        System.out.println(bloomFilter.mightContain("Str_"+2));
        System.out.println(bloomFilter.mightContain("Str_"+3));
        System.out.println(bloomFilter.mightContain("Str_"+100001));

    }
}