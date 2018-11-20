package com.mycollection.tree;

/**
 * @Description:
 * @Time: 2018-08-15 22:04
 * @Version: 1.0
 */
public class BsTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private BSTNode<T> root;

    public class BSTNode<T extends Comparable<T>> {
        T key;
        BSTNode<T> left;
        BSTNode<T> right;
        BSTNode<T> parent;

        public BSTNode(T key, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public BSTNode() {
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public BSTNode<T> getLeft() {
            return left;
        }

        public void setLeft(BSTNode<T> left) {
            this.left = left;
        }

        public BSTNode<T> getRight() {
            return right;
        }

        public void setRight(BSTNode<T> right) {
            this.right = right;
        }

        public BSTNode<T> getParent() {
            return parent;
        }

        public void setParent(BSTNode<T> parent) {
            this.parent = parent;
        }
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }
}
