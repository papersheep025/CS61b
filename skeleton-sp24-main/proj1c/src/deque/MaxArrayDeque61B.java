package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> maxArrayComparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        maxArrayComparator = c;
    }

    // Find the max item in array.
    public T max() {
        if (isEmpty()) return null;

        T maxResult = get(0);
        for (T i : this) {
            int cmp = maxArrayComparator.compare(i, maxResult);
            if (cmp > 0) {
                maxResult = i;
            }
        }
        return maxResult;
    }

    // Find the max item using comparator.
    public T max(Comparator<T> c) {
        if (isEmpty()) return null;

        T maxResult = get(0);
        for (T i : this) {
            int cmp = c.compare(i, maxResult);
            if (cmp > 0) {
                maxResult = i;
            }
        }
        return maxResult;
    }
}
