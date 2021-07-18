package 排序;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //建立随机种子
        Random random = new Random(20210718);
        int set = random.nextInt();
        System.out.println(set);


        int[] 随机数组 = new int[10];
        for(int i = 0; i < 10;i++){
            随机数组[i] = random.nextInt(100);
        }
        int[] 有序数组 = new int[10];
        for (int i = 0; i < 10;i++){
            有序数组[i] = i;
        }
        int[] 相同数组 = new int[10];
        for(int i =0;i < 10;i++){
            相同数组[i] = 1;
        }
        int[] 逆序数组 = new int[10];
        for(int i = 0;i < 10;i++){
            逆序数组[i] = 10-i;
        }


        Insert insert = new Insert();

        Select select = new Select();
        Bubble bubble = new Bubble();
        Quick quick = new Quick();


        Merge merge = new Merge();
        Heap heap = new Heap();


//        System.out.println(Arrays.toString(随机数组));
//        System.out.println(Arrays.toString( insert.insertSort(随机数组)));
//        System.out.println(Arrays.toString( insert.insertSort(有序数组)));
//        System.out.println(Arrays.toString( insert.insertSort(相同数组)));
//        System.out.println(Arrays.toString( insert.insertSort(逆序数组)));
//
//
//
//

        System.out.println(Arrays.toString(select.selectSort(随机数组)));
        System.out.println(Arrays.toString(select.selectSort(有序数组)));
        System.out.println(Arrays.toString( select.selectSort(相同数组)));
        System.out.println(Arrays.toString( select.selectSort(逆序数组)));

        System.out.println("----------------");


//        System.out.println(Arrays.toString( bubble.bubbleSort(随机数组)));
//        System.out.println(Arrays.toString( bubble.bubbleSort(有序数组)));
//        System.out.println(Arrays.toString( bubble.bubbleSort(相同数组)));
//        System.out.println(Arrays.toString( bubble.bubbleSort(逆序数组)));


//        [2, 21, 22, 33, 37, 45, 48, 53, 69, 96]
//        [2, 21, 22, 33, 37, 45, 48, 53, 69, 96]

//        System.out.println(Arrays.toString( quick.quickSort(随机数组)));
//        System.out.println(Arrays.toString( quick.quickSort(有序数组)));
//        System.out.println(Arrays.toString( quick.quickSort(相同数组)));
//        System.out.println(Arrays.toString( quick.quickSort(逆序数组)));


//        System.out.println(Arrays.toString(随机数组));
//        System.out.println(Arrays.toString( merge.mergeSort(随机数组)));
//        System.out.println(Arrays.toString( merge.mergeSort(有序数组)));
//        System.out.println(Arrays.toString( merge.mergeSort(相同数组)));
//        System.out.println(Arrays.toString( merge.mergeSort(逆序数组)));


//        System.out.println(Arrays.toString(随机数组));
//        System.out.println(Arrays.toString( heap.heapSort(随机数组)));
//        System.out.println(Arrays.toString( heap.heapSort(有序数组)));
//        System.out.println(Arrays.toString( heap.heapSort(相同数组)));
//        System.out.println(Arrays.toString( heap.heapSort(逆序数组)));


    }

}
