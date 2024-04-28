import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class ResizingDequeImplTest {

    @Test
    public void testAddFirstAndPollFirst() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addFirst(2);
        assertEquals(2, deque.size());
        assertEquals(2, (int) deque.pollFirst());
        assertEquals(1, deque.size());
        assertEquals(1, (int) deque.pollFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLastAndPollLast() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(2, (int) deque.pollLast());
        assertEquals(1, (int) deque.pollLast());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLastToFirstSlot() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.pollFirst();
        deque.addLast(5);
        Object[] exp = new Object[]{5, 2, 3, 4};
        Object[] act = deque.getArray();

        assertArrayEquals(exp, deque.getArray());
        assertEquals(exp[0], act[0]);
        assertEquals(0, deque.getTailIdx());
        assertEquals(4, deque.size());
    }

    @Test
    public void testPeekFirstAndPeekLast() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(1, (int) deque.peekFirst());
        assertEquals(2, (int) deque.peekLast());
        assertEquals(1, (int) deque.peekFirst());
        assertEquals(2, deque.size());
    }

    @Test
    public void testPeekFirstNull() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(null);
        assertNull(deque.peekFirst());
        assertEquals(4, deque.size());
    }

    @Test
    public void testPeekLastNull() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(null);
        assertNull(deque.peekLast());
        assertEquals(4, deque.size());
    }

    @Test
    public void testPollFirstAndLastNull() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(null);
        deque.addFirst(null);
        assertNull(deque.pollLast());
        assertNull(deque.pollFirst());
        assertEquals(3, deque.size());
    }

    @Test
    public void testAddFirstAndLastNull() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(null);
        deque.addFirst(null);
        assertEquals(5, deque.size());
    }

    @Test
    public void testAddHeadInMid() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.pollFirst();
        deque.pollFirst();
        deque.addFirst(5);
        Object[] arr = deque.getArray();
        assertEquals(5, (int) deque.peekFirst());
        assertEquals(5, arr[1]);
        assertEquals(1, deque.getHeadIdx());
        assertEquals(3, deque.size());
    }

    @Test
    public void testAddAndPollMix() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(0, deque.getHeadIdx());
        assertEquals(1, deque.getTailIdx());

        assertEquals(1, (int) deque.pollFirst());
        assertEquals(2, (int) deque.pollLast());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddAndPollMixAgain() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(2);
        deque.addFirst(1);
        assertEquals(2, (int) deque.pollLast());
        assertEquals(1, (int) deque.pollLast());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddFirstResize() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        Object[] exp = {1, 2, null, 3};

        assertEquals(3, (int) deque.peekFirst());
        assertEquals(2, (int) deque.peekLast());
        assertArrayEquals(exp, deque.getArray());
        assertEquals(3, deque.size());
    }

    @Test
    public void testPollFirstNoResize() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        assertEquals(3, (int) deque.pollFirst());
        assertEquals(1, (int) deque.pollFirst());
        assertEquals(2, (int) deque.pollFirst());

        Object[] exp = {null, null, 4, null};
        assertArrayEquals(exp, deque.getArray());
        assertEquals(1, deque.size());
    }

    @Test
    public void testPollFirstResize() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        assertEquals(3, (int) deque.pollFirst());
        assertEquals(1, (int) deque.pollFirst());
        assertEquals(2, (int) deque.pollFirst());
        assertEquals(4, (int) deque.pollFirst());

        Object[] exp = {null, null};
        assertArrayEquals(exp, deque.getArray());
        assertEquals(0, deque.size());
    }

    @Test
    public void testPollLastNoResize() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        assertEquals(4, (int) deque.pollLast());
        assertEquals(2, (int) deque.pollLast());
        assertEquals(1, (int) deque.pollLast());

        Object[] exp = {null, null, null, 3};
        assertArrayEquals(exp, deque.getArray());
        assertEquals(1, deque.size());
    }

    @Test
    public void testPollLastResize() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);

        assertEquals(5, (int) deque.pollLast());
        assertEquals(4, (int) deque.pollLast());
        assertEquals(2, (int) deque.pollLast());
        assertEquals(1, (int) deque.pollLast());

        Object[] exp = {3, null, null, null};
        assertArrayEquals(exp, deque.getArray());
        assertEquals(1, deque.size());
    }


    @Test (expected = NoSuchElementException.class)
    public void testEmptyDequePollFirst() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        assertEquals(0, deque.size());
        deque.pollFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyDequePollLast() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        assertEquals(0, deque.size());
        deque.pollLast();
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyDequePeekFirst() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        assertEquals(0, deque.size());
        deque.peekFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyDequePeekLast() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        assertEquals(0, deque.size());
        deque.peekLast();
    }

    @Test
    public void testIterator() {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }
}
