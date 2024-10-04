import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class SelfTest {

    @Test
    public void testIsEmpty() {
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        assertThat(lld2.size()).isEqualTo(0);
    }

    @Test
    public void testSize() {
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addLast(0);
        lld2.addLast(1);
        lld2.addFirst(-1);
        lld2.addLast(2);
        lld2.addFirst(-2);
        assertThat(lld2.size()).isEqualTo(5);
    }

    @Test
    public void removeFirst() {
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.removeFirst();
        assertThat(lld2.toList()).containsExactly("middle", "back");
    }

    @Test
    public void removeLast() {
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.removeLast();
        assertThat(lld2.toList()).containsExactly("front", "middle");
    }

    @Test
    public void getIterate() {
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addLast(0);
        lld2.addLast(1);
        lld2.addFirst(-1);
        lld2.addLast(2);
        lld2.addFirst(-2);
        assertThat(lld2.get(4)).isEqualTo(2);
        assertThat(lld2.get(6)).isEqualTo(null);
    }

    @Test
    public void getRecursive() {
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addLast(0);
        lld2.addLast(1);
        lld2.addFirst(-1);
        lld2.addLast(2);
        lld2.addFirst(-2);
        assertThat(lld2.get(4)).isEqualTo(2);
        assertThat(lld2.get(6)).isEqualTo(null);
    }
}
