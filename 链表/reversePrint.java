package Node;

import java.util.Stack;

//06 从尾到头打印链表
public class reversePrint {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        //ListNode head = null;
        ListNode node = new ListNode(2);
        ListNode node1 = new ListNode(3);
        head.next = node;
        node.next = node1;
        solution(head);
    }

    public static int[] solution(ListNode head) {
        //基本解法依次到链表的最后一个
        //放到栈里面然后实现出栈
        //if(head == null) return null;因为这个题最后是要返回一个数组
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null){
            stack.push(cur.val);
            cur = cur.next;
        }
        int[] arr = new int[stack.size()];//没有元素的时候，数组大小设置为0
        //你在出栈的时候，切记用stack.size 这个值是动态变化的
        for(int i=0 ;i <arr.length;i++){
            arr[i] = stack.pop();
        }
        return arr;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
