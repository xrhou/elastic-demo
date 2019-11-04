package com.hxr.collectiondeeping;

/**
 * 简单实现的红黑树算法代码
 *
 * @author houxiurong
 * @date 2019-11-03
 */
public class RedBlackTreeDemo {

    private static int RED = 0;
    private static int BLACK = 1;

    private static Node root = null;


    class Node {
        private int data;
        int color = RED;//默认全部为红色

        Node left;

        Node right;

        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * root节点一定不为空最开始默认就是有数据的
     *
     * @param root root
     * @param data 要插入的值
     */
    public void insertData(Node root, int data) {
        if (root.data <= data) {//表示插入到树的右边
            if (root.right == null) {
                root.right = new Node(data);
            } else {
                insertData(root.right, data);//递归调用
            }
        } else {//表示要插入到树的左边
            if (root.left == null) {
                root.left = new Node(data);
            } else {
                insertData(root.left, data);
            }
        }
    }

    /**
     * 左旋
     *
     * @param node 节点
     */
    public void leftRotate(Node node) {
        if (node.parent == null) {//此处是根节点
            Node E = root;
            Node S = E.right;

            //1.移动S的左子树
            E.right = S.left;
            S.left.parent = E;

            //2.修改E的指针
            E.parent = S;

            //3.修改S的指针
            S.parent = null;
        } else {
            //如果有父亲节点需要操作三个节点
            if (node == node.parent.left) {
                node.parent.left = node.right;//将S节点浮上来变为新的左子树
            } else {
                node.parent.right = node.right;
            }

            node.right = node.right.left;//S点子树指针挂到E节点上面
            //S点原来的左子树会挂到E上面
            node.right.left.parent = node;

            //S上浮变成E的父节点
            node.right.parent = node.parent;
            node.parent = node.right;//E点的parent会变成原来的S点

            //修正S节点左子树指针
            node.parent.left = node;
        }

    }

    public static void main(String[] args) {
        RedBlackTreeDemo redBlackTreeDemo = new RedBlackTreeDemo();
        Node root = redBlackTreeDemo.initData(12);
        redBlackTreeDemo.insertData(root, 10);
    }

    /**
     * 初始化数据
     *
     * @param data 默认值
     * @return
     */
    private Node initData(int data) {
        return null;
    }

}
