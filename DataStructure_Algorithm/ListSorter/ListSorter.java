package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class helps us determine which algorithm performs best for any type of list.
 * We have narrowed our choices to merge sort and quick sort.
 * We will conduct experiments to see which of these two sorting algorithms has the fastest
 * running time for Java lists of various sizes in the following three categories ascending list, randomized list, descending list
 *
 * @author  Ulysses Wang
 * @version 2024-6-13
 */
public class ListSorter {
    private static final int SORT_THRESHOLD = 16;
    private static final Random random = new Random();

    /**
     * Sorts the given List using merge sort.
     *
     * @param arr The given List to be sorted.
     * @param <T> The type of elements in the List.
     */
    public static <T extends Comparable<? super T>> void mergesort(List<T> arr) {
        if (arr.size() < SORT_THRESHOLD) {
            insertsort(arr, 0, arr.size() - 1);
            return;
        }
        ArrayList<T> temporaryArray = new ArrayList<>(Collections.nCopies(arr.size(), null));
        merge(arr, temporaryArray, 0, arr.size() - 1);
    }

    /**
     * @param arr            The given List to be sorted.
     * @param temporaryArray A temporary List for store.
     * @param start          The minimum index in the sort.
     * @param end            The maximum index in the sort.
     * @param <T>            The type of elements in the List.
     */
    private static <T extends Comparable<? super T>> void merge(List<T> arr, ArrayList<T> temporaryArray, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            merge(arr, temporaryArray, start, middle);
            merge(arr, temporaryArray, middle + 1, end);
            for (int i = start; i <= end; i++) {
                temporaryArray.set(i, arr.get(i));
            }
            int partOneIndex = start;
            int partTwoIndex = middle + 1;
            int mergedIndex = start;

            while (partOneIndex <= middle && partTwoIndex <= end) {
                if (temporaryArray.get(partOneIndex).compareTo(temporaryArray.get(partTwoIndex)) <= 0) {
                    arr.set(mergedIndex++, temporaryArray.get(partOneIndex++));
                } else {
                    arr.set(mergedIndex++, temporaryArray.get(partTwoIndex++));
                }
            }
            while (partOneIndex <= middle) {
                arr.set(mergedIndex++, temporaryArray.get(partOneIndex++));
            }
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(List<T> arr) {
        quicksort(arr, 0, arr.size() - 1, "medianOfThree");
    }

    /**
     * Sorts the given sublist using the quick sort algorithm with the specified pivot strategy.
     *
     * @param <T>           The type of elements in the list, must extend Comparable
     * @param arr           The list to be sorted
     * @param low           The starting index of the sublist
     * @param high          The ending index of the sublist
     * @param pivotStrategy The strategy for choosing the pivot
     */
    public static <T extends Comparable<? super T>> void quicksort(List<T> arr, int low, int high, String pivotStrategy) {
        if (low < high) {
            if (high - low < SORT_THRESHOLD) {
                insertsort(arr, low, high);
            } else {
                int pivotIndex = partition(arr, low, high, pivotStrategy);
                quicksort(arr, low, pivotIndex - 1, pivotStrategy);
                quicksort(arr, pivotIndex + 1, high, pivotStrategy);
            }
        }
    }

    /**
     * Sorts the given sublist using insertion sort.
     *
     * @param <T>   The type of elements in the list, must extend Comparable
     * @param arr   The list to be sorted
     * @param start The starting index of the sublist
     * @param end   The ending index of the sublist
     */
    private static <T extends Comparable<? super T>> void insertsort(List<T> arr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T current = arr.get(i);
            int j = i - 1;
            while (j >= start && arr.get(j).compareTo(current) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, current);
        }
    }

    /**
     * Partitions the given sublist using a pivot, for the quick sort algorithm.
     *
     * @param <T>           The type of elements in the list, must extend Comparable
     * @param arr           The list to be sorted
     * @param low           The starting index of the sublist
     * @param high          The ending index of the sublist
     * @param pivotStrategy The strategy for choosing the pivot
     * @return The index of the pivot after partitioning
     */
    private static <T extends Comparable<? super T>> int partition(List<T> arr, int low, int high, String pivotStrategy) {
        T pivot = choosePivot(arr, low, high, pivotStrategy);
        Collections.swap(arr, high, arr.indexOf(pivot));
        int partitionIndex = low;

        for (int i = low; i < high; i++) {
            if (arr.get(i).compareTo(pivot) <= 0) {
                Collections.swap(arr, i, partitionIndex);
                partitionIndex++;
            }
        }
        Collections.swap(arr, partitionIndex, high);
        return partitionIndex;
    }

    /**
     * Chooses a pivot for the quick sort algorithm using the specified strategy.
     *
     * @param <T>           The type of elements in the list, must extend Comparable
     * @param arr           The list to be sorted
     * @param low           The starting index of the sublist
     * @param high          The ending index of the sublist
     * @param pivotStrategy The strategy for choosing the pivot
     * @return The chosen pivot element
     */
    private static <T extends Comparable<? super T>> T choosePivot(List<T> arr, int low, int high, String pivotStrategy) {
        switch (pivotStrategy) {
            case "firstElement":
                return arr.get(low);
            case "random":
                return arr.get(low + random.nextInt(high - low + 1));
            case "medianOfThree":
            default:
                return medianOfThree(arr, low, high);
        }
    }

    /**
     * Chooses the median of the first, middle, and last elements of the given sublist as the pivot.
     *
     * @param <T>  The type of elements in the list, must extend Comparable
     * @param arr  The list to be sorted
     * @param low  The starting index of the sublist
     * @param high The ending index of the sublist
     * @return The median of the three elements
     */
    private static <T extends Comparable<? super T>> T medianOfThree(List<T> arr, int low, int high) {
        T first = arr.get(low);
        T middle = arr.get(low + (high - low) / 2);
        T last = arr.get(high);

        if (first.compareTo(middle) > 0) {
            if (middle.compareTo(last) > 0) return middle;
            else if (first.compareTo(last) > 0) return last;
            else return first;
        } else {
            if (first.compareTo(last) > 0) return first;
            else if (middle.compareTo(last) > 0) return last;
            else return middle;
        }
    }

    /**
     * Generates an ArrayList of Integers in ascending order from 1 to the specified size.
     *
     * @param size the size of the ArrayList to generate
     * @return an ArrayList of Integers in ascending order
     */
    public static List<Integer> generateAscending(int size) {
        List<Integer> ascendingList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            ascendingList.add(i);
        }
        return ascendingList;
    }

    /**
     * Generates and returns an ArrayList of Integers from 1 to size in permuted (shuffled) order.
     *
     * @param size the size of the ArrayList to generate
     * @return an ArrayList of Integers in permuted order
     */
    public static List<Integer> generatePermuted(int size) {
        List<Integer> permutedList = generateAscending(size);
        Collections.shuffle(permutedList);
        return permutedList;
    }

    /**
     * Generates and returns an ArrayList of Integers in descending order from size to 1.
     *
     * @param size the size of the ArrayList to generate
     * @return an ArrayList of Integers in descending order
     */
    public static List<Integer> generateDescending(int size) {
        List<Integer> descendingList = new ArrayList<>();
        for (int i = size; i > 0; i--) {
            descendingList.add(i);
        }
        return descendingList;
    }
}
