package map;

//自己实现map
//用搜索树实现
public class myTreeMap {
    private TreeNode root;

    public Integer put(String key,Integer value){
        TreeNode cur = root;
        TreeNode parent  = null;
        if(root == null) {
            root  = new TreeNode(key,value);
            return null;
        }
        int cmp = 0;
        while (cur != null){
            cmp = key.compareTo(cur.key);
            if(cmp == 0){
                //发现相等需要替换value
                Integer olaValue = cur.value;
                cur.value = value; // 要返回原来的value
                return olaValue;
            }else if(cmp > 0) {
                parent = cur;
                cur = cur.right;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }
        //出了循环，意味着树里面没有，需要插入
        //需要看看是往左边还是有右边插入
        TreeNode node =  new TreeNode(key,value);
        if (cmp > 0){
          parent.right = node;
        }else {
           parent.left = node;
        }
        //原理不存在就返回null
        return null;
    }

    public Integer get(String key){
        TreeNode cur = root;
        while (cur != null){
            int cmp = key.compareTo(cur.key);
            if(cmp == 0){
                return cur.value;
            }else if(cmp > 0){
                cur = cur.right;
            }else {
                cur = cur.left;
            }
        }
        //出来的是u没找到就是 返回null
        return null;

    }

    public Integer getOrDefault(String key,Integer value){
        Integer v = get(key);
        if( v != null){
            return v;
        }
        //key 不存在返回value
        return value;

    }



}
