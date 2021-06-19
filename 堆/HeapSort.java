package heap;

public class HeapSort {
    public static void heapSort(int[] array){
        //1.建大堆
        createHeap(array,array.length);

        //2.进行选择排序的过程，一共需要array.length-1组
        for(int i = 0;i < array.length-1;i++){
            //无序区间[0,array.length-i)
            int temp = array[0];
            array[0] = array[array.length-i-1];
            array[array.length-i-1] = temp;
            adjustDown(array,array.length-i-1,0);
        }
    }

    private static void adjustDown(int[] array,int size, int index){
        int leftIndex = 2 * index + 1;
        while(leftIndex < size){
            int maxIndex = leftIndex;
            int rightIndex = leftIndex+1;
            if(rightIndex < size && array[rightIndex] > array[leftIndex]){
                maxIndex = rightIndex;
            }
            if(array[index] >= array[maxIndex]){
                break;
            }

            int temp = array[index];
            array[index] = array[maxIndex];
            array[maxIndex] = temp;

            index = maxIndex;
            leftIndex = 2 * index + 1;
        }
    }

    private static void createHeap(int[] array,int size){
        int lastIndex = size - 1;
        int lastParentIndex = (lastIndex-1)/2;
        for(int i = lastIndex;i >= 0;i--){
            adjustDown(array,size,i);
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{2,5,3,4,9,6,1,7,8};
        int[] array2 = new int[]{1,2,3,4,5,6,7,8,9};
        int[] array3 = new int[]{1,1,1,1,1,1,1,1,1};
        int[] array4 = new int[]{9,8,7,6,5,4,3,2,1};
        heapSort(array1);
        heapSort(array2);
        heapSort(array3);
        heapSort(array4);
        for(int e : array1){
            System.out.print(e+" ");
        }
        System.out.println();
        for(int e : array2){
            System.out.print(e+" ");
        }
        System.out.println();
        for(int e : array3){
            System.out.print(e+" ");
        }
        System.out.println();
        for(int e : array4){
            System.out.print(e+" ");
        }
        System.out.println();
    }
}
