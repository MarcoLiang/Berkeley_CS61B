/* LockDListNode.java */

 

public class SafeDListNode extends DListNode {
	protected DList lst;

	SafeDListNode(Object i, DListNode p, DListNode n, DList lst) {
		super(i, p, n);
		lst = this.lst;
	}
}