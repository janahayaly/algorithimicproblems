import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A {@link java.util.Deque} implemented by a resizing array.
 *
 * @param <E> the type of the elements in the deque
 */
public class ResizingDequeImpl<E> implements ResizingDeque<E> {

    private E[] backend;

    private int headIdx;

    private int tailIdx;

    private int numElts;

    ResizingDequeImpl() {
        this.backend = (E[]) new Object[2];
        this.headIdx = 0;
        this.tailIdx = 0;
        this.numElts = 0;
    }

    /**
     * Returns the size of this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return numElts;
    }

    /**
     * @return The underlying array of the deque implementation directly.
     */
    @Override
    public E[] getArray() {
        return backend;
    }

    private E[] returnNonNull() {
        E[] nonNullElts = (E[]) new Object[countElts()];
        int idx = 0;
        if (headIdx <= tailIdx) {
            for (int i = headIdx; i <= tailIdx; i++) {
                nonNullElts[idx] = backend[i];
                idx++;
            }
        } else {
            for (int i = headIdx; i < backend.length; i++) {
                nonNullElts[idx] = backend[i];
                idx++;
            }
            for (int i = 0; i <= tailIdx; i++) {
                nonNullElts[idx] = backend[i];
                idx++;
            }
        }

        return nonNullElts;
    }

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     */
    @Override
    public void addFirst(E e) {
        if (numElts == 0) {
            backend[0] = e;
            this.headIdx = 0;
            this.tailIdx = 0;
        } else if (numElts + 1 > backend.length) {
            E[] result = (E[]) new Object[backend.length * 2];
            E[] nonNull = returnNonNull();
            for (int i = 0; i < nonNull.length; i++) {
                result[i] = nonNull[i];
            }
            result[result.length - 1] = e;
            this.headIdx = result.length - 1;
            this.tailIdx = numElts - 1;
            this.backend = result;
        } else if (headIdx == 0) {
            backend[backend.length - 1] = e;
            this.headIdx = backend.length - 1;
        } else {
            backend[headIdx - 1] = e;
            this.headIdx--;
        }
        this.numElts++;
    }

    /**
     * Inserts the specified element at the end of this deque.
     *
     * @param e the element to add
     */
    @Override
    public void addLast(E e) {
        if (numElts == 0) {
            backend[0] = e;
            this.headIdx = 0;
            this.tailIdx = 0;
        } else if (countElts() + 1 > backend.length) {
            E[] result = (E[]) new Object[backend.length * 2];
            E[] nonNull = returnNonNull();
            for (int i = 0; i < nonNull.length; i++) {
                result[i] = nonNull[i];
            }
            result[numElts] = e;
            this.tailIdx = numElts;
            this.headIdx = 0;
            this.backend = result;
        } else if (tailIdx == backend.length - 1) {
            backend[0] = e;
            this.tailIdx = 0;
        } else {
            backend[tailIdx + 1] = e;
            this.tailIdx++;
        }
        this.numElts++;
    }

    private int countElts() {
        int elts = 0;
        if (headIdx <= tailIdx) {
            for (int i = headIdx; i <= tailIdx; i++) {
                elts++;
            }
        } else {
            for (int i = headIdx; i < backend.length; i++) {
                elts++;
            }
            for (int i = 0; i <= tailIdx; i++) {
                elts++;
            }
        }
        return elts;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves and removes the first element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        E firstElt = backend[headIdx];
        backend[headIdx] = null;
        this.numElts--;
        if (numElts < (backend.length / 4)) {
            if (backend.length / 2 >= 2) {
                E[] result = (E[]) new Object[backend.length / 2];
                E[] nonNull = returnNonNull();
                for (int i = 0; i < nonNull.length; i++) {
                    result[i] = nonNull[i];
                }
                this.backend = result;
                this.headIdx = 0;
                this.tailIdx = numElts - 1;
            }
        } else if (headIdx + 1 == backend.length) {
            headIdx = 0;
        } else {
            if (backend[headIdx + 1] != null) {
                headIdx = headIdx + 1;
            }
        }
        if (isEmpty()) {
            headIdx = 0;
            tailIdx = 0;
        }
        return firstElt;
    }

    /**
     * Retrieves and removes the last element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        E lastElt = backend[tailIdx];
        backend[tailIdx] = null;
        numElts--;
        if (numElts < (backend.length / 4)) {
            if (backend.length / 2 >= 2) {
                E[] result = (E[]) new Object[backend.length / 2];
                E[] nonNull = returnNonNull();
                for (int i = 0; i < nonNull.length; i++) {
                    result[i] = nonNull[i];
                }
                this.backend = result;
                this.headIdx = 0;
                this.tailIdx = numElts - 1;
            }
        } else if (tailIdx - 1 < 0) {
            tailIdx = backend.length - 1;
        } else {
            if (backend[tailIdx - 1] != null) {
                tailIdx = tailIdx - 1;
            }
        }
        if (isEmpty()) {
            headIdx = 0;
            tailIdx = 0;
        }
        return lastElt;
    }

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return backend[this.headIdx];
    }

    /**
     * Retrieves, but does not remove, the last element of this deque
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    @Override
    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return backend[this.tailIdx];
    }

    /**
     * Returns an iterator over the elements in this deque, ordered from
     * first to last.
     *
     * @return an iterator over the elements in this deque
     */
    @Override
    public Iterator<E> iterator() {
        ArrayList<E> copy = new ArrayList<>();
        for (int i = 0; i < returnNonNull().length; i++) {
            copy.add(returnNonNull()[i]);
        }
        return copy.iterator();
    }

    int getTailIdx() {
        return this.tailIdx;
    }

    int getHeadIdx() {
        return this.headIdx;
    }


}
