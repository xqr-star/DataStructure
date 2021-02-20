package HashTable;

import java.util.Arrays;

public class HashTableExample {
    /**
     * 利用哈希表
     * 26个字母分别对应着26个下标
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "vcdsjcpodfvjbdklssabew";
        int[] count = new int[26];
        for(char ch : s.toCharArray()){
            int index = ch -'a';//分别计算出应该在那一个位置++
            count[index]++;
        }
        System.out.println(Arrays.toString(count));
    }
}
