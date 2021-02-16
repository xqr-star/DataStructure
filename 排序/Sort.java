package sort;

/**
 * 其实也没那么难，只是你的心不够静！
 * 解淇茹啊！扛不住也得给我死扛！
 * 你懂吧，真正产生突破的阶段都不会太容易的！
 */
public class Sort {
    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        //无序区间[0 - arr.length-i)
        //有序区间[arr.length -i,arr.length)

        //多少次选择的过程
        for(int i = 0;i < arr.length-1 ;i++) {
            int maxIndex = 0;
            int j = 0;
            //每一次都要在序区区间里面找到最大的，然后和无序区间的最后一个交换
            for (j =0; j < arr.length - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            //找到了无序区间里面最大执行交换的逻辑
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[j-1];
            arr[j-1] = temp;
        }
    }
    /**
     * 选择排序变形
     * 每次找出最小的和最大的
     * 无序区间 [i ， arr.lenght -i)
     * 选择的次数变为一半就可以l
     */
    public static void selectSort2(int[] arr){
        for(int i = 0;i < (arr.length)/2 ;i++) {
            int maxIndex = i;
            int minIndex = i;
            int j = 0;
            //每一次都要在序区区间里面找到最大的，然后和无序区间的最后一个交换
            //最小的和无序区间里面的第一个执行交换的逻辑
            //所以无序区间的左右两边一定要是变化动态的。
            for (j =i; j < arr.length - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            //这里出现的一个问题是 如果最大最小刚好是无序数组的边界就只剩下两个的时候
            //明明已经交换，在最大的地方交换过一次之后，又会产生交换的逻辑
            //找到了无序区间里面最大和无序区间里面的最后一个执行交换的逻辑
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[j-1];
            arr[j-1] = temp;
            //找到了无序区间里面最小的和无序区间里面的第一个执行交换的逻辑
            int temp2 = arr[minIndex];
            arr[minIndex]= arr[i];
            arr[i] = temp2;
        }
    }


    /**
     * 冒泡排序及其优化版本
     * 冒泡的过程，只在无序区间中比较
     */
    public static void bubbleSort(int[] arr){
        //【无序，有序】
        //需要多少次冒泡--每冒泡一次一个最大的归位
        for(int i = 0;i < arr.length-1;i++){
            //无序：[0 - arr.length-i)
            //有序：[arr.length - i, arr.length)
            boolean isSort = true;//
            for(int j = 0;j < arr.length-i-1;j++){
                //和相邻的比较
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSort = false;
                }
            }
            if(isSort) break;
        }
    }

    /**
     * 区间被分为两部分 左边是有序区间 右边是无序区间
     * 每次从无序区间拿出一个数据，然后依次和有序区间比较，选择合适的位置插入
     *
     */
    public static  void insertSort(int[] arr){
        //数据一共有arr.length 个
        //所以子操作需要执行的次数 arr.length-1
        for(int i = 0;i < arr.length -1;i++){
            //有序区间[0 - i]
            //无序区间[i+1 - arr.length)
            //每次拿出来的数字是无序区间的第一个 [i+1]
            int key = arr[i+1];
            //依次在有序区间进行比较--采用从后往前的比较方式
            //之所以这么做是有原因的
            int j = 0;
            for(j = i;j >= 0;j--){
                //[j] 就是需要和key比较的数字
                //key  arr[j]
                //比较可能出现三种情况 大于 等于 小于
                if(key < arr[j]) {
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+1] = key;
        }
    }
//这里是没有完全理解key的存放位置
//是错误的思路
//if(key >= arr[j]){ //不移动保证稳定性
//        arr[j+1] = key;
//        break;//结束当此的子操作
//        }else {
//        arr[j+1] = arr[j];
//        }

}

