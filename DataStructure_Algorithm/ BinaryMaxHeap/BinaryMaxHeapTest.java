package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the BinaryMaxHeap class.
 * It includes tests for different constructors, add, peek, extract,
 * size, isEmpty, clear, and toArray methods.
 * The tests cover different data types such as Integer, String, and Double.
 * Also, tests with and without a custom comparator are included.
 *
 * @author Yuli Wang
 * @version 2024/7/18
 */
public class BinaryMaxHeapTest {

    private BinaryMaxHeap<Integer> intHeap;
    private BinaryMaxHeap<String> stringHeap;
    private BinaryMaxHeap<Double> doubleHeap;

    @BeforeEach
    public void setUp() {
        intHeap = new BinaryMaxHeap<>();
    }

    // Test the unparameterised constructor
    @Test
    public void testAddWithoutCmp() {
        intHeap.add(10);
        intHeap.add(20);
        intHeap.add(5);
        assertEquals(3, intHeap.size());
        assertEquals(20, intHeap.peek());
    }

    @Test
    public void testPeekWithoutCmp() {
        intHeap.add(10);
        intHeap.add(20);
        intHeap.add(5);
        assertEquals(20, intHeap.peek());
    }

    @Test
    public void testExtractWithoutCmp() {
        intHeap.add(10);
        intHeap.add(20);
        intHeap.add(5);
        assertEquals(20, intHeap.extract());
        assertEquals(10, intHeap.extract());
        assertEquals(5, intHeap.extract());
    }

    @Test
    public void testSizeWithoutCmp() {
        assertEquals(0, intHeap.size());
        intHeap.add(10);
        assertEquals(1, intHeap.size());
    }

    @Test
    public void testIsEmptyWithoutCmp() {
        assertTrue(intHeap.isEmpty());
        intHeap.add(10);
        assertFalse(intHeap.isEmpty());
    }

    @Test
    public void testClearWithoutCmp() {
        intHeap.add(10);
        intHeap.add(20);
        intHeap.add(5);
        intHeap.clear();
        assertTrue(intHeap.isEmpty());
    }

    @Test
    public void testToArrayWithoutCmp() {
        intHeap.add(10);
        intHeap.add(20);
        intHeap.add(5);
        Object[] array = intHeap.toArray();
        assertArrayEquals(new Object[]{20, 10, 5}, array);
    }

    @Test
    public void testBuildHeapWithList() {
        BinaryMaxHeap<Integer> listHeap = new BinaryMaxHeap<>(Arrays.asList(10, 20, 5));
        assertEquals(3, listHeap.size());
        assertEquals(20, listHeap.peek());
    }

    @Test
    public void testToArrayWithList() {
        BinaryMaxHeap<Integer> listHeap = new BinaryMaxHeap<>(Arrays.asList(10, 20, 5));
        Object[] array = listHeap.toArray();
        assertArrayEquals(new Object[]{20, 10, 5}, array);
    }

    // Test constructor with comparator
    @Test
    public void testAddWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        cmpHeap.add(10);
        cmpHeap.add(20);
        cmpHeap.add(5);
        assertEquals(3, cmpHeap.size());
        assertEquals(5, cmpHeap.peek());
    }

    @Test
    public void testPeekWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        cmpHeap.add(10);
        cmpHeap.add(20);
        cmpHeap.add(5);
        assertEquals(5, cmpHeap.peek());
    }

    @Test
    public void testExtractWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        cmpHeap.add(10);
        cmpHeap.add(20);
        cmpHeap.add(5);
        assertEquals(5, cmpHeap.extract());
        assertEquals(10, cmpHeap.extract());
        assertEquals(20, cmpHeap.extract());
    }

    @Test
    public void testSizeWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        assertEquals(0, cmpHeap.size());
        cmpHeap.add(10);
        assertEquals(1, cmpHeap.size());
    }

    @Test
    public void testIsEmptyWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        assertTrue(cmpHeap.isEmpty());
        cmpHeap.add(10);
        assertFalse(cmpHeap.isEmpty());
    }

    @Test
    public void testClearWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        cmpHeap.add(10);
        cmpHeap.add(20);
        cmpHeap.add(5);
        cmpHeap.clear();
        assertTrue(cmpHeap.isEmpty());
    }

    @Test
    public void testToArrayWithCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> cmpHeap = new BinaryMaxHeap<>(cmp);
        cmpHeap.add(10);
        cmpHeap.add(20);
        cmpHeap.add(5);
        Object[] array = cmpHeap.toArray();
        assertArrayEquals(new Object[]{5, 20, 10}, array);//There's only one swap here.
    }

    // Testing constructors with lists and comparators
    @Test
    public void testBuildHeapWithListAndCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> listCmpHeap = new BinaryMaxHeap<>(Arrays.asList(10, 20, 5), cmp);
        assertEquals(3, listCmpHeap.size());
        assertEquals(5, listCmpHeap.peek());
    }

    @Test
    public void testToArrayWithListAndCmp() {
        Comparator<Integer> cmp = Comparator.reverseOrder();
        BinaryMaxHeap<Integer> listCmpHeap = new BinaryMaxHeap<>(Arrays.asList(10, 20, 5), cmp);
        Object[] array = listCmpHeap.toArray();
        assertArrayEquals(new Object[]{5, 20, 10}, array);//There's only one swap here.
    }

    // Test other data types
    @Test
    public void testWithStrings() {
        stringHeap = new BinaryMaxHeap<>();
        stringHeap.add("a");
        stringHeap.add("b");
        stringHeap.add("c");
        assertEquals("c", stringHeap.peek());

        BinaryMaxHeap<String> listStringHeap = new BinaryMaxHeap<>(Arrays.asList("a", "b", "c"));
        assertEquals("c", listStringHeap.peek());

        Comparator<String> stringCmp = Comparator.reverseOrder();
        BinaryMaxHeap<String> cmpStringHeap = new BinaryMaxHeap<>(stringCmp);
        cmpStringHeap.add("a");
        cmpStringHeap.add("b");
        cmpStringHeap.add("c");
        assertEquals("a", cmpStringHeap.peek());

        BinaryMaxHeap<String> listCmpStringHeap = new BinaryMaxHeap<>(Arrays.asList("a", "b", "c"), stringCmp);
        assertEquals("a", listCmpStringHeap.peek());
    }

    @Test
    public void testWithDoubles() {
        doubleHeap = new BinaryMaxHeap<>();
        doubleHeap.add(1.1);
        doubleHeap.add(2.2);
        doubleHeap.add(3.3);
        assertEquals(3.3, doubleHeap.peek());

        BinaryMaxHeap<Double> listDoubleHeap = new BinaryMaxHeap<>(Arrays.asList(1.1, 2.2, 3.3));
        assertEquals(3.3, listDoubleHeap.peek());

        Comparator<Double> doubleCmp = Comparator.reverseOrder();
        BinaryMaxHeap<Double> cmpDoubleHeap = new BinaryMaxHeap<>(doubleCmp);
        cmpDoubleHeap.add(1.1);
        cmpDoubleHeap.add(2.2);
        cmpDoubleHeap.add(3.3);
        assertEquals(1.1, cmpDoubleHeap.peek());

        BinaryMaxHeap<Double> listCmpDoubleHeap = new BinaryMaxHeap<>(Arrays.asList(1.1, 2.2, 3.3), doubleCmp);
        assertEquals(1.1, listCmpDoubleHeap.peek());
    }
}
