package assign06;

import java.util.NoSuchElementException;

/**
 * Represents a simple text editor with undo and redo capabilities.
 * The text editor supports insertion and deletion of characters,
 * and can undo or redo these operations.
 * It uses two stacks: one for tracking applied edits and one for tracking undone edits.
 *
 * @author  Yuli Wang
 * @version 2024-6-18
 */
public class TextEditor {

    private Stack<Edit> undoStack;
    private Stack<Edit> redoStack;
    private StringBuilder text;

    /**
     * Default constructor.
     * Initializes an empty text editor.
     */
    public TextEditor() {

        this.undoStack = new LinkedListStack<>();
        this.redoStack = new LinkedListStack<>();
        this.text = new StringBuilder();

    }

    /**
     * Constructs a text editor with a given history of edits.
     * Applies each edit to reconstruct the text.
     *
     * @param history A list of edits applied from earliest to latest.
     */
    public TextEditor(SinglyLinkedList<Edit> history) {
        this();
        for (Edit edit : history) {
            edit.apply(this.text);
            this.undoStack.push(edit);
        }
    }

    /**
     * Inserts a character at the specified position in the text.
     *
     * @param character The character to insert.
     * @param position  The position to insert the character.
     */
    public void insert(char character, int position) {
        Edit edit = new Edit(character, position, Edit.INSERT);
        edit.apply(this.text);
        this.undoStack.push(edit);
        this.redoStack.clear();
    }

    /**
     * Removes a character at the specified position in the text.
     *
     * @param position The position to remove the character.
     */
    public void remove(int position) {
        Edit edit = new Edit(this.text.charAt(position), position, Edit.REMOVE);
        edit.apply(this.text);
        this.undoStack.push(edit);
        this.redoStack.clear();
    }

    /**
     * Undoes the most recent edit.
     *
     * @throws NoSuchElementException If there are no edits to undo.
     */
    public void undo() throws NoSuchElementException {
        if (this.undoStack.isEmpty()) {
            throw new NoSuchElementException("No edits to undo.");
        }
        Edit edit = this.undoStack.pop();
        edit.revert(this.text);
        this.redoStack.push(edit);
    }

    /**
     * Redoes the most recently undone edit.
     *
     * @throws NoSuchElementException If there are no edits to redo.
     */
    public void redo() throws NoSuchElementException {
        if (this.redoStack.isEmpty()) {
            throw new NoSuchElementException("No edits to redo.");
        }
        Edit edit = this.redoStack.pop();
        edit.apply(this.text);
        this.undoStack.push(edit);
    }

    /**
     * Returns a history of applied edits in order from least to most recent.
     *
     * @return A list of applied edits.
     */
    public SinglyLinkedList<Edit> history() {
        SinglyLinkedList<Edit> history = new SinglyLinkedList<>();
        Stack<Edit> tempStack = new LinkedListStack<>();

        while (!this.undoStack.isEmpty()) {
            Edit edit = this.undoStack.pop();
            history.insertFirst(edit);
            tempStack.push(edit);
        }
        while (!tempStack.isEmpty()) {
            this.undoStack.push(tempStack.pop());
        }

        return history;
    }

    /**
     * Returns the current text in the editor.
     *
     * @return The current text as a string.
     */
    @Override
    public String toString() {
        return this.text.toString();
    }
}
