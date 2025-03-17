package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class used to be tested for ListSorter class.
 *
 * @author  Ulysses Wang
 * @version 2024-6-13
 */
public class ListSorterTest {

    private List<Integer> emptyIntegerArray;
    private List<Integer> oneIntegerArray;
    private List<Integer> ascendingList;
    private List<Integer> permutedList;
    private List<Integer> descendingList;
    private List<String> stringArray;

    private <T extends Comparable<? super T>> List<T> copyAndSort(List<T> sourceArray) {
        List<T> destinationArray = new ArrayList<>(sourceArray);
        Collections.sort(destinationArray);
        return destinationArray;
    }

    @BeforeEach
    void setUp() {
        // Set up three types of lists for testing: ascending, permuted, and descending.
        emptyIntegerArray = new ArrayList<>();
        oneIntegerArray = ListSorter.generateAscending(1);
        ascendingList = ListSorter.generateAscending(100);
        permutedList = ListSorter.generatePermuted(100);
        descendingList = ListSorter.generateDescending(100);

        stringArray = new ArrayList<>();
        Collections.addAll(stringArray, "d", "d", "d", "d", "d", "d", "d", "d", "d", "d", "c", "b", "a");
    }

    // Test cases for mergesort

    @Test
    void testEmptyIntegerArrayMergesort() {
        ListSorter.mergesort(emptyIntegerArray);
        assertEquals(new ArrayList<>(), emptyIntegerArray);
    }

    @Test
    void testOneIntegerArrayMergesort() {
        List<Integer> testCopy = copyAndSort(oneIntegerArray);
        ListSorter.mergesort(oneIntegerArray);
        assertEquals(testCopy, oneIntegerArray);
    }

    @Test
    void testAscendingIntegerArrayMergesort() {
        ListSorter.mergesort(ascendingList);
        List<Integer> expectedList = ListSorter.generateAscending(100);
        assertEquals(expectedList, ascendingList);
    }

    @Test
    void testPermutedIntegerArrayMergesort() {
        ListSorter.mergesort(permutedList);
        List<Integer> expectedList = ListSorter.generateAscending(100);
        assertEquals(expectedList, permutedList);
    }

    @Test
    void testDescendingIntegerArrayMergesort() {
        ListSorter.mergesort(descendingList);
        List<Integer> expectedList = ListSorter.generateAscending(100);
        assertEquals(expectedList, descendingList);
    }

    @Test
    void testStringArrayMergesort() {
        List<String> testCopy = copyAndSort(stringArray);
        ListSorter.mergesort(stringArray);
        assertEquals(testCopy, stringArray);
    }

    // Test cases for quicksort

    @Test
    void testEmptyIntegerArrayQuicksort() {
        ListSorter.quicksort(emptyIntegerArray);
        assertEquals(new ArrayList<>(), emptyIntegerArray);
    }

    @Test
    void testOneIntegerArrayQuicksort() {
        List<Integer> testCopy = copyAndSort(oneIntegerArray);
        ListSorter.quicksort(oneIntegerArray);
        assertEquals(testCopy, oneIntegerArray);
    }

    @Test
    void testAscendingIntegerArrayQuicksort() {
        List<Integer> testCopy = copyAndSort(ascendingList);
        ListSorter.quicksort(ascendingList);
        assertEquals(testCopy, ascendingList);
    }

    @Test
    void testPermutedIntegerArrayQuicksort() {
        List<Integer> testCopy = copyAndSort(permutedList);
        ListSorter.quicksort(permutedList);
        assertEquals(testCopy, permutedList);
    }

    @Test
    void testDescendingIntegerArrayQuicksort() {
        List<Integer> testCopy = copyAndSort(descendingList);
        ListSorter.quicksort(descendingList);
        assertEquals(testCopy, descendingList);
    }

    @Test
    void testStringArrayQuicksort() {
        List<String> testCopy = copyAndSort(stringArray);
        ListSorter.quicksort(stringArray);
        assertEquals(testCopy, stringArray);
    }

    // Other test cases

    @Test
    void testEmptyList() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        ListSorter.mergesort(emptyList);
        assertEquals(0, emptyList.size());
    }

    @Test
    void testSingleElementList() {
        ArrayList<Integer> singleElementList = new ArrayList<>(Collections.singletonList(20));
        ListSorter.quicksort(singleElementList);
        assertEquals(Collections.singletonList(20), singleElementList);
    }

    @Test
    void testDuplicateElements() {
        ArrayList<Integer> duplicateList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1));
        ListSorter.mergesort(duplicateList);
        assertEquals(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5), duplicateList);
    }
}
