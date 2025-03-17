package assign06;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTester {

    private SinglyLinkedList<Integer> emptyIntegerList = new SinglyLinkedList<>();
    private SinglyLinkedList<Integer> oneIntegerList = new SinglyLinkedList<>();
    private SinglyLinkedList<Integer> smallIntegerList = new SinglyLinkedList<>();

    private Random random = new Random();

    @BeforeEach
    void setUp() {
        oneIntegerList.insertFirst(1);

        smallIntegerList.insertFirst(1);
        smallIntegerList.insertFirst(2);
        smallIntegerList.insertFirst(3);
    }

    // insertFirst() Tests
    @Test
    void insertFirstOne() {
        emptyIntegerList.insertFirst(1);
        assertEquals(1, emptyIntegerList.get(0));
    }

    @Test
    void insertFirstThree() {
        emptyIntegerList.insertFirst(1);
        emptyIntegerList.insertFirst(2);
        emptyIntegerList.insertFirst(3);

        assertEquals(3, emptyIntegerList.get(0));
        assertEquals(2, emptyIntegerList.get(1));
        assertEquals(1, emptyIntegerList.get(2));
    }

    // insert() Tests
    @Test
    void insertIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyIntegerList.insert(-1, 1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyIntegerList.insert(1, 1);
        });
    }

    @Test
    void insertAtFirstIndex() {
        emptyIntegerList.insert(0, 1);
        assertEquals(1, emptyIntegerList.get(0));
    }

    @Test
    void insertAFewElementsOrdered() {
        for (int i = 0; i < 20; i++) {
            emptyIntegerList.insert(i, i);
            assertEquals(i, emptyIntegerList.get(i));
        }
    }

    @Test
    void insertAFewElementsRandom() {
        for (int i = 0; i < 20; i++) {
            int randIndex = random.nextInt(i + 1);
            emptyIntegerList.insert(randIndex, i);
            assertEquals(i, emptyIntegerList.get(randIndex));
        }
    }

    // getFirst() Tests
    @Test
    void getFirstEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyIntegerList.getFirst();
        });
    }

    @Test
    void getFirstOneElement() {
        assertEquals(1, oneIntegerList.getFirst());
    }

    @Test
    void getFirstThreeElements() {
        assertEquals(3, smallIntegerList.getFirst());
    }

    // get() Tests
    @Test
    void getOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            oneIntegerList.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            oneIntegerList.get(1);
        });
    }

    @Test
    void getFromEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyIntegerList.get(0);
        });
    }

    @Test
    void getFromOneElement() {
        assertEquals(1, oneIntegerList.get(0));
    }

    // deleteFirst() Tests
    @Test
    void deleteFirstEmptyInteger() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyIntegerList.deleteFirst();
        });
    }

    @Test
    void deleteFirstOneInteger() {
        assertEquals(1, oneIntegerList.deleteFirst());
    }

    @Test
    void deleteFirstThreeInteger() {
        assertEquals(3, smallIntegerList.deleteFirst());
        assertEquals(2, smallIntegerList.deleteFirst());
        assertEquals(1, smallIntegerList.deleteFirst());
        assertThrows(NoSuchElementException.class, () -> {
            emptyIntegerList.deleteFirst();
        });
    }

    //delete() tests
    @Test
    void deleteOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            oneIntegerList.delete(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            oneIntegerList.delete(1);
        });
    }

    @Test
    void deleteOneElement() {
        assertEquals(1, oneIntegerList.delete(0));
        assertEquals(0, oneIntegerList.size());
    }

    @Test
    void deleteAFewElements() {
        for (int i = 0; i < 20; i++)
            emptyIntegerList.insertFirst(i);
        for (int i = 19; i >= 0; i--)
            assertEquals(19 - i, emptyIntegerList.delete(i));
        assertEquals(0, emptyIntegerList.size());
        for (int i = 0; i < 20; i++)
            emptyIntegerList.insertFirst(i);
        for (int i = 0; i < 20; i++)
            assertEquals(19 - i, emptyIntegerList.delete(0));
        assertEquals(0, emptyIntegerList.size());
    }

    @Test
    void deleteRandomElements() {
        for (int i = 0; i < 20; i++)
            emptyIntegerList.insertFirst(i);
        for (int i = 0; i < 20; i++) {
            int randIndex = random.nextInt(20 - i);
            assertEquals(emptyIntegerList.get(randIndex), emptyIntegerList.delete(randIndex));
        }
    }

    // indexOf() Tests
    @Test
    void indexOfEmptyElement() {
        assertEquals(-1, smallIntegerList.indexOf(5));
    }

    @Test
    void IndexOfFirstElement() {
        assertEquals(-1, smallIntegerList.indexOf(4));
    }

    @Test
    void indexOfRandomElement() {
        for (int i = 0; i < 20; i++)
            emptyIntegerList.insert(i, i);
        for (int i = 0; i < 20; i++) {
            int randIndex = random.nextInt(20);
            assertEquals(randIndex, emptyIntegerList.indexOf(randIndex));
        }
    }

    // size() Tests
    @Test
    void emptyListSize() {
        assertEquals(0, emptyIntegerList.size());
    }

    @Test
    void randomListSize() {
        for (int i = 0; i < 20; i++) {
            int randSize = random.nextInt(20);

            for (int j = 0; j < randSize; j++)
                emptyIntegerList.insertFirst(1);

            assertEquals(randSize, emptyIntegerList.size());
            emptyIntegerList.clear();
        }
    }

    // isEmpty() Tests
    @Test
    void emptyListIsEmpty() {
        assertTrue(emptyIntegerList.isEmpty());
    }

    @Test
    void ListIsEmpty() {
        assertFalse(smallIntegerList.isEmpty());
    }

    // clear() Tests
    @Test
    void clearEmptyList() {
        emptyIntegerList.clear();
        assertTrue(emptyIntegerList.isEmpty());
    }

    @Test
    void clearIntegerList() {
        smallIntegerList.clear();
        assertTrue(smallIntegerList.isEmpty());
    }

    @Test
    void clearRandomSizeAndIntegerList() {
        for (int i = 0; i < 20; i++) {
            int randSize = random.nextInt(20);
            for (int j = 0; j < randSize; j++)
                emptyIntegerList.insertFirst(random.nextInt(20));
            emptyIntegerList.clear();
            assertTrue(emptyIntegerList.isEmpty());
        }
    }

    // toArray() Tests
    @Test
    void emptyListToArray() {
        assertArrayEquals(new Integer[]{}, emptyIntegerList.toArray());
    }

    @Test
    void randomizedListToArray() {
        for (int i = 0; i < 20; i++) {
            int randSize = random.nextInt(20);
            Integer[] array = new Integer[randSize];

            for (int j = 0; j < randSize; j++) {
                int randValue = random.nextInt(20);
                emptyIntegerList.insert(j, randValue);
                array[j] = randValue;
            }

            assertArrayEquals(array, emptyIntegerList.toArray());
            emptyIntegerList.clear();
        }
    }

    // ITERATOR TESTS
    // hasNext() Tests
    @Test
    void emptyHasNext() {
        Iterator<Integer> emptyIterator = emptyIntegerList.iterator();
        assertFalse(emptyIterator.hasNext());

    }

    @Test
    void oneHasNext() {
        Iterator<Integer> oneIterator = oneIntegerList.iterator();
        assertTrue(oneIterator.hasNext());
    }

    // next() Tests
    @Test
    void emptyNext() {
        Iterator<Integer> emptyIterator = emptyIntegerList.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            emptyIterator.next();
        });
    }

    @Test
    void oneNext() {
        Iterator<Integer> oneIterator = oneIntegerList.iterator();
        assertEquals(1, oneIterator.next());
    }

    @Test
    void Next() {
        Iterator<Integer> Iterator = smallIntegerList.iterator();
        assertEquals(3, Iterator.next());
        assertEquals(2, Iterator.next());
        assertEquals(1, Iterator.next());
    }

    @Test
    void NextOutOfBounds() {
        Iterator<Integer> Iterator = smallIntegerList.iterator();
        Iterator.next();
        Iterator.next();
        Iterator.next();
        assertThrows(NoSuchElementException.class, () -> {
            Iterator.next();
        });
    }

    // remove() Tests
    @Test
    void emptyRemove() {
        Iterator<Integer> emptyIterator = emptyIntegerList.iterator();
        assertThrows(IllegalStateException.class, () -> {
            emptyIterator.remove();
        });
    }

    @Test
    void oneRemove() {
        Iterator<Integer> oneIterator = oneIntegerList.iterator();
        assertThrows(IllegalStateException.class, () -> {
            oneIterator.remove();
        });
        oneIterator.next();
        oneIterator.remove();
        assertEquals(0, oneIntegerList.size());

    }

    @Test
    void Remove() {
        Iterator<Integer> Iterator = smallIntegerList.iterator();
        Iterator.next();
        Iterator.next();
        Iterator.next();
        Iterator.remove();
        assertEquals(2, smallIntegerList.size());
    }

}