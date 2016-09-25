/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
    private DList list;

    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements 
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equal according to compareTo().
     **/

    /**
     *  Constructs an empty Set. 
     *
     *  Performance:  runs in O(1) time.
     **/
    public Set() { 
        list = new DList();
    }

    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        return list.length();
    }

    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c) {
        if (c == null) {return;}

        if (list.length() == 0) {
            list.insertFront(c);
            return;
        }

        ListNode curr = list.front();
        ListNode last = list.back();
        try {
            if(c.compareTo(last.item()) > 0) {
                last.insertAfter(c);
                return;
            }
            while(curr.isValidNode()) {
                int sign = c.compareTo(curr.item());
                if(sign == 0){
                    return;
                } else if (sign < 0) {
                    curr.insertBefore(c);
                    return;
                } else {
                    curr = curr.next();
                }
            }                
        } catch(InvalidNodeException e) {
            System.out.println(e);
        }

    }
    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/

    // This is run in O(this.cardinality() * s.cardinality()) time version
    public void union(Set s) {
        if (s.cardinality() == 0) {this.list = s.list;}
        try {
            for(ListNode n = s.list.front(); n.isValidNode(); n = n.next()) {
                this.insert((Comparable)n.item());
            }
        } catch (InvalidNodeException e) {
            System.out.println(e);
        }

    }

    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
    public void intersect(Set s) {
        if (cardinality() == 0 || s.cardinality() == 0) {list = new DList();}
        ListNode p1 = this.list.front();
        ListNode p2 = s.list.front();
        try {
            while(p1.isValidNode() && p2.isValidNode()) {
                Comparable item1 = (Comparable)p1.item();
                Comparable item2 = (Comparable)p2.item();
                int sign = item1.compareTo(item2);
                if(sign == 0) {
                    p1 = p1.next();
                    p2 = p2.next();
                } else if (sign < 0) {
                    p1 = p1.next();
                    p1.prev().remove();
                } else {
                    p2 = p2.next();
                }

            }
            ListNode temp = p1;
            while(p1.isValidNode()) {               
                temp = p1;
                p1 = p1.next();
                temp.remove();
            }
            
        } catch (InvalidNodeException e) {
            System.out.println(e);
        }
    }

    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        String s = "{ ";
        ListNode node = list.front();
        try {
            while(node.isValidNode()) {
                s += " " + node.item() + " ";
                node = node.next();
            }
        } catch(InvalidNodeException e) {
            System.out.println(e);
        }
        s += " }";
        return s;
    }

    public static void main(String[] argv) {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(3));
        System.out.println("Set s = " + s);

        Set s2 = new Set();
        s2.insert(new Integer(4));
        s2.insert(new Integer(5));
        s2.insert(new Integer(5));
        s2.insert(new Integer(6));
        s2.insert(new Integer(7));
        s2.insert(new Integer(8));
        s2.insert(new Integer(9));
        s2.insert(new Integer(10));
        System.out.println("Set s2 = " + s2);

        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(3));
        s3.insert(new Integer(8));
        System.out.println("Set s3 = " + s3);

        System.out.println("test insert");
        s.insert(new Integer(5));
        System.out.println(s);

        s.union(s2);
        System.out.println("After s.union(s2), s = " + s);

        s.intersect(s3);
        System.out.println("After s.intersect(s3), s = " + s);

        System.out.println("s.cardinality() = " + s.cardinality());
        // You may want to add more (ungraded) test code here.
    }
}