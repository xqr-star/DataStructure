package HashTable;

//元素类型Integer
public class MyHashTable {
    //1.需要一个数组
    private Node[] arr = new Node[11];
    private int size ;
    //true ：key之前不在hash表中
    //flase：key之前已经有了
    public boolean insert (Integer key) {
        //1.把对象转换从int类型得到一个下标
        //hashCode() 核心
        int hashValue = key.hashCode();
        //2.hashValue 可能不是一个合法下标
        int index = hashValue % arr.length;
        //3.遍历index对于的链表，确定key是否存在在元素中
        Node cur = arr[index];
        while (cur != null){
            if(key.equals(cur.key)){ // equals核心
                return false;
            }
            cur = cur.next;
        }
        //循环退出的时候，就是key不在链表中，所以执行插入的逻辑
        //4. 把key装入结点中，并插入index所对应的链表里面（头插尾插都可以目前来说）
        //这里使用头插 arr[index] 里面的元素类型是结点，所以arr[index] 里面的值是所有
        //hash后为index 的链表的头结点
        Node node = new Node(key);
        node.next = arr[index];
        arr[index] = node;

        //5.维护元素个数
        size++;

        //6.通过维护负载因子进而维护较低的冲突率
        if(size / arr.length*100 >= 75){
            扩容();
        }
        return true;
    }

    //搬运的时间复杂度是o(n)
    private void 扩容() {
        Node[] newArray = new Node[arr.length *2];
        //搬原来的元素
        //同时更改对象里面的成员属性的指向关系
        //不能单纯的把原来的元素搬出来，因为下标是和数组长度有关系的
        //数组长度变了，下标也会变
        // 所以需要重写计算他的下标重新插入

        //外层遍历每一个数组
        for(int i =0 ;i < arr.length;i++){
            //每一个数组上面是一个链表，所以还需要遍历链表
            Node cur = arr[i];
            while (cur != null){
                //高效的做法是搬运结点，但写起来复杂
                //采用的是复制元素
                Integer key = cur.key;
                int hashValue = key.hashCode();
                int index  = hashValue % newArray.length;
                Node node = new Node(key);

                //然后采用头插入的办法/尾插
                node.next = newArray[index];
                newArray[index] = node;

                cur = cur.next;
            }
        }

        arr = newArray;
    }

    //如果有并且删除成功了，就返回true
    //如果没有那么就返回false
    public boolean remove(Integer key){
        //1.将对象转换成int类型的下标
        int hashValue = key.hashCode();
        //2.下标合法化
        int index = hashValue % arr.length;
        //3.遍历
        Node cur = arr[index];
        Node parent = null;
        while (cur != null ){

            if(cur.key.equals(key)){//注意这里一定要判断parent
                //删除
                if(parent != null){
                    parent.next = cur.next;
                }else { //  什么结点没有前驱，只有头节点没有说明链表的头节点是要删除的结点
                     arr[index] = cur.next;
                }
                size--;
                return true;
            }
            parent = cur;
            cur = cur.next;
        }
        return false;
    }

    public boolean contains(Integer key){
        //1.将对象转换成int类型的下标
        int hashValue = key.hashCode();
        //2.下标合法化
        int index = hashValue % arr.length;
        Node cur = arr[index];
        while (cur != null) {
            if(cur.key.equals(key)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


}
