package 排序;

public class Quick {
    public int[]  quickSort(int[] arr){
        quickSort(0,arr.length-1,arr);
        return arr;
    }


    //闭区间
    private void quickSort(int i , int j, int[] arr) {
        int size = j-i+1;
        if(size == 0 || size == 1) { //
            return ;
        }
        if(i < 0 || i >= arr.length || j < 0|| j >= arr.length) return;
        int keyIndex = partition2(i,j,arr);
        quickSort(i,keyIndex-1,arr);
        quickSort(keyIndex+1,j,arr);
    }

    private int partition1( int leftIndex, int rightIndex, int[] arr) {
        int key = arr[leftIndex];
        while (leftIndex < rightIndex){
            while ((leftIndex < rightIndex) && (arr[rightIndex] >= key)){
                rightIndex--;
            }
            arr[leftIndex] = arr[rightIndex];

            while (leftIndex < rightIndex && arr[leftIndex] < key){
                leftIndex++;
            }
            arr[rightIndex] = arr[leftIndex];
        }
        arr[leftIndex] = key;
        return leftIndex;
    }


    private int partition2( int leftIndex, int rightIndex, int[] arr) {
        int left = leftIndex;
        int key = arr[leftIndex];
        while (leftIndex < rightIndex){
            while ((leftIndex < rightIndex) && (arr[rightIndex] >= key)){
                rightIndex--;
            }
            while (leftIndex < rightIndex && arr[leftIndex] <= key){
                leftIndex++;
            }
            if(leftIndex < rightIndex){
                int temp = arr[rightIndex];
                arr[rightIndex] = arr[leftIndex];
                arr[leftIndex]= temp;
            }
        }

        //最后需要交换
        int temp = arr[leftIndex];
        arr[leftIndex] = key;
        arr[left] = temp;
        return leftIndex;
    }


    //快慢指针
    //保证慢指针左侧的都 <= key
    private int partition3( int leftIndex, int rightIndex, int[] arr) {

        int key = arr[leftIndex];


        int seperate = leftIndex+1;

        for(int fast = leftIndex+1;fast <= rightIndex;fast++){
            //当前位置小于key 就执行交换的逻辑
            if(arr[fast] <= key){ // 加上等号可以确保和key一样的放在前半段区间
                int temp = arr[seperate];
                arr[seperate] = arr[fast];
                arr[fast] = temp;
                seperate++;
            } //如果比key大什么也不做，继续往后遍历就好了
        }

        //把key 和 小于等于key的最后一个交换
        int temp = arr[seperate-1];
        arr[seperate-1] = arr[leftIndex];
        arr[leftIndex] = temp;

        //seperate 左边的 都是小于等于key 的  seperate-1 是key的位置
        return seperate-1;


    }


}
