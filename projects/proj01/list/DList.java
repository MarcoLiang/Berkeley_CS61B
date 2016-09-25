/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList<T> {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode<T> head;//sentinel
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode<T> newNode(T item, DListNode<T> prev, DListNode<T> next) {
    return new DListNode<T>(item, prev, next, this);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
    head = newNode(null, null, null);
    head.next = head;
    head.prev = head;
  }

  public DList(T item) {
    this();
    this.insertFront(item);
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  public boolean isSentinel(DListNode<T> node) {
    return node == head;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(T item) {
    // Your solution here.
    DListNode<T> node = newNode(item, head, head.next);
    head.next.prev = node;
    head.next = node;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(T item) {
    // Your solution here.
    DListNode<T> node  = newNode(item, head.prev, head);
    head.prev.next = node;
    head.prev = node;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode<T> front() {
    if (isEmpty()) return null;
    return head.next;
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode<T> back() {
    if (isEmpty()) return null;
    return head.prev;
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(T item, DListNode<T> node) {
    // Your solution here.
    if (node == null || node.lst != this) return;
    DListNode<T> temp = newNode(item, node.prev, node.next);
    node.next.prev = temp;
    node.next = temp;
    size++;
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(T item, DListNode<T> node) {
    // Your solution here.
    if (node == null || node.lst != this) return;
    DListNode<T> temp = newNode(item, node.prev, node);
    node.prev.next = temp;
    node.prev = temp;
    size++;
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode<T> node) {
    // Your solution here.
    if (node == null || node.lst != this) return;
    node.prev.next = node.next;
    node.next.prev = node.prev;
    size--;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode<T> current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public static void main(String[] args) {
    System.out.println("testing");
    DList<Integer> lst = new DList<Integer>();
    System.out.println("length :"+lst.length());
    System.out.println("is empty? "+lst.isEmpty());
    System.out.println("Testing empty: " + lst);
    lst.insertFront(3);
    System.out.println("Testing insert front "+lst);
    lst.insertBack(55);
    System.out.println("Testing insert back "+ lst);
    lst.insertFront(1000);
    System.out.println("Testing insert front with 1000 "+lst);
    System.out.println("Testing front() "+lst.front().item);
    System.out.println("Testing back() "+lst.back().item);
    // test insertAfter and insertBefore
    DListNode<Integer> midNode = lst.front().next;
    lst.insertBefore(999,midNode);
    System.out.println("Testing insertBefore of 3: "+lst);
    lst.insertAfter(66,midNode);
    System.out.println("Testing insertAfter of 3: "+lst);

    // test remove
    lst.remove(lst.front().next);
    System.out.println("Testing remove '999': " + lst);
    // test DList<int[]>
    String[] run = {"length", "red", "green", "blue"};
    DList<String[]> runs = new DList<String[]>(run);
    String rlst = "Testing DList<Array> : ";
    for (String r : runs.front().item) {
      rlst += r + " ";
    }
    System.out.println(rlst);
  }
}
