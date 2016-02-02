/**
 * 
 */
package datastructures;

import java.util.Iterator;

/** Comp
 * @author Marimuthu
 *10:57:16 PM Jan 22, 2016
 */
public class Bag<Item> implements Iterable<Item>{
	private Node head;
	private int N;
	class Node{
		Item item;
		Node next;
		Node(Item item){
			this.item=item;
		}
	}
	public int size(){
		return N;
	}
	public void put(Item data){
		if(head==null) {
			head= new Node(data);
			N++;
			return;
		}
		Node temp=head;
		head=new Node(data);
		head.next=temp;
		N++;
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new BagIterator();
	}
	
	private class BagIterator implements Iterator<Item>{

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		Node _head_=head;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return _head_!=null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Node temp=_head_;
			_head_=_head_.next;
			return temp.item;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("remove operation not implemented!");
			
		}
		
	}
	public static void main(String args[]){
		Bag<Integer> bag=new Bag<>();
		bag.put(2);
		bag.put(22);
		for(int b:bag){
			System.out.println(b);
		}
	}

}
