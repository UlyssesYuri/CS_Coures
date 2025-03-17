package assign04;
/**
 * All test for the AnagramChecker class
 *
 * @author Ulysses Wang
 * @version 06/06/2024
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class AnagramCheckerTest {

    @Test
    public void testSort() {
        assertEquals("ader", AnagramChecker.sort("read"));
        assertEquals("adderss", AnagramChecker.sort("address"));
        assertEquals("", AnagramChecker.sort(""));
        assertEquals("a", AnagramChecker.sort("a"));
        assertEquals("aaa", AnagramChecker.sort("aaa"));
        assertEquals(" !@aabcc", AnagramChecker.sort("ccba a!@"));
    }

    @Test
    public void testInsertionSort() {
        Integer[] array = {8, 3, 5, 21, 31, 1, 11, 15};
        Integer[] sortedArray = {1, 3, 5, 8, 11, 15, 21, 31};
        AnagramChecker.insertionSort(array, Comparator.naturalOrder());
        assertArrayEquals(sortedArray, array);
    }

    @Test
    public void testAreAnagrams() {
        assertTrue(AnagramChecker.areAnagrams("aerd", "read"));
        assertFalse(AnagramChecker.areAnagrams("say", "hi"));
        assertTrue(AnagramChecker.areAnagrams("A gentleman", "Elegant man"));
    }

    @Test
    public void testGetLargestAnagramGroup() {
        String[] words = {};
        String[] expected = {};
        assertArrayEquals(expected, AnagramChecker.getLargestAnagramGroup(words));

        String[] words2 = {"one", "neo", "eon", "two", "wot", "owt"};
        String[] expected2 = {"one", "neo", "eon"};
        assertArrayEquals(expected2, AnagramChecker.getLargestAnagramGroup(words2));

    }


    @Test
    public void testGetLargestAnagramGroupFromFile() {
        String[] expected = {};
        assertArrayEquals(expected, AnagramChecker.getLargestAnagramGroup("sample_word_list.txt"));
    }

}