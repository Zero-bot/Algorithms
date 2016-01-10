/**
 * 
 */
package datastructures;

/** Comp
 * @author Marimuthu
 *7:55:37 PM Jan 10, 2016
 */
public class BinaryTree<Key extends Comparable<Key>,Item> {
	private Node root;
	private class Node<Key extends Comparable<Key>,Item>{
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
	public static void main(String args[]){
		BinaryTree<Integer, String> bst=new BinaryTree<>();
		bst.put(11,"Marimuthu");
		bst.put(10,"everybody");
		bst.put(12,"nobody");
		bst.put(0, "Sombody");
		bst.put(6,"six");
		bst.put(122, "max");
		bst.put(20, "ksd");
		bst.preorder();
		bst.inorder();
		bst.postorder();
		bst.levelOrder();
		bst.zizzag();
	}
}
