package PriorityQueue;

/**
 * 在一个集合的数据中，找出最大值或者最小值
 *
 * 一颗逻辑上的完全二叉树，是可以通过数组保存的
 * 逻辑上是完全二叉树，但实际是用数组保存的
 *
 * 向下调整的前提条件是，只有需要调整的位置和它的两个孩子不知道是否满足，但其他地方一定满足
 *
 * 判断index的位置是不是叶子结点，如果是，直接返回
 * 找到两个孩子中的最小值
 * 小孩子的值和index 的位置进行比较
 * == >  直接turn
 * < 就直接交换，把最小的孩子视为index 循环再次
 *
 * 怎么判断index的位置是不是叶子（原来的想法是，看它的左右结点是不是null，但是思想误区是现在是一个数组，不能用是不是null来判断）
 * 所以没有孩子就是叶子结点，--因为是一个完全二叉树，所以没有左孩子就是叶子结点，而是和size判断
 *
 * 确定有左孩子之后，就去判断有没有右孩子
 *
 * 小堆不一定有序，有序（升序的）的一定是小堆
 *
 */
public class Priority {
    /**
     * 向上调整
     * index 是需要向上调整的起始位置
     * 或者也可以写成循环的方式
     */
    public static void adjustUp(int[] array,int size,int index){
        //如果是要调整的是根调整结束
        if(index == 0) return;
        if(index >= size) return;
        //index父节点
        int parentIndex = (index -1)/2; // 这里的下标永远大于等于0
        //和父亲比较
        if (array[parentIndex] <= array[index]){
            return;
        }
        //交换
        int t = array[index];
        array[index] = array[parentIndex];
        array[parentIndex] = t;
        //把父节点看作index 接续循环
        index = parentIndex;
        adjustUp(array,size,index);
    }
    /**
     * 建堆
     * 找到第一个非叶子结点
     * 第一个非叶子结点其实就是最后一个结点的的父节点
     * 最坏的情况，不仔细算就是 o(n * log n)仔细算其实是o（n)
     *
     */
    public static void createHeap(int[] array,int size){
        //找到最后一个非叶子结点，然后不断进行向下调整
        int lastIndex = size -1;
        int lastParentIndex = (lastIndex -1)/2;
        //从【lastParent ，0】 不断地进行向下调整
        for(int i = lastParentIndex;i >= 0;i--){
            adjustDown(array,size,i);
        }

    }
    /**
     * 向下调整
     * 因为向下调整是有前提的，所以需要建堆
     * 时间复杂度 最坏就是log（n)
     * @param array
     * @param size
     * @param index
     */
    public static void adjustDown(int[] array,int size,int index){
        int leftIndex = 2*index+1;
        //1.判断index 是不是叶子
        if(leftIndex >= size) return;//说明需要调整的结点是叶子结点 直接跳出

        //现在已经确定了它有左孩子
        //2.找最小的的孩子
        //需要找到它的孩子里面的最小值 --所以需要判断有没有右孩子
        int midIndex = leftIndex;//这种先赋值之后再次进行更改的做法比较不错，可以学习
        int rightIndex = leftIndex+1;
        if(rightIndex < size && array[rightIndex] < array[index]){
            //说明最小的孩子是右孩子
            midIndex = rightIndex;
        }

        //3.比较最小孩子的值
        if(array[index] <= array[midIndex]){
            return;
        }


        //4.交换
        int t = array[index];
        array[index] = array[midIndex];
        array[midIndex] = t;

        //5.把最小的孩子看作index 继续循环
        index = midIndex;

        adjustDown(array,size,index);

        /*if(rightIndex >= size){
            //说明没有右孩子，那么继续判断左孩子和index的值
            if(array[index] > array[leftIndex]){
                //交换
                int temp = array[index];
                array[index] = array[leftIndex];
                array[leftIndex] = temp;
            }
            //如果小于等于就不用交换了
        }else {
            //说明有右孩子
            //那么就在左右两个孩子里面找到最小的那个孩子
            int min = Math.min(array[leftIndex],array[rightIndex]);
            if(min >= array[index]){
                //不用执行交换的逻辑
                return;
            }else
            if (){
                //都是和左孩子交换
                int temp = array[index];
                array[index] = array[leftIndex];
                array[leftIndex] = temp;
            }else {
                //和右孩子交换
                int temp = array[index];
                array[index] = array[rightIndex];
                array[rightIndex] = temp;
            }
        }
*/
    }


}
