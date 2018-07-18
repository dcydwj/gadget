import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        long index = 285625296934912L;

        for (int i = 0; i < 12456789; i = i + 7) {
            int hash = Hashing.consistentHash(index + i, 16);
            Integer v = map.get(hash);
            if (null != v) {
                map.put(hash, v + 1);
            } else {
                map.put(hash, 1);
            }
        }
        System.out.println(JSON.toJSONString(map));
        long sum = map.values().stream().mapToLong(e -> e).sum();
        System.out.println(sum);

        System.out.println("--------------------华丽分割线--------------------");

        map.clear();
        for (int i = 0; i < 12456789; i = i + 7) {
            int hash = (int) ((index + i) % 16);
            Integer v = map.get(hash);
            if (null != v) {
                map.put(hash, v + 1);
            } else {
                map.put(hash, 1);
            }
        }
        System.out.println(JSON.toJSONString(map));
        long sum2 = map.values().stream().mapToLong(e -> e).sum();
        System.out.println(sum2);

        int a =3;
        int b =5;
        int c = a = b;

        System.out.println(c);

    }
}
