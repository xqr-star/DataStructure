package binary_search_tree;

public class removeTest {
    private static void case1(){
        BST tree = new BST();
        tree.insert(3);

        //返回false 树不变化
        System.out.println(tree.remove(5));
    }
    private static void case2(){
        BST tree = new BST();
        tree.insert(3);

        //返回true 树空
        System.out.println(tree.remove(3));
    }
    //删除叶子结点的
    private static void case3(){
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(6);


        //返回true 树没有3
        System.out.println(tree.remove(3));
    }


    private static void case6(){
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(6);
        tree.insert(2);


        //返回true 树没有3
        System.out.println(tree.remove(3));
    }
    private static void case7(){
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(8);


        //返回true 树没有3
        System.out.println(tree.remove(7));
    }

    /**
     * 替换的测试
     * 3
     *
     */
    private static void case11(){
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(6);
        tree.insert(2);
        tree.insert(4);


        //返回true 树没有3
        System.out.println(tree.remove(3));
    }
    /**
     *
     */
    private static void case12(){
        BST tree = new BST();
        tree.insert(7);
        tree.insert(8);
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);


        //返回true 树没有3
        System.out.println(tree.remove(5));
    }

    /**
     * 删除根
     */
    private static void case13(){
        BST tree = new BST();
        tree.insert(7);
        tree.insert(8);
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);


        //返回true 树没有3
        System.out.println(tree.remove(7));
    }

    /**
     * 空树删除
     */
    private static void case14(){
        BST tree = new BST();
        System.out.println(tree.remove(3));
    }


    public static void main(String[] args) {
//        case1();
//        case2();
//         case3();
//        case6();
//        case7();
//        case11();
 //       case12();
 //      case13();
       case14();
    }
}
