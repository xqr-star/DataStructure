package heap;

import java.util.ArrayList;

public class TopK {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k){
        ArrayList<Integer> list = new ArrayList<>();
        if(k > input.length )return list;
        heapSort(input);
        for(int i = 0;i < k;i++){
            list.add(input[i]);
        }
        return list;
    }
    public static void heapSort(int[] input){
        createHeap(input);
        for(int i = 0;i < input.length-1;i++){
            int temp = input[0];
            input[0] = input[input.length - i - 1];
            input[input.length - i - 1] = temp;
            adjustDown(input,input.length-i-1,0);
        }
    }
    public static void createHeap(int[] input){
        int lastIndex = input.length - 1;
        int lastParentIndex = (lastIndex-1)/2;
        for(int i = lastParentIndex;i >= 0;i--){
            adjustDown(input,input.length,i);
        }
    }
    public static void adjustDown(int[] input,int size,int index){
        int leftIndex = index*2+1;
        while(leftIndex < size){
            int maxIndex = leftIndex;
            int rightIndex = leftIndex + 1;
            if(rightIndex < size && input[rightIndex] > input[leftIndex]){
                maxIndex = rightIndex;
            }

            if(input[index] >= input[maxIndex]){
                break;
            }
            int temp = input[maxIndex];
            input[maxIndex] = input[index];
            input[index] = temp;

            index = maxIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{4,5,1,6,2,7,3,8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(input,4);
        for(int e : list){
            System.out.print(e + " " );
        }
    }
}
