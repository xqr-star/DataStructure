package day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NearLess {

    /**有重复元素的单调栈
     * 把重复元素看作是一整个处理
     *
     */

    public static void main(String[] args) {
        //int[] arr = {3,1,3,4,3,5,3,2,2};
        int[] arr = {9,3,1,3,4,3,5,3,2,2};
        int[][] res = getNearLess(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }
    }

    private static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];//构造返回的的结果集数组
        //栈是一个单调递增的栈 -- 栈的数据类型变成一个list
        Stack<List<Integer>> stack = new Stack<>();
        for(int i =0 ;i < arr.length;i++){

            //如果即将入栈的元素小于当前的栈顶元素
            //这一步操作同时也是在寻找可以入栈的位置
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]){//这里因为是比较操作所以可以不用写那么长
                List<Integer> topList = stack.pop();
                //那么当前栈的左右最小元素就可以确定
                //因为同一个链表的左边小的是同一个所以定义在外面就可以啦
                //int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(0);
                //虽然元素相同的放在一个list里面，但但是获取元素的时候，还是哟啊注意入栈的顺序
                int leftLessIndex = stack.isEmpty()?-1:stack.peek().get(stack.peek().size()-1);//获取相同元素最后一个入栈的序号
                //循环处理这个链表
                for(Integer n: topList){
                    res[n][1] =i;

                    res[n][0] = leftLessIndex;
                }
            }
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)] ){ // 但是相同的放到一个list里面去
                stack.peek().add(i);
            }else {
                //刚开始栈肯定是空的--但是这个不能写在最前面，因为那个比栈顶元素小的还没找到它该放的位置
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        //清算栈里面元素
        while (!stack.isEmpty()){
          List<Integer> topList = stack.pop();
          int leftLessIndex = stack.isEmpty()? -1:stack.peek().get(stack.peek().size()-1);
          for(Integer e :topList){
              res[e][1] = -1;

              res[e][0] = leftLessIndex;
          }
        }
        return res;
    }
}


