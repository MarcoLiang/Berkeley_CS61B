/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private int numEntries;
  private int numBuckets;
  private List[] table;
  private int hashCodePrime;
  private int numCollisions;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    numEntries = 0;
    numBuckets = nextPrime(sizeEstimate);
    table = new DList[numBuckets];
    for (int i = 0; i < numBuckets; i++) {
      table[i] = new DList();
    }
    hashCodePrime = nextPrime(numBuckets * 100);
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(100);
  }

  /** 
   *  Find a prime number >= i
   *  @param i the number to start at to find next prime
   *  @return the first prim >= i
   **/

  public static int nextPrime(int i) {
    while(true) {
      if (isPrime(i)) return i;
      i++;
    }
  }

  /** 
   *  isPrime(int i) checks if a number 'i' is prime or not
   *  @param i the number to be check is prime or not
   *  @return true if is, otherwise false
   **/

  public static boolean isPrime(int i) {
    int divisor = 2;
    while (divisor * divisor <= i) {
      if (i % divisor == 0) return false;
      divisor++;
    }
    return true;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return ((2 * code + 3) % hashCodePrime + hashCodePrime) % numBuckets;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return numEntries;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return numEntries == 0;
  }

  /** 
   *  Return the number of collisions in the hash table; 
   *  Expected number of collisions is N - N * (1 - 1/N)^n
   *  @return number of collisions
   **/

  public int collisions() {
    // Replace the following line with your solution.
    return numCollisions;
  }

  /** 
   *  Return the number of buckets in the hash table; 
   *  @return number of buckets
   **/

  public int buckets() {
    // Replace the following line with your solution.
    return numBuckets;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry e = new Entry();
    e.key = key;
    e.value = value;
    int hashCode = compFunction(key.hashCode());
    // count number of collision
    if (table[hashCode].length() != 0) numCollisions++;
    table[hashCode].insertBack(e);
    numEntries++;
    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int hashCode = compFunction(key.hashCode());
    List lst = table[hashCode];
    ListNode curr = lst.front();
    try {
      while(curr.isValidNode()) {
        if (((Entry)curr.item()).key.equals(key)) {
          return (Entry) curr.item();
        } else {
          curr = curr.next();
        }
      }
    } catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int hashCode = compFunction(key.hashCode());
    List lst = table[hashCode];
    ListNode curr = lst.front();
    try {
      while(curr.isValidNode()) {
        if (((Entry)curr.item()).key.equals(key)) {
          Entry e = (Entry) curr.item();
          curr.remove();
          numEntries--;
          return e;
        } else {
          curr = curr.next();
        }
      }
    } catch(InvalidNodeException e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for (int i = 0; i < numBuckets; i++) {
      table[i] = new DList();
    }
    numEntries = 0;
  }

  public String toString() {
    String s = "[ ";
    for (List lst: table) {
      ListNode curr = lst.front();
      try {
        while(curr.isValidNode()) {
          s += ((Entry)curr.item()).value + " ";
          curr = curr.next();
        }
      } catch(InvalidNodeException e) {
        System.out.println(e);
      }
    }
    s += "]";
    return s;
  }

  public static void main(String[] args) {
    // prime numbers
    int i = 100;
    System.out.println("Testing nextPrime() of " + i + ". Get? " + HashTableChained.nextPrime(i));
    HashTableChained table1 = new HashTableChained(500);
    System.out.println("Table 1 has " + table1.numBuckets + " buckets");
    System.out.println("Table 1 should be 'empty', and isEmpty() is " + table1.isEmpty());
    HashTableChained table2 = new HashTableChained();
    System.out.println("Table 2 has " + table2.numBuckets + " buckets");
    // compFunction()
    System.out.println("1000's hashCode is " + table1.compFunction(1000) + " in table 1, and " + table2.compFunction(1000) + " in table 2");
    // insert() and find()
    table1.insert(10, "CS61B");
    table1.insert(20, "CS61A");
    System.out.println("Testing insert() entries, 'CS61A', 'CS61B'. The hashTable is " + table1);
    System.out.println("Testing find() 'CS61B' in table 1 with KEY 10. Get? " + table1.find(10).value);
    // remove()
    System.out.println("Testing remove() 'CS61B' in table 1 with KEY 10. Get? " + table1.remove(10).value + ". The current hashTable is " + table1);
    // make empty
    table1.makeEmpty();
    System.out.println("Testing makeEmpty(). table1 is " + table1);
    // 127 & 16908799
    System.out.println("Is 127 a prime? " + HashTableChained.isPrime(127));
    System.out.println("Is 16908799 a prime? " + HashTableChained.isPrime(16908799));
  }

}
