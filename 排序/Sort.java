package sort;


/**
 * 其实也没那么难，只是你的心不够静！
 * 解淇茹啊！扛不住也得给我死扛！
 * 你懂吧，真正产生突破的阶段都不会太容易的！
 */
public class Sort {

    /**
     * 快速排序
     * 1.选择数据中的一个数字作为基准
     * 2.然后将无序数据进行partition，分为比基准大 和比基准小的 左右两个区间
     * 3.分别对左右两个区间进行处理
     * 4.直到小区间已经有序的时候就不需要处理
     * 小区间有序 就是区间内的元素个数等于0 或者等于1
     *
     * [lowIndex ,highIndex-1]
     * [highIndex,arr.length-1]
     *
     */

    public static void quickSort(int[] arr){
        //第一次代表需要对所有区间进行排序
        quickSortInternal(arr,0,arr.length-1);

    }

    private static void quickSortInternal(int[] arr, int lowIndex, int highIndex) {

        int size = highIndex- lowIndex +1;
        //递归结束的条件
        if(size <= 1) return;

        //遍历进行partition  返回keyIndex 经过partition之后最终所在的下标
        int keyIndex = partitionHover(arr,lowIndex,highIndex);
        //分别对左右区间进行相同的处理过程-- 递归方法
        quickSortInternal(arr,lowIndex,keyIndex-1);
        quickSortInternal(arr,keyIndex+1,highIndex);
    }



    //1.选择arr[lowIndex] 作为一个特殊的数字
    //2.保证能够对数组进行完整的遍历arr[lowIndex - highIndex]
    //3.保证小于等于的在左边，大于的在右边，但没有顺序要求

    /**
     * 第一种方法 hover方法
     * 特殊情况分析就是当你的数字选择在最左边的时候，你需要让最右边的先走
     * 最后跳出循环的位置 和key交换就可以了
     */
    private static int partitionHover(int [] arr, int lowIndex, int highIndex) {
        //选择最左边的一个数字
        int key = arr[lowIndex];
        int leftIndex = lowIndex;
        int rightIndex = highIndex;
        //相等的时候就不用继续比较了
        while (leftIndex < rightIndex){
            //选择最左边要从右边先走
            while (leftIndex < rightIndex && arr[rightIndex] >= key){
                rightIndex--;
            }
            //说明遇到了比key小的
            while (leftIndex < rightIndex && arr[leftIndex] <= key){ //一定要写成小于等于 刚开始的指向是key本身，如果不写左边就没有办法走都无法往后面走
                leftIndex++;
            }
            //说明遇到了比key大的
            //执行交换逻辑
            //为什么这里不用写等号的原因就是 我上面的条件是<
            //所以就算是因为不满足 leftIndex < rightIndex 而结束循环
            //交换也只不过是原地交换而已
            swap(arr,leftIndex,rightIndex);
            /*if(leftIndex <= rightIndex){
                //执行交换的流程
             swap(arr,leftIndex,rightIndex);
            }*/
        }
        swap(arr,lowIndex,leftIndex);
        return leftIndex;
    }

    /**
     * 第二种方法：挖坑方法
     */
    private static int partition挖坑(int [] arr, int lowIndex, int highIndex) {
        int key = arr[lowIndex];
        int leftIndex = lowIndex;
        int rightIndex = highIndex;
        while (leftIndex < rightIndex){

        }
    }
    /**
     * 堆排序
     * 对数据进行建大堆操作
     * 和直接排序相比较，优化的地方就是利用堆的特性可以使得找出最值的过程简化
     * 所以每一次就可以确定无序数组的第一个是最大值，这样查找最大值的时间复杂度降低
     * 之后将第一个值和无序数组的最后一个进行交换，然后对这个数字向下调整
     * 之后继续和无序数组的最后一个交换，然后继续向下调整
     * 就是一个不断的进行交换和向下调整的过程
     * 每一次操作包括一次交换和向下调整  使得无序区间一个最大的归位
     *
     *
     * 建堆的时间复杂度+向下调整的时间复杂度
     * n*log(n) + n *log(n) = 2n *log(n)
     * 最终的时间复杂的 n *log(n)
     * 最好最坏平均都一样
     * 空间复杂度o(1)
     * 不具备稳定性
     *
     * 可以利用小堆排倒序，但是做不到升序
     * 可以建小堆排升序吗？ -- 不可以
     * 堆顶就是最小的，之后要在剩余的数字中找最小的，把谁当作堆顶？堆的结构都被破坏掉了
     *
     */
    public static void heapSort(int[] arr) {
        //1.对数组进行建立大堆的操作
        createHeap(arr, arr.length);

        //2.进行选择的过程,一共需要arr.length-1 组
        for (int i = 0; i < arr.length - 1; i++) {

            //无序区间[ 0 - arr.length-i-1]
            //先交换
            swap(arr, 0, arr.length - 1 - i);
            //再向下调整
            //无序区间[ 0 - arr.length-i-2]
            //传入的是无序区间的个数以及需要向下调整的位置
            //为什么需要知道数组的长度，我一路向下调整，需要到什么时候结束呢
            //就是知道需要调整的位置是叶子结点就说明结束了
            //所以需要知道数组的长度，来判断当前是不是叶子结点
            adjustDown(arr, arr.length - i - 1, 0);
        }
    }

    /**
     * 建堆操作
     * 找到第一个非叶子结点，然后对其进行向下调整
     * 一次从后往前遍历
     * @param arr
     * @param length
     */
    private static void createHeap(int[] arr, int length) {
        for(int i = (length-2)/2;i >= 0;i--){
            adjustDown(arr,length,i);
        }
        /*//如何确定第一个非叶子结点 -- 那就是最后一个结点的父亲
        int theLastIndex = length-1;
        int theLastParentIndex = (theLastIndex-1)/2;
        for(int i = theLastParentIndex;i >= 0;i--){
            adjustDown(arr,length,i);
        }*/

    }

    /**
     * 递归版本
     * 循环版本
     * @param arr
     * @param length
     * @param index
     */
    private static void adjustDown(int[] arr, int length, int index) {
        /**
         * 循环版本，同时进行简化
         */
        //这里判断是不是叶子结点
        while (2 *index+1 < length) { //进来代表一定有叶子结点
            int maxIndex = 2*index+1;//那么就假定最大的是左孩子

            //找到左右子树的最大的 判断是不是需要和index交换
            int rightIndex = maxIndex+1;

            if(rightIndex < length && arr[rightIndex] > arr[maxIndex]){
                maxIndex++;
            }
            //孩子中最大的没有index位置的大
            if(arr[index] > arr[maxIndex]){
                break;
            }
            //执行交换的逻辑
            swap(arr,index,maxIndex);
            index = maxIndex;
        }

        /**
         * 递归版本
         */
        /*//找到左右子树的最大的和当前的index位置交换
        int rightIndex = leftIndex+1;
        int maxIndex = leftIndex;

        if(rightIndex < length && arr[rightIndex] > arr[index]){
            maxIndex = rightIndex;
        }

        if(arr[index] > arr[maxIndex]){
            return;
        }
        //执行交换的逻辑
        swap(arr,index,maxIndex);
        index = maxIndex;
        //继续进行向下调整
        adjustDown(arr,arr.length,index);*/
    }




    /**
     * 选择排序
     * 不具备稳定性
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
     * 但是会出现的一个问题就是 下标冲突的问题
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
           swap(arr,maxIndex,j-1);
            //出现下标冲突的问题
            if(minIndex == j-1){
                minIndex = maxIndex;
            }
            swap(arr,minIndex,i);
        }
    }

    /**
     *
     * @param arr
     * @param i 需要交换的下标
     * @param j
     */
    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
     * 插入排序的优化版
     * 希尔排序 -- 分组排序 --组内插排
     * 最好o(n)
     * 最坏 o(n2)
     * 平均的又优化
     * 不稳定
     */
    public static void shellSort(int[] arr){
        int gap = arr.length/2;
        while (true){
            insertSortGap(arr,gap);
            if(gap == 1){
                break;
            }
            gap = gap/2;
            //什么时候停止循环 -- 只有一组的时候就可以停下来了
        }
    }

    /**
     * 带间隔的插入排序
     * @param arr
     * @param gap
     */
    public static void insertSortGap(int[] arr,int gap){
        //并没有采用从第0个开始看的思路
        for(int i =gap;i < arr.length;i++){
            int key = arr[i];
            int j= 0;
            for(j = i-gap; j >= 0; j= j-gap){
                if(arr[j] > key){
                    arr[j+gap] = arr[j];
                }else {
                    break;
                }

            }
            //退出的时候 一种是因为j 越界了
            //还有一种是当前j的位置 小于等于 key ，那么就把key相当于再次填回去
            arr[j+gap] = key;
        }
    }

    /**
     * 插入排序
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

