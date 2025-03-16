package comprehensive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class generates random phrases based on grammar file.
 *
 * @author Yuli Wang
 * @version 2024-7-30
 */
public class RandomPhraseGenerator {
    HashMap<String, ArrayList<String>> rules;  // Store the mapping of non-terminals and their generation rules
    Random rng;  // Random number generator for generating random numbers

    /**
     * Constructs a RandomPhraseGenerator instance and parses the given grammar file.
     *
     * @param filePath the path to the grammar file
     * @throws IOException if an I/O error occurs
     */
    public RandomPhraseGenerator(String filePath) throws IOException {
        rules = new HashMap<>();
        rng = new Random();
        commandReader(filePath);
    }

    /**
     * Parses the grammar file and stores the rules in the HashMap.
     *
     * @param filePath the path to the grammar file
     * @throws IOException if an I/O error occurs
     */
    // Parse the file into rules
    private void commandReader(String filePath) throws IOException {
        //BufferedReader reader;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;//read one line at a time
        String currentNonterminal = null;

        // Continuously read each line in the file
        while ((line = reader.readLine()) != null) {
            // Remove whitespace at the beginning and end of lines
            if (line.isEmpty() || line.startsWith("//")) {// If it's a blank line or a comment line, skip it.
                continue;
            }
            if (line.equals("{")) {// In the case of left curly brackets, this indicates the start of a new non-terminator definition
                currentNonterminal = reader.readLine(); // Read the next line as a non-terminator name
                rules.put(currentNonterminal, new ArrayList<>());// Create a list of generation rules for this non-terminator

            } else if (line.equals("}")) {//The end of the current non-terminator definition
                currentNonterminal = null;
            } else if (currentNonterminal != null) {
                rules.get(currentNonterminal).add(line);// Add the current line to the list of generated rules for this non-terminal character
            }
        }
        reader.close();
    }

    /**
     * Generates a random phrase starting from the <start> non-terminal.
     *
     * @return the generated phrase
     */
    public String generatePhrase() {
        return expandNonTerminal("<start>");  // Start generating phrases from <start> non-terminals.
    }

    /**
     * Recursively expands a non-terminal into a phrase.
     *
     * @param nonTerminal the non-terminal to expand
     * @return the expanded phrase
     * @throws IllegalArgumentException if the non-terminal is not defined
     */
    private String expandNonTerminal(String nonTerminal) {
        ArrayList<String> productions = rules.get(nonTerminal);
        if (productions == null) {
            throw new IllegalArgumentException("Undefined non-terminal: " + nonTerminal);
        }
        String production = productions.get(rng.nextInt(productions.size()));  // Randomly select a generation rule
        StringBuilder result = new StringBuilder();
        char[] sentenceStore = production.toCharArray();  // Convert generation rules to character arrays
        StringBuilder currentNonTerminal = new StringBuilder();  // Store the current non-terminator
        boolean isNonTerminal = false;  // Mark if currently processing non-terminals

        for (char c : sentenceStore) {//Reading Sentences
            if (c == '<') {
                isNonTerminal = true;
                currentNonTerminal = new StringBuilder();  // Reset the current non-terminator
                currentNonTerminal.append(c);
            } else if (c == '>') {
                isNonTerminal = false;
                currentNonTerminal.append(c);
                result.append(expandNonTerminal(currentNonTerminal.toString()));// end the traversal of the terminator, output it to the result
            } else {// Non-terminator case
                if (isNonTerminal) {
                    currentNonTerminal.append(c);
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();

    }

    /**
     * The main method to run the RandomPhraseGenerator.
     *
     * @param args the command line arguments, where the first argument is the path to the grammar file
     *             and the second argument is the number of phrases to generate
     */

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("grammar_file_path and number_of_phrases>");
            System.exit(1);
        }

        String filePath = args[0];
        int numPhrases = Integer.parseInt(args[1]);

        try {
            RandomPhraseGenerator generator = new RandomPhraseGenerator(filePath);
            for (int i = 0; i < numPhrases; i++) {
                System.out.println(generator.generatePhrase());
            }
        } catch (IOException e) {
            System.err.println("Error reading grammar file: " + filePath);
            System.exit(1);
        }
    }
}

