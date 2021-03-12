package redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class TestRedis {

	public static void main(String[] args) {
		demo1();
	}

	/**
	 * 验证redis中 zset的基本操作
	 */
	public static void demo1() {
		//连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        // jedis.auth("123456"); 
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        String key = "testkey";
        //testZADD(jedis, key);
        
        Set<String> set = jedis.zrange(key, 0, 10);
        for(String str:set) {
        	System.out.print(str + ",");
        }
        System.out.println();
        System.out.println("----------------------");
        
        //ZRANK key member
        //返回有序集合中指定成员的索引
        Long rank = jedis.zrank(key, "mysql");
        System.out.println("rank:" + rank);
        System.out.println("----------------------");
        
        //ZCOUNT key min max
        //计算在有序集合中指定区间分数的成员数
        Long count = jedis.zcount(key, 1.0, 10.0);
        System.out.println("count:" + count);
        System.out.println("----------------------");
        
        Set<Tuple> tuples = jedis.zrangeByScoreWithScores(key, 1.0, 10.0);
        for(Tuple tu:tuples) {
        	System.out.print(tu.getElement() + "," + tu.getScore() + ";");
        }
        System.out.println();
        System.out.println("----------------------");
        
        jedis.close();
	}
	
	public static void testZADD(Jedis jedis, String key) {
		jedis.zadd(key, 1.0, "redis");
        jedis.zadd(key, 2.0, "mongodb");
        jedis.zadd(key, 3.0, "mysql");
        jedis.zadd(key, 3.0, "mysql");
        jedis.zadd(key, 4.0, "mysql");
        jedis.zadd(key, -2.0, "zookeeper");
        jedis.zadd(key, 14.0, "kafka");
        jedis.zadd(key, 5.0, "consul");
        System.out.println("zadd添加完成");
        
	}
	
}
