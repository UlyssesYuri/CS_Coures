package assign08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a "dictionary" of strings using a binary search tree and offers
 * methods for spell-checking documents.
 *
 * @author Prof. Parker and Yuli wang
 * @version July 5, 2024
 */
public class SpellChecker {

    private BinarySearchTree<String> dictionary;

    /**
     * Creates empty dictionary.
     */
    public SpellChecker() {
        dictionary = new BinarySearchTree<String>();
    }

    /**
     * Creates dictionary from a list of words.
     *
     * @param words - the list of strings used to build the dictionary
     */
    public SpellChecker(List<String> words) {
        this();
        buildDictionary(words);
    }

    /**
     * Creates dictionary from a file.
     *
     * @param dictionaryFile - the file containing strings used to build the dictionary
     */
    public SpellChecker(File dictionaryFile) {
        this();
        buildDictionary(readFromFile(dictionaryFile));
    }

    /**
     * Adds a word to the dictionary.
     *
     * @param word - the string to be added to the dictionary
     */
    public void addToDictionary(String word) {
        dictionary.add(word.toLowerCase());
    }

    /**
     * Removes a word from the dictionary.
     *
     * @param word - the string to be removed from the dictionary
     */
    public void removeFromDictionary(String word) {
        dictionary.remove(word.toLowerCase());
    }

    /**
     * Spell-checks a document against the dictionary.
     *
     * @param documentFile - the file containing strings to be looked up in the dictionary
     * @return the list of misspelled words
     */
    public List<String> spellCheck(File documentFile) {
        List<String> wordsToCheck = readFromFile(documentFile);


        List<String> miswords = new ArrayList<>();

        for (String word : wordsToCheck) {
            if (!dictionary.contains(word)) {
                miswords.add(word);
            }
        }


        return miswords;
    }

    /**
     * Determines the number of words that are between begin and end (inclusive).
     *
     * @param begin - the string at the beginning of the range
     * @param end   - the string at the end of the range
     * @return the number of words that are between begin and end
     */
    public int countWordsBetween(String begin, String end) {
        Object[] rangeArray = dictionary.toArrayRange(begin.toLowerCase(), end.toLowerCase());
        return rangeArray.length;
    }

    /**
     * Fills in the dictionary with the input list of words.
     *
     * @param words - the list of strings to be added to the dictionary
     */
    private void buildDictionary(List<String> words) {
        for (String word : words) {
            dictionary.add(word.toLowerCase());
        }
    }

    /**
     * Generates a list of the words contained in the specified file.
     * Note that symbols, digits, and spaces are ignored, and all characters
     * are converted to lowercase.
     *
     * @param file - the file to be read
     * @return the list of the strings in the input file
     */
    private List<String> readFromFile(File file) {
        ArrayList<String> words = new ArrayList<String>();

        try {
            /*
             * Java's Scanner class is a simple lexer for strings and primitive types (see
             * the Java API, if you are unfamiliar).
             */
            Scanner fileInput = new Scanner(file);

            /*
             * The scanner can be directed how to delimit (or divide) the input. By default,
             * it uses whitespace as the delimiter. The following statement specifies
             * anything other than alphabetic characters as a delimiter (so that punctuation
             * and such will be ignored). The string argument is a regular expression that
             * specifies "anything but an alphabetic character". For the assignment, it is
             * acceptable for your understanding of this to be limited.
             */
            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals(""))
                    words.add(s.toLowerCase());
            }

            fileInput.close();

        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found.");
        }

        return words;
    }
}