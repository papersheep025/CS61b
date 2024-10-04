import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    // Constructor
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        // Link sentinel node to itself.
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode; // Let the first node's pointer point to newNode.
        sentinel.next = newNode; // Point sentinel's next to the newNode, so that newNode becomes the first node
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newNode; // Update the current last node's next pointer
        sentinel.prev = newNode; // Point sentinel's previous to the new node
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node current = sentinel.next; // Start from the first node.
        while (current != sentinel) { // If current = sentinel, it has iterated all items.
            list.add(current.item);
            current = current.next; // Move to the next node.
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null; // List is empty, nothing to remove.
        T item = sentinel.next.item; // Store the removed item.
        sentinel.next = sentinel.next.next; // Delete the node.
        size--;
        return item; // Return the removed item.
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null; // List is empty, nothing to remove.
        T item = sentinel.prev.item; // Store the removed item.
        sentinel.prev = sentinel.prev.prev; // Delete the node.
        sentinel.prev.next = sentinel; // Update the new last node's next pointer.
        size--;
        return item; // Return the removed item.
    }

    @Override
    public T get(int index) { // Get the item iteratively.
        if (size < index) return null;
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item; // When index is 0, return the item.
    }

    @Override
    public T getRecursive(int index) {
        if (size < index) return null; // Same as get().
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node node, int i) {
        if (i == 0) return node.item;
        return getRecursive(node.next, i - 1); // If greater than 0, move to the next node.
    }
}
