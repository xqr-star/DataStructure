package day09;

import java.util.Scanner;
import java.util.Stack;

/**
 * 单调栈结构--
 */

public class NearLessNoRepeat {



    /**
     * 无重复元素的单调栈
     * 构建一个单调递增的栈
     * @param args
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int[] arr = new int[num];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            int[][] res = getNearLessNoRepeat(arr);
            //最后遍历输出
            for(int i = 0;i < arr.length;i++){
                System.out.println(res[i][0]+" "+ res[i][1]);
            }
        }

    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int [][] res = new int[arr.length][2];//arr.length 存放的是原数组的下标 后面的2 表示的是左右两边的
        Stack<Integer> stack = new Stack<>();//维护栈中的元素是一个单调递增的
        for(int i = 0;i < arr.length;i++) {//这是当前访问的元素下标数组里面的元素是下标
            //栈里面存放的是下标
            while ( !stack.isEmpty() && arr[i] < arr[stack.peek()]){ //栈不为空 并且 比栈顶元素那么栈顶元素的左右最雄安就找到了
                int popIndex = stack.pop();
                int leftLesIndex = stack.isEmpty()? -1:stack.peek();
                res[popIndex][1] = i ; // 栈顶右侧是最小元素是当前访问的元素
                res[popIndex][0] = leftLesIndex;//栈顶左侧的最小元素是栈顶原色的下一个
            }
            //如果比栈顶元素大或者栈式空的那么就进栈--下标
            stack.push(i);//当前元素入栈
        }
        //遍历完整个数组，对栈中元素进行清算
        while (!stack.isEmpty()){
            int popIndex = stack.pop();
            res[popIndex][1] = -1;//右边的就没有
            //处理左边的
            int leftLessIndex = stack.isEmpty()? -1:stack.peek();
            res[popIndex][0] = leftLessIndex;
        }
        return res;

    }



/**
 * 单调栈结构--硬做出来!
 */


    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            int [] arr = new int[num];
            for(int i =0 ;i <arr.length;i++){
                arr[i] = sc.nextInt();
            }
            for(int j =0 ;j < arr.length;j++){
                find(j,arr);
            }
        }
    }
    public  static void find(int i,int[] arr){
        int left = i -1;
        int right = i+1;

        //遍历循环数组，注意如果走到最后还没有也要返回-1
        for(;left >=0;left-- ){
            if(arr[left] < arr[i]){
                System.out.print(left+" ");
                break;
            }
        }
        if(left < 0) System.out.print(-1+" ");   //处理一开始就越界的情况和没有的


        //遍历循环数组，注意如果走到最后还没有也要返回-1
        for(;right <= arr.length -1;right++ ){
            if(arr[right] < arr[i]){
                System.out.print(right+" ");
                break;
            }
        }
        if(right > arr.length-1) System.out.print(-1);  //处理右边y开始就越界的情况和没有的


        System.out.println();
    }
}
