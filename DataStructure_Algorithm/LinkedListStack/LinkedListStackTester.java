package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListStackTester {

    private LinkedListStack<Integer> emptyStack;
    private LinkedListStack<Integer> oneElementStack;
    private LinkedListStack<Integer> smallStack;
    private LinkedListStack<Integer> RandomStack;

    private Random random = new Random();

    @BeforeEach
    void setUp() throws Exception {
        emptyStack = new LinkedListStack<>();

        oneElementStack = new LinkedListStack<>();
        oneElementStack.push(1);

        smallStack = new LinkedListStack<>();
        for (int i = 0; i < 3; i++)
            smallStack.push(i);

        RandomStack = new LinkedListStack<>();
        for (int i = 0; i < 20; i++)
            RandomStack.push(random.nextInt(20));
    }


    // clear() Tests
    @Test
    void clearEmptyStack() {
        emptyStack.clear();
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void clearOneElementStack() {
        oneElementStack.clear();
        assertTrue(oneElementStack.isEmpty());
    }

    @Test
    void clearStack() {
        smallStack.clear();
        assertTrue(smallStack.isEmpty());
    }

    @Test
    void clearRandomStack() {
        RandomStack.clear();
        assertTrue(RandomStack.isEmpty());
    }

    // peek() Tests
    @Test
    void peekEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyStack.peek();
        });
    }

    @Test
    void peekOneStack() {
        assertEquals(1, oneElementStack.peek());
    }

    @Test
    void peekStack() {
        assertEquals(2, smallStack.peek());
        smallStack.pop();
        assertEquals(1, smallStack.peek());
    }

    // pop() Tests
    @Test
    void popEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            emptyStack.pop();
        });
    }

    @Test
    void popOneStack() {
        assertEquals(1, oneElementStack.pop());
    }

    @Test
    void popStack() {
        assertEquals(2, smallStack.pop());
        assertEquals(1, smallStack.pop());
    }

    //push() Tests
    @Test
    void pushEmptyStack() {
        emptyStack.push(0);
        assertEquals(0, emptyStack.peek());
    }

    @Test
    void pushOneStack() {
        oneElementStack.push(17);
        assertEquals(17, oneElementStack.peek());
    }

    @Test
    void pushSmallStack() {
        smallStack.push(69);
        assertEquals(69, smallStack.peek());
    }

    //size() Tests
    @Test
    void emptySize() {
        assertEquals(0, emptyStack.size());
    }

    @Test
    void oneSize() {
        assertEquals(1, oneElementStack.size());
    }

    @Test
    void Size() {
        assertEquals(3, smallStack.size());
    }

}