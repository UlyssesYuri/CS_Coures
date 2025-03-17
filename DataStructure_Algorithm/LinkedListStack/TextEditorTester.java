package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TextEditor.
 * This class tests the functionality of the TextEditor class,
 * including insertion, deletion, undo, redo, and history of edits.
 * The tests are based on a predefined base text.
 *
 * @author Yuli Wang
 * @version 2024-6-18
 */
class TextEditorTester {
    private TextEditor textEditor;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
    }

    @Test
    void testInsert() {
        textEditor.insert('a', 0);
        assertEquals("a", textEditor.toString());

        textEditor.insert('b', 1);
        assertEquals("ab", textEditor.toString());

        textEditor.insert('c', 1);
        assertEquals("acb", textEditor.toString());
    }

    @Test
    void testRemove() {
        textEditor.insert('a', 0);
        textEditor.insert('b', 1);
        textEditor.insert('c', 2);
        assertEquals("abc", textEditor.toString());

        textEditor.remove(1);
        assertEquals("ac", textEditor.toString());

        textEditor.remove(1);
        assertEquals("a", textEditor.toString());
    }

    @Test
    void testUndo() {
        textEditor.insert('a', 0);
        textEditor.insert('b', 1);
        assertEquals("ab", textEditor.toString());

        textEditor.undo();
        assertEquals("a", textEditor.toString());
        textEditor.undo();
        assertEquals("", textEditor.toString());
        assertThrows(NoSuchElementException.class, textEditor::undo);
    }

    @Test
    void testRedo() {
        textEditor.insert('a', 0);
        textEditor.insert('b', 1);
        assertEquals("ab", textEditor.toString());
        textEditor.undo();
        textEditor.undo();
        assertEquals("", textEditor.toString());
        textEditor.redo();
        assertEquals("a", textEditor.toString());
        textEditor.redo();
        assertEquals("ab", textEditor.toString());
        assertThrows(NoSuchElementException.class, textEditor::redo);
    }

    @Test
    void testUndoRedoSequence() {
        textEditor.insert('a', 0);
        textEditor.insert('b', 1);
        textEditor.insert('c', 2);
        assertEquals("abc", textEditor.toString());

        textEditor.undo();
        assertEquals("ab", textEditor.toString());
        textEditor.undo();
        assertEquals("a", textEditor.toString());
        textEditor.redo();
        assertEquals("ab", textEditor.toString());
        textEditor.redo();
        assertEquals("abc", textEditor.toString());
    }

    @Test
    void testHistory() {
        textEditor.insert('a', 0);
        textEditor.insert('b', 1);
        textEditor.insert('c', 2);
        assertEquals("abc", textEditor.toString());
        SinglyLinkedList<Edit> history = textEditor.history();
        assertEquals(3, history.size());
        Edit firstEdit = history.get(0);
        assertEquals(new Edit('a', 0, Edit.INSERT), firstEdit);
        Edit secondEdit = history.get(1);
        assertEquals(new Edit('b', 1, Edit.INSERT), secondEdit);
        Edit thirdEdit = history.get(2);
        assertEquals(new Edit('c', 2, Edit.INSERT), thirdEdit);
    }
}
