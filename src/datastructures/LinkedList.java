/**
 * 
 */
package datastructures;

import java.security.Principal;
import java.util.Iterator;

import com.sun.org.apache.regexp.internal.recompile;

/** Comp
 * @author Marimuthu
 *2:32:23 PM Jan 10, 2016
 */
public class LinkedList<Item> implements Iterable<Item>{
	private Node<Item> head;
	private int N;
	private class Node<Item>{
		Node next;
		Item item;
		Node(Item item){
			this.item=item;
			next=null;
		}
		Node(){
			
		}
	}
	public void insert(Item item){
		if(isEmpty()){
			this.head=new Node(item);
			N++;
			return;
		}
		else{
			Node temp=head;
			head=new Node(item);
			head.next=temp;
			N++;
		}
	}
	
	public void reverse(){
		Node reversed=null;
		Node current=head;
		while(current!=null){
			Node next=current.next;
			current.next=reversed;
			reversed=current;
			current=next;
		}
		head=reversed;
	}
	
	public void reverse(int k){
		Node reversed=null;
		Node current=head;
		Node next=new Node<Item>();
		int count=0;
		while(count<k&&current!=null){
			next=current.next;
			current.next=reversed;
			reversed=current;
			current=next;
			count++;
		}
		System.out.println("Printing current");
		head=reversed;
	}
	public void reverseList(){
		head=reverseList(head);
	}
	private Node reverseList(Node node){
		if(node==null||node.next==null) return node;
		Node second=node.next;
		node.next=null;
		Node rest=reverseList(second);
		second.next=node;
		return rest;
	}
	private boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public Node next(Node node){
		return node.next;
	}
	public void print(Node node){
		while(node!=null){
			System.out.print(node.item+" ");
			node=node.next;
		}System.out.println();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		Node temp=head;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return temp!=null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Node node=temp;
			temp=temp.next;
			return (Item) node.item;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Remove operation is not implemented in iterator");
		}
		
	}
	public static void main(String[] args){
		LinkedList<Integer> list=new LinkedList<>();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(6);
		list.reverse(2);
		Iterator itr=list.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next()+" ");
		}
		
	}
}
