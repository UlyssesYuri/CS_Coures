package assign04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class uses insertion sort to solve anagrams,
 * and the main thing to do is to find the largest anagram group.
 *
 * @author Eric Heisler and Ulysses Wang
 * @version 2024-6-5
 *
 */
public class AnagramChecker {
    /**
     * This method returns the version of the input string in which
     * the characters are sorted lexicographically.
     * The sorting must be accomplished using your implementation of
     * insertion sort described next.
     * @param str input the string
     * @return return the arr after sorted
     */
    public static String sort(String str) {
        char[] charArray = str.toCharArray();
        insertionSort(charArray);
        return new String(charArray);
    }

    /**
     * This generic method sorts the input array using an insertion sort
     * and the input Comparator object.
     * @param arr input char array
     * @param comparator for comparison
     * @param <T> the type of elements contained in the array
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator) {
        for (int i = 1; i < arr.length; i++) {
            T val = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], val) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
    }

    /**
     * This insertion sort for char array.
     * @param arr input char array
     */
    private static void insertionSort(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //Current element in the array
            char val = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > val) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
    }

    /**
     * This method returns true if the two input strings are anagrams of each other,
     * otherwise returns false.
     * This method must call your sort(String) method.
     * @param str1 first string
     * @param str2 second string
     * @return true or false
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return sort(str1).equals(sort(str2));
    }

    /**
     * This method returns the largest group of anagrams in the input
     * array of words, in no particular order.
     * It returns an empty array if there are no anagrams
     * in the input array.  This method must call your
     * areAnagrams(String, String) method and your insertionSort(T[], Comparator)
     * method with a new Comparator class or lambda expression that you design.
     * @param arr enter an array
     * @return returns the largest array as a string
     */
    public static String[] getLargestAnagramGroup(String[] arr) {
        // If the input array is null or empty, an empty array is returned.
        if (arr == null || arr.length == 0) {
            return new String[0];
        }

        // Create an array containing the original arr and its sorted arr.
        ArrAndSorted[] TwoArr = new ArrAndSorted[arr.length];
        for (int i = 0; i < arr.length; i++) {
            TwoArr[i] = new ArrAndSorted(arr[i], sort(arr[i].toLowerCase()));
        }

        // Sorting by sorted arr using insertion sort
        insertionSort(TwoArr, Comparator.comparing(o -> o.sortedArr));

        // Find the largest array after traversal
        List<String> largestGroup = new ArrayList<>();
        List<String> currentGroup = new ArrayList<>();
        currentGroup.add(TwoArr[0].originalArr);
        for (int i = 1; i < TwoArr.length; i++) {
            if (areAnagrams(TwoArr[i].sortedArr, TwoArr[i - 1].sortedArr)) {
                currentGroup.add(TwoArr[i].originalArr);
            } else {
                if (currentGroup.size() > largestGroup.size()) {
                    largestGroup = new ArrayList<>(currentGroup);
                }
                currentGroup.clear();
                currentGroup.add(TwoArr[i].originalArr);
            }
        }

        if (currentGroup.size() > largestGroup.size()) {
            largestGroup = currentGroup;
        }

        return largestGroup.toArray(new String[0]);
    }

    /**
     * The help method used for save the original arr and sorted arr.
     */
    private static class ArrAndSorted {
        String originalArr;
        String sortedArr;

        ArrAndSorted(String originalWord, String sortedWord) {
            this.originalArr = originalWord;
            this.sortedArr = sortedWord;
        }
    }
    /**
     * This method behaves the same as the previous method,
     * but reads the list of words from the input filename.
     * It is assumed that the file contains one word per line.
     * If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
     * This method must call your getLargestAnagramGroup(String[]) method.
     * @param filename input the file
     * @return call the getLargestAnagramGroup method to find the largest anagram group
     */
    public static String[] getLargestAnagramGroup(String filename) {
        List<String> Arr;
        try {
            Arr = Files.readAllLines(Paths.get(filename));
        }  catch (IOException e) {
            return new String[0];
        }

        return getLargestAnagramGroup(Arr.toArray(new String[0]));
    }
}