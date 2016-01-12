/**
 * 
 */
package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Comp
 * @author Marimuthu
 *7:55:37 PM Jan 10, 2016
 */
public class BinaryTree<Key extends Comparable<Key>,Item> {
	private Node root;
	private class Node{
		Key key;
		Item item;
		Node right;
		Node left;
		int N;
		Node(Key key,Item item,int N){
			this.key=key;
			this.item=item;
			this.right=null;
			this.left=null;
			this.N=N;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "[key=" + key + ", item=" + item + "]";
		}
	}
	public void put(Key key,Item item){
		root=put(root,key,item);
	}
	public int size(){
		return size(root);
	}
	private int size(Node node){
		if(node==null) return 0;
		return node.N;
	}
	public boolean isEmpty(){
		return size()==1;
	}
	private Node put(Node node,Key key,Item item){
		if(node==null)
			return new Node(key,item,1);
		int cmp=key.compareTo((Key) node.key);
		if(cmp<0) 		node.left=put(node.left, key, item);
		else if(cmp>0) 	node.right=put(node.right,key, item);
		else 			node.item=item;
		node.N=1+size(node.left)+size(node.right);
		return node;
	}
	public Item get(Key key){
		Node node= get(root,key);
		if(node==null) return null;
		else return  (Item) node.item;
	}
	private Node get(Node node,Key key){
		if(node==null) return null;
		int cmp=node.key.compareTo(key);
		if(cmp==0) return node;
		else if(cmp<0) return get(node.left,key);
		else return get(node.right,key);
	}
	public void inorder(){
		inorder(root);
		System.out.println();
	}
	private void inorder(Node node){
		if(node==null)  return;
		inorder(node.left);
		System.out.print(node+" ");
		inorder(node.right);
	}
	public void preorder(){
		preorder(root);
		System.out.println();
	}
	private void preorder(Node node){
		if(node==null) return;
		System.out.print(node+" ");
		preorder(node.left);
		preorder(node.right);
	}
	public void postorder(){
		postorder(root);
		System.out.println();
	}
	private void postorder(Node node){
		if(node==null) return;
		postorder(node.left);
		postorder(node.right);
		System.out.print(node+" ");
	}
	private void levelOrder(Node node){
		Queue<Node> queue=new Queue<>();
		queue.enqueue(node);
		while(!queue.isEmpty()){
			Node current=queue.dequeue();
			if(current!=null)
				System.out.print(current);
			if(current.left!=null)
				queue.enqueue(current.left);
			if(current.right!=null)
				queue.enqueue(current.right);
		}
	}
	public void levelOrder(){
		levelOrder(root);
		System.out.println();
	}
	public void zizzag(){
		zigzag(root);
		System.out.println();
	}
	private void zigzag(Node node){
		Stack<Node> stack_1=new Stack<>();
		Stack<Node> stack_2=new Stack<>();
		stack_1.push(node);
		while(!stack_1.isEmpty()||!stack_2.isEmpty()){
			Node temp;
			while(!stack_1.isEmpty()){
				temp=stack_1.pop();
				System.out.print(temp+" ");
				if(temp.right!=null)
					stack_2.push(temp.right);
				if(temp.left!=null)
					stack_2.push(temp.left);
			}
			while(!stack_2.isEmpty()){
				temp=stack_2.pop();
				System.out.print(temp+" ");
				if(temp.left!=null)
					stack_1.push(temp.left);
				if(temp.right!=null)
					stack_1.push(temp.right);
			}
		}
	}

	public int height(){
		return height(root);
	}
	private int height(Node node){
		if(node==null) return 0;
		int leftHeight=height(node.left);
		int rightHeight=height(node.right);
		return Math.max(leftHeight, rightHeight)+1;
	}
	private int diameter(Node node){
		if(node==null) return 0;
		int leftHeight=height(node.left);
		int rightHeight=height(node.right);
		int leftDiameter=diameter(node.left);
		int rightDiameter=diameter(node.right);
		return Math.max(leftHeight+rightHeight+1,Math.max(leftDiameter, rightDiameter));
	}
	public int diameter(){
		return diameter(root);
	}
	public void deleteMin(){
		if(isEmpty()) throw new NoSuchElementException("Tree is empty");
		root=deleteMin(root);
	}
	private Node deleteMin(Node node){
		if(node.left==null) return node.right;
		node.left=deleteMin(node.left);
		node.N=size(node.left)+size(node.right)+1;
		return node;
	}

	private Node deleteMax(Node node){
		if(node.right==null) return node.left;
		node.right=deleteMax(node.right);
		node.N=size(node.left)+size(node.right)+1;
		return node;
	}
	public void deleteMax(){
		if(isEmpty()) throw new NoSuchElementException("Tree is empty");
		root=deleteMax(root);
	}

	public void delete(Key key){
		if(isEmpty()) throw new NoSuchElementException();
		root = delete(root,key);
	}
	private Node  delete(Node node,Key key){
		if(node==null) return null;
		int cmp=node.key.compareTo(key);
		if(cmp>0) node.left=delete(node.left,key);
		else if (cmp<0)node.right=delete(node.right, key);
		else{
			if(node.right==null) return node.left;
			if(node.left==null) return node.right;
			Node temp=node;
			node=min(temp.right);
			node.right=deleteMin(temp.right);
			node.left=temp.left;

			node.N=1+size(node.left)+size(node.right);
			return node;
		}
		return node;
	}
	public Node floor(Node node,Key key){
		if (node==null) return null;
		int cmp=node.key.compareTo(key);
		if(cmp>0) return floor(node.left, key);
		else{
			Node temp=floor(node.right,key);
			if(temp!=null) 
				return temp;
			else return node;
		}
	}

	public Node floor(Key key){
		return floor(root, key);
	}
	
	private Node celling(Node node,Key key){
		if(node==null) return null;
		int cmp=node.key.compareTo(key);
		if(cmp==0) return node;
		if(cmp<0) return celling(node.right, key);
		else{
			Node temp=celling(node.left, key);
			if(temp!=null) return temp;
			return node;
		}
		
	}
	
	public Node celling(Key key){
		return celling(root,key);
	}
	
	private void keys(Node node,Queue<Node> q,Key low, Key high){
		if(node==null) return;
		int cmpLow=node.key.compareTo(low);
		int cmpHigh=node.key.compareTo(high);
		if(cmpLow>0) keys(node.left, q, low, high);
		if(cmpLow>=0&&cmpHigh<=0) q.enqueue(node);
		if(cmpHigh<0) keys(node.right, q, low, high);
	}
	public Queue<Node> keys(Key low, Key high){
		Queue<Node> q=new Queue<>();
		keys(root,q,low, high);
		return q;
	}
	private Node min(Node node){
		if(node.left==null) return node;
		return min(node.left);
	}
	public Node min(){
		return min(root);
	}
	
	private boolean isBst(Node node,Key min,Key max){
		if(node==null) return true;
		if(min!=null && node.key.compareTo(min)<0) return false;
		if(max!=null && node.key.compareTo(max)>0) return false;
		return isBst(node.left, min, node.key) && isBst(node.right, node.key, max);
	}
	
	
	
	public boolean isBst(){
		return isBst(root,null,null);
	}
	public static void main(String args[]){
		BinaryTree<Integer, String> bst=new BinaryTree<>();
		bst.put(11,"Marimuthu");
		bst.put(10,"everybody");
		bst.put(12,"nobody");
		bst.put(0, "Sombody");
		bst.put(6,"six");
		bst.put(122, "max");
		bst.put(20, "ksd");
		bst.put(21, "tenty1");
		bst.put(29, "tenty1");
		bst.preorder();
		bst.inorder();
		bst.postorder();
		Iterator itr=bst.keys(2, 13).iterator();
		while(itr.hasNext())
			System.out.print(itr.next()+" ");
		System.out.println();
		System.out.println(bst.isBst());
		
	}
}
