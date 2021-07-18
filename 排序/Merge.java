package 排序;

public class Merge {
    public int[]  mergeSort(int[] arr){
        mergeSortInternal(arr,0,arr.length-1);
        return arr;
    }


    //闭区间
    //通过把数组划分成两份 然后 分别在对左右区间依次划分直到区间里面没有元素 或者元素的个数是1 然后进行数组的合并
    private void mergeSortInternal(int[] arr, int i, int j) {
        int size = j-i+1;
        if(size == 0 || size == 1) return;
//        if(i < 0 || i >= arr.length || j < 0|| j >= arr.length) return;
        //进行不断的无脑二分
        int midIndex = (i+j)/2;
        mergeSortInternal(arr,i,midIndex);
        mergeSortInternal(arr,midIndex+1,j);

        //需要传递的参数 是数组的头和尾巴
        mergeArr(arr,i,midIndex,j);
    }

    private void mergeArr(int[] arr, int leftIndex, int midIndex, int highIndex) {
        int length = highIndex - leftIndex+1;
        int[] newArr = new int[length];

        int left = leftIndex;
        int right = midIndex+1;

        int i = 0;
        while (left <= midIndex && right <= highIndex ){
            if(arr[left] <= arr[right]){
                newArr[i++] = arr[left];
                left++;
            }else {
                newArr[i++] = arr[right];
                right++;
            }
        }

        while (left <= midIndex){
            newArr[i++] = arr[left];
            left++;
        }
        while (right <= highIndex){
            newArr[i++] = arr[right];
            right++;
        }


        //从哪个数组开始哪里 赋值    替换到哪一个数组的哪里   替换的长度
        System.arraycopy(newArr,0,arr,leftIndex,length);

    }
}
