package 排序;

public class Heap {

    //数组升序排序

    /**
     * 对无序区间的数组 建立一个大根堆 然后和无序的最后一个交换 进行向下调整
     * @param arr
     * @return
     */
    public int[] heapSort(int[] arr){

        //建立一个大堆
        createHeapBig(arr,arr.length);
        //需要比较的次数
        for(int i=0;i< arr.length-1;i++){
            //无序区间 [0,arr.length-1]
            int temp = arr[0];
            arr[0] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
            //这里一定要传入size 并且不是数组的长度 最大的值 已经走了 堆的size 要变化
            adjustDownBig(arr,0,arr.length-1-i);
        }
        return arr;
    }

    /**
     *
     * 只要是使用向下调整的思路 都要找到第一个非叶子结点
     * 由于要使用向下调  找到第一个非叶子结点
     * 和建立什么堆没关系
     * @param arr
     * @param length
     */
    private void createHeapBig(int[] arr, int length) {
        for(int i = (length-2)/2;i >= 0;i--){
            adjustDownBig(arr,i,length);
        }
    }



    /**
     * 向下调整只是一种思想并不是说
     * 向下调整只可以 用来建立小堆
     * 它的使用前提是 除了 当前要调整的和它的左右孩子
     * 其他位置均满足 堆的性质即可！
     * @param arr
     * @param index
     * @param length
     */

    private void adjustDownBig(int[] arr, int index, int length) {

        int leftIndex = 2* index+1;
        //如果当前的结点是叶子结点就不要调整了
        if(leftIndex >= length) return;

        int maxIndex = leftIndex;
        int rightIndex = 2*index+2;
        if(rightIndex < length && arr[rightIndex] > arr[leftIndex]){
            maxIndex = rightIndex;
        }
        if(arr[index] > arr[maxIndex]) return;

        //交换
        int temp = arr[index];
        arr[index] = arr[maxIndex];
        arr[maxIndex] =temp;

        adjustDownBig(arr,maxIndex,length);
    }



    /**
     * 建一个小根堆
     * 找到数组的第一个非叶子结点开始进行向下调整
     * 第一个非叶子结点就是最后一个结点的父节点
     */
    public void createHeapSmall (int[] arr){
        int lastIndex = arr.length-1;
        int index = (lastIndex-1)/2;
        for(int i = index;i >= 0;i++){
            adjustDownBig(arr,index,arr.length);
        }
    }
    /**
     * 向下调整
     * 除了需要调整的 位置以外 其他的位置都需要满足堆的特质
     *
     * 也就是说  向下调整可以找出最小的也可以找出最大的！
     *
     * @param arr
     * @param index
     */
    public void adjustDownSmall(int[]arr,int index,int size){
        //判断要调整的位置是不是叶子节点 如果是就退出
        int leftIndex = 2 *index + 1 ;
        int rightIndex = 2 * index + 2;
        if(leftIndex >= size) return;

        //说明有左孩子
        int minIndex = leftIndex;
        //判断是否有有孩子 并且右孩子的值比左孩子的小 更新minIndex
        if(rightIndex < size && arr[rightIndex] < arr[leftIndex]){
            minIndex = rightIndex;
        }

        //然后让左右孩子的最小值和 index 比较看是否需要调整
        if(arr[index] < arr[minIndex]) return;

        int temp = arr[index];
        arr[index] = arr[minIndex];
        arr[minIndex] = temp;


        //然后更新要调整的位置
        index = minIndex;
        adjustDownSmall(arr,minIndex,size);
    }

    /**
     * 向上调整
     * 作为补充学习
     * 需要判断当前是不是根
     * @param arr
     * @param index
     */

    public void adjustUp(int[]arr,int index,int size) {
        //如果当前的结点 是叶子结点 或者当前结点是根节点退出
        if(index == 0) return;


        int parentIndex = (index-1)/2;

        if(arr[parentIndex] > arr[index]) return;


        int temp = arr[index];
        arr[index] = arr[parentIndex];
        arr[parentIndex] = temp;

        index = parentIndex;
        adjustUp(arr,index,size);
    }
}
