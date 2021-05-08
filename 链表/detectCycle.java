package ListNode;

//142. 环形链表 II
//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
public class detectCycle {
    /**
     * 这个链表有一个特性就是两个相遇的时候
     * 让快指针重新回到头部 然后和慢指针一人一步走，再次相遇的时候就是换的第一个结点
     *
     * 问：为什么会这样？
     * 答：公式推导。
     *  链表共有a+b(环的长度)个结点
     *  fast 走了f步 slow走了s步
     * f = 2s(因为一人一步，一人两步)
     * fast 比slow多走了 n个环的长度 f = s+nb
     * f = 2nb  s = nb 即fast和slow 指针分别走了 2n2n，nn 个 环的周长
     *
     * 单纯分析，不看上面的公式走到环的入口 其实需要走的步数是 a+nb
     * 那么此时slow 已经走了nb 那么slow再走a 即可到达入口结点
     * 可是这个a是不确定的，但是刚好一个指针从头走到入口就是a
     * 让fast 指针指向head  f=a 步时，slow 指针走到步s = a+nb 两指针重合，并同时指向链表环入口 。
     * 返回slow指针指向的节点。
     *
     * */

    public ListNode solution(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null; //说明链表没有环
        }
        //如果不是的话说明链表有环，那么就让fast指向头部
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        //跳出循环，说明相等了，就可以返回了
        return fast;
    }

    //下面的代码更简洁一些
//    ListNode slow = head, fast = head;
//        while (fast != null && fast.next != null) {
//        //快慢指针，快指针每次走两步，慢指针每次走一步
//        fast = fast.next.next;
//        slow = slow.next;
//        //先判断是否有环，
//        if (slow == fast) {
//            //确定有环之后才能找环的入口
//            while (head != slow) {
//                //两相遇指针，一个从头结点开始，
//                //一个从相遇点开始每次走一步，直到
//                //再次相遇为止
//                head = head.next;
//                slow = slow.next;
//            }
//            return slow;
//        }
//    }
//        return null;


}
