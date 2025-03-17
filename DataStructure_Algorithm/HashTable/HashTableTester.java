package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tester class for testing the HashTable class's methods.
 *
 * @author Prof. Parker and Yuli Wang
 * @version July 11, 2024
 */
class HashTableTester {

    private HashTable<Integer, Integer> emptyIntegerHashTable = new HashTable<>();
    private HashTable<String, String> oneHashTable = new HashTable<>();
    private HashTable<Integer, Integer> normalHashTable = new HashTable<>();

    private Random random = new Random();

    @BeforeEach
    void setUp() throws Exception {
        oneHashTable.put("a", "a");

        normalHashTable.put(1, 1);
        normalHashTable.put(2, 2);
        normalHashTable.put(3, 3);
        normalHashTable.put(4, 4);
        normalHashTable.put(5, 5);
        normalHashTable.put(10, 10);
    }

    //clear()
    @Test
    void testClear() {
        oneHashTable.clear();
        assertEquals(0, oneHashTable.size());
    }

    //containsKey()
    @Test
    void oneContainsKey() {
        assertTrue(oneHashTable.containsKey("a"));
    }

    @Test
    void oneDoesNotContainsKey() {
        assertFalse(oneHashTable.containsKey("b"));
    }

    @Test
    void normalContainsKey() {
        assertTrue(normalHashTable.containsKey(1));
        assertTrue(normalHashTable.containsKey(3));
        assertTrue(normalHashTable.containsKey(5));
        assertTrue(normalHashTable.containsKey(10));
    }

    //containsValue()
    @Test
    void oneContainsValue() {
        assertTrue(oneHashTable.containsValue("a"));
    }

    @Test
    void oneDoesNotContainValue() {
        assertFalse(oneHashTable.containsValue("b"));
    }

    @Test
    void normalContainsValue() {
        assertTrue(normalHashTable.containsValue(1));
        assertTrue(normalHashTable.containsValue(3));
        assertTrue(normalHashTable.containsValue(5));
        assertTrue(normalHashTable.containsValue(10));
    }

    //entries()
    @Test
    void oneEntries() {
        ArrayList<MapEntry<String, String>> expected = new ArrayList<>();
        expected.add(new MapEntry<String, String>("a", "a"));
        assertEquals(expected, oneHashTable.entries());
    }

    //get()
    @Test
    void oneGet() {
        assertEquals("a", oneHashTable.get("a"));
    }

    @Test
    void normalGet() {
        assertEquals(1, normalHashTable.get(1));
        assertEquals(2, normalHashTable.get(2));
        assertEquals(3, normalHashTable.get(3));
        assertEquals(4, normalHashTable.get(4));
        assertEquals(5, normalHashTable.get(5));
        assertEquals(10, normalHashTable.get(10));
    }

    //isEmpty()
    @Test
    void emptyIsEmpty() {
        assertTrue(emptyIntegerHashTable.isEmpty());
    }

    @Test
    void oneIsEmpty() {
        assertFalse(oneHashTable.isEmpty());
    }

    @Test
    void normalIsEmpty() {
        assertFalse(normalHashTable.isEmpty());
    }

    //put()
    @Test
    void onePutReplace() {
        assertEquals("a", oneHashTable.put("a", "b"));
        assertEquals("b", oneHashTable.get("a"));
    }

    @Test
    void emptyPut() {
        for (int i = 0; i < 100; i++)
            emptyIntegerHashTable.put(random.nextInt(20), random.nextInt(10));

        emptyIntegerHashTable.put(1, 99);
        assertEquals(99, emptyIntegerHashTable.get(1));
    }

    @Test
    void putReturnNull() {
        assertEquals(null, normalHashTable.put(82, 82));
    }

    @Test
    void putNull() {

        assertEquals(null, oneHashTable.put("b", null));
        assertTrue(oneHashTable.containsValue(null));
        assertTrue(oneHashTable.containsKey("b"));
        assertEquals(null, oneHashTable.get("b"));
        assertEquals(null, oneHashTable.remove("b"));

    }

    //remove()
    @Test
    void oneRemove() {
        assertEquals("a", oneHashTable.remove("a"));
    }

    @Test
    void emptyRemove() {
        for (int i = 0; i < 100; i++)
            emptyIntegerHashTable.put(i * 6, i);

        for (int i = 0; i < 50; i++)
            emptyIntegerHashTable.remove(i * 6);

        assertEquals(50, emptyIntegerHashTable.size());
    }

    @Test
    void removeReturnNull() {
        assertEquals(null, normalHashTable.remove(8));
    }

    //size()
    @Test
    void emptySize() {
        assertEquals(0, emptyIntegerHashTable.size());
    }

    @Test
    void oneSize() {
        assertEquals(1, oneHashTable.size());
    }

    @Test
    void normalSize() {
        assertEquals(6, normalHashTable.size());
    }

    @Test
    void equalsBadHash() {
        StudentBadHash Yang = new StudentBadHash(1397670, "Yang", "Yu");
        StudentBadHash Yang2 = new StudentBadHash(1397670, "Yang", "Yu");

        assertTrue(Yang.equals(Yang2) && Yang.hashCode() == Yang2.hashCode());
    }

    @Test
    void equalsMediumHash() {
        StudentMediumHash Yang = new StudentMediumHash(1397670, "Yang", "Yu");
        StudentMediumHash Yang2 = new StudentMediumHash(1397670, "Yang", "Yu");

        assertTrue(Yang.equals(Yang2) && Yang.hashCode() == Yang2.hashCode());
    }

    @Test
    void equalsGoodHash() {
        StudentGoodHash Yang = new StudentGoodHash(1397670, "Yang", "Yu");
        StudentGoodHash Yang2 = new StudentGoodHash(1397670, "Yang", "Yu");

        assertTrue(Yang.equals(Yang2) && Yang.hashCode() == Yang2.hashCode());
    }


}