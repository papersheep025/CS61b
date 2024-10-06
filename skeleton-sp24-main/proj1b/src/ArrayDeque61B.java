import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private int size;
    private int maxSize;
    private int nextFirst, nextLast;

    public ArrayDeque61B() {
        maxSize = 8; // Initial max size to 8.
        array = (T[]) new Object[maxSize];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void addFirst(T x) {
        if (size == maxSize) resizeUp();

        array[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, maxSize);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == maxSize) resizeUp();

        array[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, maxSize);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        int index = Math.floorMod(nextFirst + 1, maxSize);
        for (int i = 0; i < size; i++) {
            returnList.add(array[index]);
            index = Math.floorMod(index + 1, maxSize);
        }
        return returnList;
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
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return array[Math.floorMod(nextFirst + 1 + index, maxSize)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;

        while (size <= maxSize * 0.25) {
            resizeDown();
        }
        T first = get(0);
        nextFirst = Math.floorMod(nextFirst + 1, maxSize);
        size--;
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;

        while (size <= maxSize * 0.25) {
            resizeDown();
        }
        T last = get(size - 1);
        nextLast = Math.floorMod(nextLast - 1, maxSize);
        size--;
        return last;
    }

    private void resizeUp() {
        maxSize *= 2; // Double the size.
        T[] newArray = (T[]) new Object[maxSize];

        // Copy the elements to new array.
        int index = Math.floorMod(nextFirst + 1, size);
        for (int i = 0; i < size; i++) {
            newArray[i] = array[index];
            index = Math.floorMod(index + 1, size);
        }
        nextFirst = Math.floorMod(-1, maxSize);
        nextLast = Math.floorMod(size, maxSize);
        array = newArray;
    }

    private void resizeDown() {
        maxSize /= 2; // Halve the size.
        T[] newArray = (T[]) new Object[maxSize];

        int index = Math.floorMod(nextFirst + 1, size);
        for (int i = 0; i < size; i++) {
            newArray[i] = array[index];
            index = Math.floorMod(index + 1, size);
        }
        nextFirst = Math.floorMod(-1, maxSize);
        nextLast = Math.floorMod(size, maxSize);
        array = newArray;
    }
}


