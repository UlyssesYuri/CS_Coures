package assign08;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * BinarySearchTree class that implements the SortedSet interface.
 * Represents a binary search tree of generically-typed items.
 *
 * @param <Type> the type of elements maintained by this set
 * @author Prof. Parker and Yuli wang
 * @version July 5, 2024
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private class Node {
        Type data;
        Node leftChild;
        Node rightChild;
        private static int dotLabelCount = 0;
        private int dotLabel;

        Node(Type data) {
            this.data = data;
            leftChild = null;
            rightChild = null;


        }

        /**
         * Degenerate
         *
         * @return ret
         */
        String generateDot() {
            String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + data + "|<f2> \"]\n";
            if (leftChild != null) {
                ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
            }
            if (rightChild != null) {
                ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
            }
            return ret;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call ; otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if (root == null) {
            root = new Node(item);
            size++;
            return true;
        }
        return addHelper(item, root);
    }

    /**
     * helper method for add,Realization of the add function
     *
     * @param item
     * @param node
     * @return
     */
    private boolean addHelper(Type item, Node node) {
        if (item.compareTo(node.data) < 0) {
            if (node.leftChild == null) {
                node.leftChild = new Node(item);
                size++;
                return true;
            }
            return addHelper(item, node.leftChild);
        } else if (item.compareTo(node.data) > 0) {
            if (node.rightChild == null) {
                node.rightChild = new Node(item);
                size++;
                return true;
            }
            return addHelper(item, node.rightChild);
        }
        return false; // item is already in the tree
    }

    /**
     * Removes all items from this set. The set will be empty after this method call.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item the item sought in this set
     * @return true if there is an item in this set that is equal to the given item; otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {

        Node current = root;
        while (current != null) {
            int cmp = item.compareTo(current.data);
            if (cmp < 0) {
                current = current.leftChild;
            } else if (cmp > 0) {
                current = current.rightChild;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines the first (smallest) item in this set.
     *
     * @return the first (smallest) item in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty.");
        }
        Node current = root;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current.data;
    }

    /**
     * Determines if this set contains any items.
     *
     * @return true if this set contains no items; otherwise returns false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Determines the last (largest) item in this set.
     *
     * @return the last (largest) item in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty.");
        }
        Node current = root;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current.data;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        if (isEmpty()) {
            return false;
        }
        if (!contains(item)) {
            return false;


        }
        root = removeHelper(item, root);
        size--;
        return true;
    }

    /**
     * helper for remove
     *
     * @param item
     * @param node
     * @return
     */
    private Node removeHelper(Type item, Node node) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.data);
        if (cmp < 0) {
            node.leftChild = removeHelper(item, node.leftChild);
        } else if (cmp > 0) {
            node.rightChild = removeHelper(item, node.rightChild);
        } else {
            // Node to be deleted found
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            } else {
                // Node with two children get the inorder successor
                Node minRight = findMin(node.rightChild);
                node.data = minRight.data;
                node.rightChild = removeHelper(minRight.data, node.rightChild);
            }
        }
        return node;
    }

    /**
     * helper for remove
     *
     * @param node
     * @return
     */
    private Node findMin(Node node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    /**
     * Determines the number of items in this set.
     *
     * @return the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Generates a basic array containing all of the items in this set, in sorted order.
     *
     * @return a basic array containing all of the items in this set
     */
    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        ArrayList<Type> resultList = new ArrayList<>();
        toArrayHelper(root, resultList);
        insertionSort(resultList);
        return resultList.toArray();
    }

    /**
     * helper method for toArray
     *
     * @param node
     * @param resultList
     */
    private void toArrayHelper(Node node, ArrayList<Type> resultList) {
        if (node != null) {
            toArrayHelper(node.leftChild, resultList);
            resultList.add(node.data);
            toArrayHelper(node.rightChild, resultList);
        }
    }

    /**
     * sorter for toArray method
     *
     * @param list
     */
    private void insertionSort(ArrayList<Type> list) {
        for (int i = 1; i < list.size(); i++) {
            Type key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    /**
     * Generates a basic array containing all of the items in this set that are in the range begin to end (inclusive), in sorted order.
     *
     * @param begin the string at the beginning of the range
     * @param end   the string at the end of the range
     * @return a basic array containing all of the items in this set that are in the range begin to end (inclusive)
     */
    @Override
    public Object[] toArrayRange(Type begin, Type end) {
        ArrayList<Type> resultList = new ArrayList<>();
        toArrayRangeHelper(root, begin, end, resultList);
        insertionSort(resultList);
        return resultList.toArray();
    }

    /**
     * helper for toArrayRange
     *
     * @param node
     * @param begin
     * @param end
     * @param resultList
     */
    private void toArrayRangeHelper(Node node, Type begin, Type end, ArrayList<Type> resultList) {
        if (node != null) {
            if (begin.compareTo(node.data) < 0) {
                toArrayRangeHelper(node.leftChild, begin, end, resultList);
            }
            if (begin.compareTo(node.data) <= 0 && end.compareTo(node.data) >= 0) {
                resultList.add(node.data);
            }
            if (end.compareTo(node.data) > 0) {
                toArrayRangeHelper(node.rightChild, begin, end, resultList);
            }
        }
    }

    /**
     * Generates the dot representation of the tree.
     *
     * @param treeName name of the tree to be used in the dot representation
     * @return the dot representation of the tree as a string
     */
    public String generateDot(String treeName) {
        String output = "digraph " + treeName + " {\n\tnode [shape=record]\n";
        if (root != null)
            output += root.generateDot();
        return output + "}";
    }
}
