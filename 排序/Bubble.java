package 排序;

public class Bubble {
    //每一轮找出一个最大的一共要进行n-1轮次
    //带优化的
    public int[] bubbleSort(int[] arr){
        for(int i = 0; i< arr.length-1;i++){
            boolean flag = false;
            int j = 0;
            for( j = 0;j< arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if(!flag) {
                System.out.println("我优化啦！"+ i);
                break;
            }

        }
        return arr;
    }
}
