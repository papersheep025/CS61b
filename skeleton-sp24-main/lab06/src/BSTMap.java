import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class BSTNode{
        public K key;
        public V value;
        public BSTNode left = null;
        public BSTNode right = null;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) { // If there is no root, set a root namely n.
            BSTNode n = new BSTNode(key, value);
            root = n;
            size ++;
        } else { // If there is a root, insert a node.
            putNode(root, key, value);
            size ++;
        }
    }

    private BSTNode putNode(BSTNode node, K key, V value) {
        if (node == null) return new BSTNode(key, value);

        if (key.compareTo(node.key) > 0) { // If the key of inserted node is greater than the current key, recurse to the right subtree.
            node.right = putNode(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) { // Recurse to the left subtree.
            node.left = putNode(node.left, key, value);
        } else if (key.compareTo(node.key) == 0) { // If equal, update the value.
            node.value = value;
            size --;
        }
        return node;
    }

    @Override
    public V get(K key) {
        return getNode(root, key);
    }

    /** Get a specific key. */
    private V getNode(BSTNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) { // If the key matches.
            return node.value; // Return the value of the key.
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return isContain(root, key);
    }

    /** Check whether a specific key exists. */
    private boolean isContain(BSTNode node, K key) {
        if (node == null) return false;

        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) > 0) {
            return isContain(node.right, key);
        }else {
            return isContain(node.left, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns a hashset which contains all the keys. */
    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();

        for (K key : this) {
            result.add(key);
        }
        return result;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) return null;

        V result = get(key);
        root = removeNode(root, key);
        size --;
        return result;
    }

    private BSTNode removeNode(BSTNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) {
            if (node.right == null) { // If the right child is null, return the left child.
                return node.left;
            } else if (node.left == null) { // If the left child is null, return the right child.
                return node.right;
            }
            // If both right and left child exist, find the biggest node in left child and replace the current node.
            BSTNode r = findMaxInLeft(node);
            node.key = r.key;
            node.value = r.value;
            // Then remove the biggest node in left child recursively.
            node.left = removeNode(node.left,r.key);
        }
        return node;
    }

    private BSTNode findMaxInLeft(BSTNode node){
        BSTNode m = node.left;
        // Return the most right one.
        while(m.right != null) {
            m = m.right;
        }
        return m;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        public Stack<BSTNode> BSTStack = new Stack<>();

        // Initialize.
        public BSTMapIterator(){
            Push_Left(root); // Push the most left node onto the stack.
        }

        // Method to push all left children of the given node onto the stack.
        public void Push_Left(BSTNode node){
            while (node != null){
                BSTStack.push(node); // Using stack method.
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !BSTStack.empty();
        }

        @Override
        public K next() {
            BSTNode node = BSTStack.pop();
            Push_Left(node.right);
            return node.key;
        }
    }
}

