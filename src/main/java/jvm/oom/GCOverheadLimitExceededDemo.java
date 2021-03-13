package jvm.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * JVM 参数： -Xmx14m -XX:+PrintGCDetails
 * 
 * 
 * @author Yan
 *
 */
public class GCOverheadLimitExceededDemo {

	static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        while (true){
            for (int i = 0; i < 1000; i++){
                if (!m.containsKey(new Key(i))){
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println("m.size()=" + m.size());
        }
    }

}
