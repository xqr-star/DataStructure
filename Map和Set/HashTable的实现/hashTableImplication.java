package HashTable;

import java.util.HashMap;
import java.util.Map;

public class hashTableImplication {
    public static void main(String[] args) {
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        System.out.println(set);
//        System.out.println(set.add(1));
//        System.out.println(set.remove(3));
//        System.out.println(set.remove(4));

        Map<String,Integer> map = new HashMap<>();
        System.out.println(map.put("cpx", 14));//第一次put的返回值是null
        System.out.println(map.put("cpx", 15));//第一次put的返回值是null
        map.put("dfv",24);
        map.put(null,14);
        //这个顺序既不是字母顺序也不是其他什么顺序
        for(String key : map.keySet()){
            System.out.println(key);
        }

    }
}

