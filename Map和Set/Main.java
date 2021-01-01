package set的使用;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {


        //Set<String> set = new TreeSet<String>();
        Set<String> set = new TreeSet<>();
        System.out.println(set.add("apple"));
        System.out.println(set.add("peach"));
        System.out.println(set.add("banana"));
        System.out.println(set.add("orange"));

        System.out.println(set.size());
        System.out.println(set);

        System.out.println(set.add("apple"));
        System.out.println(set.contains("watermelon"));
        System.out.println(set.contains("apple"));

        System.out.println(set.remove("apple"));
        System.out.println(set.remove("watermelon"));

        System.out.println("=================");
        set.clear();//你把它清空的时候，结构还在不在。还是只是说里面的元素没有了
//        System.out.println(set.isEmpty());
//        System.out.println(set.size());

        //==================
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);
        }

        //和下面等效的
//
//        for (String fruit : set) {
//            System.out.println(fruit);
//        }


    }



}
