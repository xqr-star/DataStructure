package binary_search_tree;

public class BST {
    private Node root;

    /**
     * 构造一颗二叉搜索树
     */
    public BST() {
        root = null;
    }

    /**
     * 搜索树过程是和对象和二叉搜索树有关的，所以不可以写成静态方法
     * 在外面把树传给我就可
     * @param e
     * @return
     */
    public boolean search(Integer e) {

        Node cur = root;
        while (cur != null) {
            int cmp = cur.key.compareTo(e);
            if (cmp == 0) return true;
            if (cmp > 0) cur = cur.left;
            if (cmp < 0) cur = cur.right;
        }
//        while (cur != null){
//            if(cur.key.equals(e)) return true;
//            if(cur.key > e) cur = cur.left;
//            if(cur.key < e) cur = cur.right;
//        }
        return false;
    }

    /**
     * 插入操作
     * 非静态方法和对象有关系
     * 插入的是一个结点
     * 由于搜索树里面不允许重复的key 所以不可以
     * <p>
     * 实际上是先查找的过程。插入的过程一定是发生在查找不存在的过程
     */
    public void insert(Integer e) {
        //没有考虑特殊情况
        if (root == null) {
            root = new Node(e);
            return; //一定要记得return
        }
        //始终保持parent是cur 的父节点
        Node parent = null; //需要对父节点进行保存
        Node cur = root;

        //先经历依次查找
        int cmp = 0; //这是一个什么变量局部的
        while (cur != null) {
            cmp = cur.key.compareTo(e);
            if (cmp == 0) throw new RuntimeException("结点已经存在");
            if (cmp > 0) {
                parent = cur;
                cur = cur.left;
            }
            if (cmp < 0) {
                parent = cur;
                cur = cur.right;
            }
        }
        //跳出循环说明在树里面没有找到该节点 所以执行插入的过程
        //但是此时的cur == null 就算是由比较值还是不知道往哪里插入
        //所以需要保存一个父节点
        //但是具体往哪里插入，还是需要看比较值的
        Node node = new Node(e);
        if (cmp > 0) parent.left = node;
        if (cmp < 0) parent.right = node;
    }

    /**
     * 删除操作
     * 一半来说树的特殊情况无非就是 空树 一个结点的树
     * 叶子结点 只有一个孩子 两个孩子都有
     * 1.删除的叶子结点
     * 2.删除的不是叶子结点，但只有一个孩子
     * 3.删除的不是叶子结点，有两个孩子
     * 我们采用替换值删除的操作，选择左子树中最大的一个或者右子树中最小的一个
     *
     * 具体的删除叶子结点的操作
     * 判断key值是否在叶子结点中存在 不存在返回，存在就删除
     * 1.如果要删除的叶子结点是叶子结点
     * parent.left / right = null 置为空就可以删除 但是这的情况是默认有了parent 所以有一个特殊的情况
     * 如果刚好是根的话 就让根为空
     * 2.如果要删除的叶子结点只有左孩子/右孩子 就让孩子继承它的位置
     * 3.如果左右孩子都有，那么就让左孩子里面最大的继承它的地位，值覆盖就可以不用真的删除结点
     * 但是这个要替换的结点可能会有左孩子 ，所以替换完了以后，还需要让它的左孩子顶替它的位置
     */
    public boolean remove(Integer e) {
        if (root == null) return false;
        Node cur = root;
        Node parent = null;
        int cmp = 0;
        while (cur != null) {
            cmp = cur.key.compareTo(e);
            if (cmp == 0){//说明找到了要删除的值
                removeInternal(cur, parent);
                //removeInternal2(cur, parent);
                return true;
            }
            if (cmp > 0) {
                parent = cur;
                cur = cur.left;
            }
            if (cmp < 0) {
                parent = cur;
                cur = cur.right;
            }
        }
        return false;
    }

    /**
     * 采用的是值替换的方式
     * 几个易错点：
     * 1.把双亲结点的zuo/右置为空的时候，是否考虑到有没有双亲结点
     * 2.在待删除到的结点左右孩子都存在的时候
     *  找待删除结点的左子树的最大值（顶替结点）
     *  顶替结点一定在它的父节点的右边吗？这一点忽略了
     *
     *
     * @param cur
     * @param parent
     */
    private void removeInternal(Node cur, Node parent) {

        if(cur.left == null && cur.right == null){
            //说明删除的是叶子结点 然后让它的父节点左/右置为空
            if(cur == root) {//因为当时根节点的时候，parent的值是空，
                root = null; //这一步其实是在将parent为空的情况排除
            }
            //不能借助cmp比较，因为既然已经找到了要删除的值，那么cmp一定是0
            else if(cur == parent.left){ //判断是父亲的左边还是右边
                parent.left = null;
            }else {
                parent.right = null;
            }
        }else if(cur.left == null || cur.right == null){
            //说明只有一个孩子 那么让孩子覆盖cur的位置
            //可是这不一定是cur的正确位置啊-一定是
            if(cur.left != null){
                cur.key = cur.left.key;
                cur.left = null;
            }
            //右孩子是可以直接覆盖的
            if(cur.right != null) {
                cur.key = cur.right.key;
                cur.right = null;
            }
        }else { //说明左右结点都存在
            //那就一路向下搜索到左孩子的最大值
            Node trick = cur.left;
            Node trickParent = cur;
            while (trick.right != null) {
                trickParent = trick;
                trick = trick.right;
            }
            //找到了替代的结点
            cur.key = trick.key;

            //然后对替代的结点做处理
            //如果有孩子
            if (trick.left != null) {
                //可是这个孩子的值可以替换吗
                trick.key = trick.left.key;
                trick.left = null;
            } else { // 说明没有孩子
                //这里出现了问题
                //trick 到底是在trick的左边还是右边
                if (cur == trickParent) {
                    trickParent.left = null;
                } else
                    trickParent.right = null; //那就让父节点置为空
            }
        }
    }

    /**
     * 采用的是更改双亲结点的指向
     * 所以在更改的时候，如果需要删除的结点有孩子，让孩子继承删除结点的位置的时候
     * 需要多加一条判断 待删除的结点是双亲结点的左还是右
     * @param cur
     * @param parent
     */
    private void removeInternal2(Node cur, Node parent) {
        if(cur.left == null && cur.right == null){
            if(cur == root){
                root = null;
            }else if(cur == parent.left){ //判断是父亲的左边还是右边
                parent.left = null;
            }else {
                parent.right = null;
            }
        }else if(cur.left != null && cur.right == null){
            if(cur == root){
                root = cur.left;
            }else if(cur == parent.left){ // 为什么不能直接替换值，然后原来的置为空--不用双亲结点 --全部用左边继承
                parent.left = cur.left;
            }else {
                parent.right = cur.left;
            }
        }else if(cur.left == null && cur.right != null){ // 全部用右边继承
            if(cur == root){
                root = cur.right;
            }else if(cur == parent.left){ //
                parent.left = cur.right;
            }else {
                parent.right = cur.right;
            }
        }else {
            //使用替换法删除 使用cur 左子树的最大结点替换，记作ghost
            Node ghostParent = cur;
            Node  ghost = cur.left;//去寻找左子树的最大值
            while (ghost.right != null){
                ghostParent = ghost;
                ghost = ghost.right;
            }
            //替换
            cur.key = ghost.key;
            //删除ghost结点 其右孩子一定为空 -- 然后分情况讨论 让ghost的左孩子继承它的地位
            //现在只是确定继承他的哪一个地位
            if(cur == ghostParent) {
                ghostParent.left = ghost.left;
            }else{
                ghostParent.right = ghost.left;
            }
        }

    }



}

