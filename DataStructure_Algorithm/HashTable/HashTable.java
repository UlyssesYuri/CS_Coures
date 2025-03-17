package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class creates a HashTable with maps and keys to values.
 * It cannot contain duplicate keys, and each key can map to at most one value.
 * Using the doublehash strategy
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 * @author Prof. Parker and Yuli Wang
 * @version July 11, 2024
 */

public class HashTable<K, V> implements Map<K, V> {

    private ArrayList<MapEntry<K, V>> table;
    private int count;
    private int capacity;
    private final double loadFactor = 0.75;

    /**
     * Constructs a new HashTable with an initial capacity of 101.
     */
    public HashTable() {
        this.capacity = 101;
        this.count = 0;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(N)
     */
    @Override
    public void clear() {
        this.count = 0;
        this.capacity = 101;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    /**
     * Determines whether this map contains the specified key.
     * O(1)
     *
     * @param key - the key being searched for
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new NullPointerException();
        int index = indexCalculator(key);
        return table.get(index) != null && table.get(index).getKey().equals(key);
    }

    /**
     * Determines whether this map contains the specified value.
     * O(N)
     *
     * @param value - the value being searched for
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        Iterator<MapEntry<K, V>> iterator = table.iterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> entry = iterator.next();
            if (entry != null) {
                if (value == null && entry.getValue() == null) {
                    return true;
                }
                if (value != null && value.equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list view of the mappings contained in this map, where the ordering of
     * mappings in the list is insignificant.
     * O(N)
     *
     * @return a list containing all mappings (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> entries = new ArrayList<>();
        Iterator<MapEntry<K, V>> iterator = table.iterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> entry = iterator.next();
            if (entry != null && entry.getKey() != null) {
                entries.add(entry);
            }
        }
        return entries;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1)
     *
     * @param key - the key for which to get the mapped value
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException();
        int index = indexCalculator(key);
        MapEntry<K, V> entry = table.get(index);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1)
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * O(1)
     *
     * @param key   - the key for which to update the value (if exists)
     *              or to be added to the table
     * @param value - the value to be mapped to the key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException();

        // Rehash if the load factor threshold is exceeded
        if ((double) count / capacity >= loadFactor) {
            rehash();
        }

        int index = indexCalculator(key);
        MapEntry<K, V> entry = table.get(index);
        if (entry == null || entry.getKey() == null) {  // handling lazy deletion
            table.set(index, new MapEntry<>(key, value));
            count++;
            return null;
        } else {
            V oldValue = entry.getValue();
            table.set(index, new MapEntry<>(key, value));
            return oldValue;
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * O(1)
     *
     * @param key - the key to be removed
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new NullPointerException();
        int index = indexCalculator(key);
        MapEntry<K, V> entry = table.get(index);
        if (entry == null || !entry.getKey().equals(key)) {
            return null;
        } else {
            V oldValue = entry.getValue();
            table.set(index, new MapEntry<>(null, null)); // lazy deletion
            count--;
            return oldValue;
        }
    }

    /**
     * Determines the number of mappings in this map.
     * O(1)
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Calculates the index for a given key using primary and secondary hash functions.
     *
     * @param key - the key for which to calculate the index
     * @return the calculated index for the key
     */
    private int indexCalculator(K key) {
        int hashPrimary = primaryHashCode(key);
        int hashSecond = secondHashCode(key);
        int index = hashPrimary;

        int i = 0;
        while (table.get(index) != null && (table.get(index).getKey() == null || !table.get(index).getKey().equals(key))) {
            index = Math.abs((hashPrimary + i * hashSecond) % capacity);
            i++;
        }
        return index;
    }

    /**
     * Primary hash function for a given key.
     *
     * @param key - the key to hash
     * @return the primary hash value for the key
     */
    private int primaryHashCode(K key) {
        String strKey = key.toString();
        int hash = 0;
        for (int i = 0; i < strKey.length(); i++) {
            hash = (31 * hash + strKey.charAt(i)) % capacity;
        }
        return Math.abs(hash);
    }

    /**
     * Secondary hash function for a given key.
     *
     * @param key - the key to hash
     * @return the secondary hash value for the key
     */
    private int secondHashCode(K key) {
        String strKey = key.toString();
        int hash = 0;
        for (int i = 0; i < strKey.length(); i++) {
            hash = (37 * hash + strKey.charAt(i)) % (capacity - 2);
        }
        return Math.abs(hash) + 1; // Ensure step size is positive

    }

    /**
     * Rehashes the table by doubling its capacity and re-inserting all entries.
     */
    private void rehash() {
        List<MapEntry<K, V>> old = entries();
        capacity = primeMaker(capacity * 2);
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
        count = 0;
        for (MapEntry<K, V> entry : old) {
            if (entry.getKey() != null) {
                put(entry.getKey(), entry.getValue());
            }
        }

    }

    /**
     * Finds the next prime number greater than or equal to n.
     *
     * @param n - the starting number to check for primality
     * @return the next prime number greater than or equal to n
     */
    private int primeMaker(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    /**
     * Checks if a number is prime.
     *
     * @param n - the number to check
     * @return true if n is prime, false otherwise
     */
    private boolean isPrime(int n) {
        int i = 2;
        for (; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return i > Math.sqrt(n);
    }
}