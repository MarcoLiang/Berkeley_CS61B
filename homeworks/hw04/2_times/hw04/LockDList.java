/* DList.java */


/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList{

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
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }

    /**
     *  DList() constructor for an empty DList.
     */
    public void lockNode(DListNode node) {
        if (node instanceof LockDListNode) {
            ((LockDListNode)node).locked = true;
        }
    }

    public void remove(DListNode node) {
        if (node == null || node instanceof LockDListNode && ((LockDListNode)node).locked) return;
        super.remove(node);
    }
    
    public static void main(String[] args) {
	    System.out.println("testing");
	    LockDList lst = new LockDList();
	    System.out.println("length :"+lst.length());
	    System.out.println("is empty? "+lst.isEmpty());
	    System.out.println("Testing empty: " + lst);
	    lst.insertFront(3);
	    System.out.println("Testing insert front "+lst);
	    lst.insertBack(55.55);
	    System.out.println("Testing insert back "+ lst);
	    lst.insertFront(1000);
	    System.out.println("Testing insert front with 1000 "+lst);
	    System.out.println("Testing front() "+lst.front().item);
	    System.out.println("Testing back() "+lst.back().item);
	    // next(), prev()
	    System.out.println("Testing next() of 1000 is " + lst.next(lst.head.next).item);
	    System.out.println("Testing prev() of 55.55 is " + lst.prev(lst.head.prev).item);
	    System.out.println("Testing next() of 55.55 is " + lst.next(lst.back()));
	    System.out.println("Testing prev() of 1000 is " + lst.prev(lst.front()));
	    // test insertAfter and insertBefore
	    DListNode midNode = lst.next(lst.front());
	    lst.insertBefore("hello",midNode);
	    System.out.println("Testing insertBefore of 3: "+lst);
	    lst.insertAfter("whatever",midNode);
	    System.out.println("Testing insertAfter of 3: "+lst);
	    // test remove
	    lst.lockNode(lst.next(lst.front()));
	    lst.remove(lst.next(lst.front()));
	    System.out.println("Testing remove locked 'hello': " + lst);
	    // test remove locked node
	    lst.remove(lst.prev(lst.back()));
	    System.out.println("Testing remove 'whatever': " + lst);
	}
}
  