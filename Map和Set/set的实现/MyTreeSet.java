package set的实现;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//直接实现set的接口
//有些方法不实现
//抛出UnSupportedOperationException
//java 的实现是用TressSet来实现的
public class MyTreeSet implements Set<Integer> {
    private  TreeNode root;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Comparable<Integer> comparable = (Comparable<Integer>) o;

        TreeNode cur = root;
        while (cur != null){
            int cmp = comparable.compareTo(cur.key);
            if(cmp ==0){
                return true;
            }else if(cmp < 0){ // 比较的元素比当前这个元素小
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }

        return false;
    }

    @Override
    //中序遍历，就是返回的要有顺序
    public Iterator<Integer> iterator() {
        throw  new UnsupportedOperationException();
        //他说这是欠的代码后期会补回来
    }

    @Override
    public boolean add(Integer integer) {
        if(root == null){
            TreeNode node  = new TreeNode(integer);
        }

        TreeNode parent = null;
        TreeNode cur = root;

        int cmp = 0;
        while (cur != null){
            cmp = integer.compareTo(cur.key);
            if(cmp == 0) {
                return false;
            }else if(cmp > 0){
                parent = cur;
                cur = cur.right;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }

        if(cmp < 0) {
            parent.left = new TreeNode(integer);
        }else {
            parent.right = new TreeNode(integer);
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    @Override
    public Object[] toArray() {
        throw  new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
       throw new UnsupportedOperationException();
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }




    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException();
    }

    //求set 和c 的交集
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

}
