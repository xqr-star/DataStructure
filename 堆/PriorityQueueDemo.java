package PriorityQueue;

import java.util.PriorityQueue;

/**
 * 使用JDK的堆
 */
public class PriorityQueueDemo {


    /**
     * 堆的应用1
     * 优先级队列--小堆
     * 默认JDK实现的是一个小堆
     *  java.util.PriorityQueue
     */
    public static void main1(String[] args) {
        MyPriorityQueue queue = new MyPriorityQueue();
        //PriorityQueue<Integer> queue= new PriorityQueue<>();
        queue.add(3);
        queue.add(5);
        queue.add(2);
        queue.add(7);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

    }
    /**
     * 堆的应用2
     * 由于堆的构建，需要比较以及调整的能力
     * 所以如果用自定义类进行堆的构建，那么需要实现比较能力
     * 自定义类
     */
    public static void main(String[] args) {
        // PriorityQueue<Person> queue = new PriorityQueue<Person>();

        //本质上不是在new 接口，而是new了一个匿名内部类
        //按照年龄
        /*PriorityQueue<Person> queue = new PriorityQueue<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });*/

        //按照名字的字典序
        /*PriorityQueue<Person> queue = new PriorityQueue<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        });*/

        //简化lambda表达式
        PriorityQueue<Person> queue = new PriorityQueue<Person>(
                (Person o1, Person o2) -> {
                    return o1.age - o2.age;
                }
        );


        Person p1 = new Person("gb",35);
        Person p2 = new Person("cpx",34);
        Person p3 = new Person("tz",45);
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
    /**
     * 堆的应用3
     * 利用堆的特性，利用找最值的特点可以实现排序
     *
     */

    /**
     * 堆的应用4
     * TopK 问题--面试中经常问
     * 海量数据，找到集合中最大的前几个
     * 用大堆还是小堆-- 用小堆
     * 海量数据的问题决定了我们不能对所有数据进行建堆
     * 建立一个五个数据的小堆，每一个要比较的数据都和这个堆里面堆顶去比较
     * 如果比堆顶大，那么就可以替换这个堆顶，然后对堆进行向下调整即可。
     * 代码不做要求
     */


}
//注意这个类为什么是这样写的，相当于是用了泛型
class Person implements Comparable<Person>{
    String name;
    int age;
    Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        //由于上面使用了泛型，所以这里就不用传递泛型了
        return age -o.age;
    }
}