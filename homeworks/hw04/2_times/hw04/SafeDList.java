/* DList.java */


/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class SafeDList extends DList{

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
    protected SafeDList lst;
    
    public SafeDList() {
        super();
        lst = this; //??
    }
    
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        DList lst = this.lst;
        return new SafeDListNode(item, prev, next, lst);
        
    }

    /**
     *  DList() constructor for an empty DList.
     */
    public void lockNode(DListNode node) {
        if (node instanceof LockDListNode) {
            ((LockDListNode)node).locked = true;
        }
    }
    
    public void insertAfter(Object item, DListNode node) {
		if (((SafeDListNode)node).lst != this) return;
		super.insertAfter(item, node);
	}

	public void insertBefore(Object item, DListNode node) {
		if (((SafeDListNode)node).lst != this) return;
		super.insertBefore(item, node);
	}
	
    public void remove(DListNode node) {
        if (node == null || node instanceof LockDListNode && ((LockDListNode)node).locked || ((SafeDListNode)node).lst != this ) { return;}
        super.remove(node);
    }
    
    public static void main(String[] args) {
        System.out.println("testing");
        SafeDList lst = new SafeDList();
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

        // test insertAfter and insertBefore
        DListNode midNode = lst.next(lst.front());
        lst.insertBefore("hello",midNode);
        System.out.println("Testing insertBefore of 3: "+lst);
        lst.insertAfter("what-ever",midNode);
        System.out.println("Testing insertAfter of 3: "+lst);
        // test weakness
        SafeDList lst2 = new SafeDList();
        lst2.insertFront(101);
        lst2.insertFront(102);
        System.out.println("lst2 is " + lst2);
        lst2.insertBefore("weakness", lst.front());
        System.out.println("Testing insertBefore() of 1000 in lst using lst2");
        System.out.println("lst: " + lst + " length: " + lst.length() + "\nlst2: " + lst2 + " length: " + lst2.length());
    }
    
    
}
  