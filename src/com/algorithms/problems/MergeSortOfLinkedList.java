/**
 * 
 */
package com.algorithms.problems;


/** Comp
 * @author Marimuthu
 *11:07:15 AM Jan 20, 2016
 */
public class MergeSortOfLinkedList {
	public static Node sort(Node head){
		if(head==null || head.next==null) return head;
		Node slow=head;
		Node fast=head.next;
		while(fast!=null && fast.next!=null){
			head=head.next;
			fast=fast.next.next;
		}
		fast=head.next;
		head.next=null;
		return merge(sort(slow),sort(fast));
	}
	public static Node merge(Node p1, Node p2){
		Node temp=new Node(0);
		Node head=temp;
		Node fake=head;
		while(p1!=null&&p2!=null){
			int cmp=p1.item.compareTo(p2.item);
			if(cmp<=0){
				fake.next=p1;
				fake=p1;
				p1=p1.next;
			}
			else{
				fake.next=p2;
				fake=p2;
				p2=p2.next;
			}
		}fake.next=(p1==null)?p2:p1;
		return head.next;
	}
	
	public static void main(String args[]){
		List<Integer> list=new List();
		list.put(3);
		list.put(0);
		list.put(9);
		list.put(2);
		list.put(12);
		list.put(20);
		list.put(93);
		list.put(24);
		list.print();
		list.head=sort(list.head);
		list.print();
		list.reverse();
		list.print();
	}

}
class List<Item extends Comparable<Item>>{
	Node head;
	int N;
	
	public void put(Item item){
		if(N==0) head= new Node(item);
		Node temp=head;
		head=new Node(item);
		head.next=temp;
		N++;
	}
	public void print(){
		Node temp=head;
		while(temp!=null){
			System.out.print(temp.item+" ");
			temp=temp.next;
		}
		System.out.println();
	}
	public void reverse(){
		reverse(head);
	}
	public void reverse(Node node){
		if(node==null) return;
		if(node.next==null){
			head=node;
			return;
		}
		reverse(node.next);
		node.next.next=node;
		node.next=null;
	}
}
class Node<Item extends Comparable<Item>>{
	Item item;
	Node next;
	Node(Item item){
		this.item=item;
		next=null;
	}
}
