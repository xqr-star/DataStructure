package 排序;

public class Select {
    /**
     * 每一次在无序区间里面选择最大的
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr){
        //外循环是比较次数 操作只在无序区间里面
        for(int i = 0; i< arr.length-1;i++){
            int maxIndex = 0;
            //找无序区间里面最大的元素和无序区间的最后一个元素交换
            //无序[0,arr.length -1-i)
            //有序  [arr.length -1-i,arr.length)
            int j =0 ;
            for(j = 0; j < arr.length-i; j++){
               if(arr[j] > arr[maxIndex]) {
                   maxIndex = j;
               }
            }
            int temp = arr[j-1];
            arr[j-1] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        return arr;
    }
}
