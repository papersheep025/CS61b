public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left, RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        if (node == null || node.left == null) return node;

        // Rotate right.
        RBTreeNode<T> newRoot = node.left; // node is b and new root is a(node.left).
        node.left = newRoot.right; // b's left is new root's right.
        newRoot.right = node; // new root's right is b.

        boolean temp = newRoot.isBlack;
        newRoot.isBlack = node.isBlack;
        node.isBlack = temp;

        return newRoot;
    }
//    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
//        // a is left child of b -> b is right child of a
//        RBTreeNode<T> newRoot = node.left; // new root -> a
//        RBTreeNode<T> preRoot = newRoot.right; // New root's right is the previous root.
//        newRoot.right = node; // new root's right is b now.
//        node.left = preRoot; // Maintain the structure of the tree.
//
//        flipColors(newRoot); // Flip the color of the new root.
//
//        return newRoot;
//    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    // TODO: Fix rotateLeft
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        if (node == null || node.right == null) return node;

        RBTreeNode<T> newRoot = node.right; // node is a and new root is b(a's right)
        node.right = newRoot.left; // a's right is new root's left.
        newRoot.left = node; // new root's left is a

        boolean temp = newRoot.isBlack;
        newRoot.isBlack = node.isBlack;
        node.isBlack = temp;

        return newRoot;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // Insert (return) new red leaf node.
        if (node == null) return new RBTreeNode<>(false, item);

        // Handle normal binary search tree insertion.
        if (item.compareTo(node.item) > 0) { // If the item is greater, recurse to right.
            node.right = insert(node.right, item);
        } else if (item.compareTo(node.item) < 0) { // If the item is smaller, recurse to left.
            node.left = insert(node.left, item);
        }

        // Rotate left operation
        // If left child is black and right child is red, rotate left.
        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        // If both node and its right is red, should also rotate left.
        if (isRed(node) && isRed(node.right)) node = rotateLeft(node);

        // Rotate right operation
        // If left child and its child are both red, rotate right.
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);

        // Color flip
        // If both left and right child are red, flip the color.
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

}
