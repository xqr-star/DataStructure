package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 1.测试乱序
 * 2.已经有序
 * 3.逆序
 * 4.完全相等
 * 5.测试不同规模的数据
 */
public class SortTest {
    public static int[] 构建随机数组(){
        //构造一个随机种子
        Random random = new Random(20210216);
        int[] arr = new int[10];
        for(int i = 0;i < 10;i++){
            arr[i] = random.nextInt(100);//数字的范围在0-99之间
        }
        return arr;
    }
    public static int[] 构建完全有序的数组(){
        Random random = new Random(20210216);
        int[] arr = new int[10];
        for(int i = 0;i < 10;i++){
            arr[i] = random.nextInt(100);//数字的范围在0-99之间
        }
        Arrays.sort(arr);
        return arr;
    }

    public static int[] 构建完全逆序的数组(){

        //或者可以构建一个有序的然后交换即可
        //或者这样写
        int [] arr = new int[10];
        int num = 10;
        for(int i = 0;i <10;i++){
            arr[i] = num--;
        }
        return arr;
    }

    public static int[] 构建完全相等的数组(){
        int [] arr = new int[10];
        int num = 2;
        for(int i = 0;i <10;i++){
            arr[i] = num;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = 构建随机数组();
        System.out.println(Arrays.toString(arr));
        Sort.selectSort2(arr);
        System.out.println(Arrays.toString(arr));

//        int[] arr2 =  构建完全逆序的数组();
//        System.out.println(Arrays.toString(arr2));
//        Sort.selectSort(arr2);
//        System.out.println(Arrays.toString(arr2));
//
//        int[] arr3 =  构建完全相等的数组();
//        System.out.println(Arrays.toString(arr3));
//        Sort.selectSort(arr3);
//        System.out.println(Arrays.toString(arr3));
//
//        int[] arr4 =  构建完全有序的数组();
//        System.out.println(Arrays.toString(arr4));
//        Sort.selectSort(arr4);
//        System.out.println(Arrays.toString(arr4));

    }
}
