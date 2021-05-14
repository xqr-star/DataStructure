package ListNode;

//2. 两数相加
public class addTwoNumbers {
    /**
     * 采用最简单的遍历方式
     * 然后处理进制的方式
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //同时遍历两个链表 两个链表都不为空的时候进行相加的操作
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode fakeHead = new ListNode(-1);
        ListNode cur = fakeHead;//表示需要返回的链表的当前指向的结点
        int temp = 0;
        int res = 0;
        while(cur1 != null && cur2 != null){
            res = (cur1.val + cur2.val+temp)%10;
            cur.next = new ListNode(res);
            cur = cur.next;
            temp = (cur1.val + cur2.val+temp)/10;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        while(cur1 != null){
            res = (temp+cur1.val)%10;
            cur.next = new ListNode(res);
            temp = (temp+cur1.val)/10;
            cur = cur.next;
            cur1 = cur1.next;
        }
        while(cur2 != null){
            res = (temp+cur2.val)%10;
            cur.next = new ListNode(res);
            temp = (temp+cur2.val)/10;
            cur = cur.next;
            cur2 = cur2.next;
        }
        //出现的问题是最后虽然遍历完了整个链表，但是还是有可能出现需要多加一个进制的情况
        //如果链表遍历结束后，temp >0
        //还需要在链表的后面附加一个节点
        if(temp != 0){
            cur.next = new ListNode(temp);
        }
        return fakeHead.next;
    }
}
