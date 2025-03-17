package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements List interface with a singly-linked list.
 *
 * @param <E> the type of elements in SinglyLinkedList
 * @author Erin Parker,Yuli Wang
 * @version June 20, 2024
 */
public class SinglyLinkedList<E> implements List<E> {

    /**
     * This private class states the contents of nodes and the relationships between nodes.
     *
     * @param <N> the type of element in a node
     */
    private class Node<N> {
        public N value;
        public Node<N> next;

        public Node(N value, Node<N> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private int count;

    public SinglyLinkedList() {
        head = new Node<E>(null, null);
        count = 0;
    }

    /**
     * Inserts an element at the beginning of the list.O(1) for a singly-linked list.
     *
     * @param element - the element to be add
     */
    @Override
    public void insertFirst(E element) {
        head.next = new Node<E>(element, head.next);
        count++;
    }

    /**
     * This method returns a node previous of this node.
     *
     * @param index - the index of previous node
     * @return the index of previous node
     */
    private Node<E> getPreviousNode(int index) {
        // Creates a temporary node to store the current node
        Node<E> temp = head;

        for (int i = -1; i < index - 1; i++)
            temp = temp.next;

        return temp;
    }

    /**
     * This method used to insert a new element.
     *
     * @param element      - the element to add
     * @param previousNode - the previous node
     */
    private void insert(E element, Node<E> previousNode) {
        previousNode.next = new Node<E>(element, previousNode.next);
        count++;
    }

    /**
     * Inserts an element at a specific position in the list.O(N) for a singly-linked list.
     *
     * @param index   - the specified position
     * @param element - the element to be added
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
     *                                   index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > count)
            throw new IndexOutOfBoundsException();
        // If it is the first index using insertFirst method
        if (index == 0)
            insertFirst(element);
        else
            insert(element, getPreviousNode(index));
    }

    /**
     * Gets the first element in the list. O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (count == 0)
            throw new NoSuchElementException();
        return head.next.value;
    }

    /**
     * Gets the element at a specific position in the list. O(N) for a singly-linked
     * list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
     *                                   index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        // Creates a temporary Node to store the current Node
        Node<E> temp = head.next;

        for (int i = 0; i < index; i++)
            temp = temp.next;

        return temp.value;
    }

    /**
     * Deletes and returns the first element. O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (count == 0)
            throw new NoSuchElementException();

        //Creates a temporary Node to store and return the element
        E temp = head.next.value;

        head.next = head.next.next;

        count--;

        return temp;
    }

    /**
     * Deletes and returns the element at a specific position. O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
     *                                   index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();

        //Creates a temporary Node to store and return the element
        Node<E> previousNode = getPreviousNode(index);
        E temp = previousNode.next.value;

        getPreviousNode(index).next = getPreviousNode(index).next.next;

        count--;

        return temp;
    }

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to be searched
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        for (int i = 0; i < count; i++)
            if (get(i).equals(element))
                return i;

        return -1;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Removes all elements from this list. O(1) for a singly-linked list.
     */
    @Override
    public void clear() {
        head.next = null;
        count = 0;
    }

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element). O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        // Creates an array to store elements
        Object[] array = new Object[count];

        // Traverse the list
        for (int i = 0; i < count; i++)
            array[i] = get(i);

        return array;
    }

    /**
     * @return an iterator over the elements in this list in proper sequence
     * (from first element to last element)
     */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> previousNode;
        private Node<E> currentNode;
        private Node<E> nextNode;
        private boolean isRemove;

        /**
         * This is known as the 'default constructor'.
         */
        public SinglyLinkedListIterator() {
            this.currentNode = head;
            this.nextNode = head.next;

            this.isRemove = false;
        }

        /**
         * Determines the next element exists
         *
         * @return true or false determine the element exists
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Go for next element, and returns the current element
         *
         * @return the current element
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            isRemove = true;

            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = nextNode.next;

            return currentNode.value;
        }

        /**
         * Removes the current element.
         */
        public void remove() {
            if (!isRemove || count < 1)
                throw new IllegalStateException();
            count--;

            previousNode.next = nextNode;
            currentNode = nextNode;
            if (nextNode != null)
                nextNode = nextNode.next;

            isRemove = false;
        }
    }
}