package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A BinaryMaxHeap is a max heap backed by an array. It can use natural ordering or a custom comparator.
 *
 * @param <E> the type of elements held in this heap
 * @author Yuli Wang
 * @version 2024/7/18
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    private E[] arr;
    private int size;
    private Comparator<? super E> cmp;

    /**
     * Constructs an empty binary max heap using natural ordering.
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap() {
        this.arr = (E[]) new Object[10];
        this.size = 0;
        this.cmp = null;
    }

    /**
     * Constructs an empty binary max heap using the provided comparator.
     *
     * @param cmp the comparator to order the elements
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this.arr = (E[]) new Object[10];
        this.size = 0;
        this.cmp = cmp;
    }

    /**
     * Constructs a binary max heap from the given list using natural ordering.
     *
     * @param list the list of elements to be turned into a heap
     */
    public BinaryMaxHeap(List<? extends E> list) {
        this(list, null);
    }

    /**
     * Constructs a binary max heap from the given list using the provided comparator.
     *
     * @param list the list of elements to be turned into a heap
     * @param cmp  the comparator to order the elements
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.cmp = cmp;
        this.arr = (E[]) new Comparable[list.size() + 10];
        this.size = list.size();
        for (int i = 0; i < list.size(); i++) {
            arr[i + 1] = list.get(i);
        }
        buildHeap();
    }


    /**
     * Adds the given item to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item - to be added to this priority queue
     */
    @Override
    public void add(E item) {
        if (size == arr.length - 1) {
            resize();
        }
        arr[++size] = item;
        percolateUp(size);
    }

    /**
     * Returns, but does not remove, the highest priority item this priority queue.
     * O(1)
     *
     * @return the highest priority item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        return arr[1];
    }

    /**
     * Returns, but does not remove, the highest priority item this priority queue.
     * O(1)
     *
     * @return the highest priority item
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extract() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        E max = arr[1];
        arr[1] = arr[size--];
        percolateDown(1);
        return max;
    }

    /**
     * Determines the number of items in this priority queue.
     * O(1)
     *
     * @return the number of items in this priority queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines the number of items in this priority queue.
     * O(1)
     *
     * @return the number of items in this priority queue
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empties this priority queue of items.
     * O(1)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        arr = (E[]) new Comparable[10];
        size = 0;
    }

    /**
     * Generates an array of the items in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is needed for grading purposes. The root item
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     *
     * @return array of items in this priority queue
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 1; i <= size; i++) {
            result[i - 1] = arr[i];
        }
        return result;
    }

    /**
     * Builds the heap from the current array.
     * O(N)
     */
    private void buildHeap() {
        for (int i = size / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Percolates the item at index i down the heap to restore heap order.
     * O(log N)
     *
     * @param i the index of the item to percolate down
     */
    private void percolateDown(int i) {
        int childIndex;
        E temp = arr[i];
        while (i * 2 <= size) {
            childIndex = leftChild(i);
            if (childIndex != size && compare(arr[childIndex + 1], arr[childIndex]) > 0) {
                childIndex++;
            }
            if (compare(arr[childIndex], temp) > 0) {
                arr[i] = arr[childIndex];
            } else {
                break;
            }
            i = childIndex;
        }
        arr[i] = temp;
    }

    /**
     * Percolates the item at index i up the heap to restore heap order.
     * O(log N)
     *
     * @param i the index of the item to percolate up
     */
    private void percolateUp(int i) {
        E temp = arr[i];
        while (i > 1 && compare(temp, arr[parent(i)]) > 0) {
            arr[i] = arr[parent(i)];
            i = parent(i);
        }
        arr[i] = temp;
    }

    /**
     * Resizes the backing array to double its current size.
     * O(N)
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newArray = (E[]) new Object[arr.length * 2];
        for (int i = 1; i <= size; i++) {
            newArray[i] = arr[i];
        }
        arr = newArray;
    }

    /**
     * Returns the index of the left child of the item at index i.
     * O(1)
     *
     * @param i the index of the item
     * @return the index of the left child
     */
    private int leftChild(int i) {
        return 2 * i;
    }

    /**
     * Returns the index of the parent of the item at index i.
     * O(1)
     *
     * @param i the index of the item
     * @return the index of the parent
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Compares two items using the comparator if provided, or natural ordering otherwise.
     * O(1)
     *
     * @param left  the first item
     * @param right the second item
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     * equal to, or greater than the second
     */
    private int compare(E left, E right) {
        if (cmp != null) {
            return cmp.compare(left, right);
        } else {
            return ((Comparable<? super E>) left).compareTo(right);
        }
    }


}
