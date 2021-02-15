package PriorityQueue;

/**
 * 自己实现优先级队列
 * 利用数据结构的堆
 */
public class MyPriorityQueue {
    private Integer[] array;
    private int size;
    public MyPriorityQueue(){
        //简单起见 不扩容
        array = new Integer[100];
        size = 0;
    }
    public Integer element (){
        if(size == 0){
            throw new RuntimeException("数组是空的");
        }
        //如果不是空的，直接返回堆顶的元素就可以了
        return array[0];
    }
    /**
     *删除实现的操作就是 把数组的第一个元素删除，
     * 然后让最后一个元素来顶替第一个元素的位置，之后进行向下调整即可
     */
    public Integer remove(){
        if(size == 0){
            throw  new RuntimeException("数组是空的");
        }
        int e = array[0];
        array[0] = array[size-1];

        //维护size
        size--;

        //向下调整
        //只是说这里的向下调整的index变成了0的位置
        //Priority.adjustDown();或者去调用之前写好的
        adjustDown(0);
        return e;
    }
    private void adjustDown(int index){
        int leftIndex = index * 2+1;
        if(leftIndex >= size) return;
        int minIndex = leftIndex;
        int rightIndex = leftIndex+1;
        if(rightIndex < size && array[rightIndex] < array[leftIndex]){
            minIndex = rightIndex;
        }
        if(array[index] <= array[minIndex]) {
            return;
        }
        //交换
        int t = array[index];
        array[index] = array[minIndex];
        array[minIndex] = t;
        index = minIndex;
        adjustDown(index);
    }

    /**
     * 插入的操作
     * 采用尾插，使用向上调整
     * 乞丐变皇帝 o(log n)
     */
    public void add(Integer e){
        array[size] = e;
        size++;
        adjustUp(size-1);

    }
    private void adjustUp(int index){
        if(index == 0) return;//要调整的是不是根
        while (index != 0){
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
        }
    }


}
