package day36;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class T {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            Map<Character,Integer>map = new LinkedHashMap<>();
            for(char ch = 'A';ch<= 'Z';ch++){
                map.put(ch,0);
            }
            for(int i=0;i <str.length();i++){
                char ch = str.charAt(i);
                //map有的方法是containsKey
                if(map.containsKey(ch)){
                    map.put(ch,map.get(ch)+1);
                }
            }
            //循环输出
            for(Map.Entry<Character,Integer> mapSet : map.entrySet()){
                System.out.println(mapSet.getKey()+":"+mapSet.getValue());
            }

        }
    }
}
