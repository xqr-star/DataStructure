package 排序;

/**
 * 插入排序
 */
public class Insert {


    /**
     * 有序区间 [0,i)
     * 无序区间 [i,arr.length)
     * @param arr
     */
    public  int[]  insertSort(int[] arr){
        //插入排序要执行的次数
        for(int i = 0;i < arr.length-1;i++){
            int key = arr[i+1];
            //从后往前遍历有序区间
            int j = 0;
            for(j = i;j >=0;j--){
                //找到要插入的位置
                if(arr[j] > key){
                    arr[j+1] = arr[j];//当前的元素往后挪动
                }else {
                   break;
                }
            }
            arr[j+1] = key;
        }
        return arr;
    }
}
