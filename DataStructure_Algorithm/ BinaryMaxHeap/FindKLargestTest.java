package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the FindKLargest class.
 * It tests the methods for finding the k largest items in a list using
 * both a binary max heap and sorting, with and without a custom comparator.
 *
 * @author Yuli Wang
 * @version 2024/7/18
 */
public class FindKLargestTest {

    @Test
    public void testFindKLargestHeap() {
        List<Integer> items = Arrays.asList(1, 23, 12, 9, 30, 2, 50);
        List<Integer> result = FindKLargest.findKLargestHeap(items, 3);
        assertEquals(Arrays.asList(50, 30, 23), result);
    }

    @Test
    public void testFindKLargestHeapWithComparator() {
        List<Integer> items = Arrays.asList(1, 23, 12, 9, 30, 2, 50);
        List<Integer> result = FindKLargest.findKLargestHeap(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList(1, 2, 9), result);
    }

    @Test
    public void testFindKLargestSort() {
        List<Integer> items = Arrays.asList(1, 23, 12, 9, 30, 2, 50);
        List<Integer> result = FindKLargest.findKLargestSort(items, 3);
        assertEquals(Arrays.asList(50, 30, 23), result);
    }

    @Test
    public void testFindKLargestSortWithComparator() {
        List<Integer> items = Arrays.asList(1, 23, 12, 9, 30, 2, 50);
        List<Integer> result = FindKLargest.findKLargestSort(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList(1, 2, 9), result);
    }

    @Test
    public void testInvalidK() {
        List<Integer> items = Arrays.asList(1, 23, 12, 9, 30, 2, 50);

        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestHeap(items, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestHeap(items, 8);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestSort(items, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            FindKLargest.findKLargestSort(items, 8);
        });
    }

    // Test with other data types
    @Test
    public void testFindKLargestHeapWithStrings() {
        List<String> items = Arrays.asList("a", "z", "b", "y", "c", "x", "d");
        List<String> result = FindKLargest.findKLargestHeap(items, 3);
        assertEquals(Arrays.asList("z", "y", "x"), result);
    }

    @Test
    public void testFindKLargestHeapWithStringsAndComparator() {
        List<String> items = Arrays.asList("a", "z", "b", "y", "c", "x", "d");
        List<String> result = FindKLargest.findKLargestHeap(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    public void testFindKLargestSortWithStrings() {
        List<String> items = Arrays.asList("a", "z", "b", "y", "c", "x", "d");
        List<String> result = FindKLargest.findKLargestSort(items, 3);
        assertEquals(Arrays.asList("z", "y", "x"), result);
    }

    @Test
    public void testFindKLargestSortWithStringsAndComparator() {
        List<String> items = Arrays.asList("a", "z", "b", "y", "c", "x", "d");
        List<String> result = FindKLargest.findKLargestSort(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    public void testFindKLargestHeapWithDoubles() {
        List<Double> items = Arrays.asList(1.1, 23.23, 12.12, 9.9, 30.3, 2.2, 50.5);
        List<Double> result = FindKLargest.findKLargestHeap(items, 3);
        assertEquals(Arrays.asList(50.5, 30.3, 23.23), result);
    }

    @Test
    public void testFindKLargestHeapWithDoublesAndComparator() {
        List<Double> items = Arrays.asList(1.1, 23.23, 12.12, 9.9, 30.3, 2.2, 50.5);
        List<Double> result = FindKLargest.findKLargestHeap(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList(1.1, 2.2, 9.9), result);
    }

    @Test
    public void testFindKLargestSortWithDoubles() {
        List<Double> items = Arrays.asList(1.1, 23.23, 12.12, 9.9, 30.3, 2.2, 50.5);
        List<Double> result = FindKLargest.findKLargestSort(items, 3);
        assertEquals(Arrays.asList(50.5, 30.3, 23.23), result);
    }

    @Test
    public void testFindKLargestSortWithDoublesAndComparator() {
        List<Double> items = Arrays.asList(1.1, 23.23, 12.12, 9.9, 30.3, 2.2, 50.5);
        List<Double> result = FindKLargest.findKLargestSort(items, 3, Comparator.reverseOrder());
        assertEquals(Arrays.asList(1.1, 2.2, 9.9), result);
    }
}
