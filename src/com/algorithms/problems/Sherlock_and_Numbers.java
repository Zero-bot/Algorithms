/**
 * 
 */
package com.algorithms.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Comp
 * @author Marimuthu
 *1:27:18 PM Jan 16, 2016
 */
public class Sherlock_and_Numbers {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in=br.readLine();
		int T=Integer.parseInt(in);
		
		for(int j=0;j<T;j++){
			String[] input=br.readLine().split(" ");
			Long N=Long.parseLong(input[0]);
			int K=Integer.parseInt(input[1]);
			Long P=Long.parseLong(input[2]);
			Long[] toRemove=new Long[K];
			input=br.readLine().split(" ");
			for(int i=0;i<K;i++){
				toRemove[i]=Long.parseLong(input[i].trim());
			}
			RedBlackBST<Long,Long> tree=new RedBlackBST();
			for(Long i=(long) 1;i<=N;i++){
				tree.put(i, i);
			}
			System.out.println(check(toRemove, P,tree));
			}	
	}
	

	public static Long check(Long[] toRemove,Long p,RedBlackBST<Long,Long> tree){
		for(Long temp:toRemove){
			tree.delete(temp);
		}
		Long temp=tree.select(p);
		if(temp==null) return (long) -1;
		return temp-1;
	}
}
class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED   = true;
	private static final boolean BLACK = false;

	private Node root;     // root of the BST

	// BST helper node data type
	private class Node {
		private Key key;           // key
		private Value val;         // associated data
		private Node left, right;  // links to left and right subtrees
		private boolean color;     // color of parent link
		private int N;             // subtree count

		public Node(Key key, Value val, boolean color, int N) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.N = N;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public RedBlackBST() {
	}

	/***************************************************************************
	 *  Node helper methods.
	 ***************************************************************************/
	// is node x red; false if x is null ?
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}

	// number of node in subtree rooted at x; 0 if x is null
	private int size(Node x) {
		if (x == null) return 0;
		return x.N;
	} 


	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return size(root);
	}

	/**
	 * Is this symbol table empty?
	 * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}


	/***************************************************************************
	 *  Standard BST search.
	 ***************************************************************************/

	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and <tt>null</tt> if the key is not in the symbol table
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public Value get(Key key) {
		if (key == null) throw new NullPointerException("argument to get() is null");
		return get(root, key);
	}

	// value associated with the given key in subtree rooted at x; null if no such key
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}

	/**
	 * Does this symbol table contain the given key?
	 * @param key the key
	 * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
	 *     <tt>false</tt> otherwise
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/***************************************************************************
	 *  Red-black tree insertion.
	 ***************************************************************************/

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is <tt>null</tt>.
	 *
	 * @param key the key
	 * @param val the value
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new NullPointerException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		root = put(root, key, val);
		root.color = BLACK;
		// assert check();
	}

	// insert the key-value pair in the subtree rooted at h
	private Node put(Node h, Key key, Value val) { 
		if (h == null) return new Node(key, val, RED, 1);

		int cmp = key.compareTo(h.key);
		if      (cmp < 0) h.left  = put(h.left,  key, val); 
		else if (cmp > 0) h.right = put(h.right, key, val); 
		else              h.val   = val;

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;

		return h;
	}

	/***************************************************************************
	 *  Red-black tree deletion.
	 ***************************************************************************/

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private Node deleteMin(Node h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}


	/**
	 * Removes the largest key and associated value from the symbol table.
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMax(root);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the maximum key rooted at h
	private Node deleteMax(Node h) { 
		if (isRed(h.left))
			h = rotateRight(h);

		if (h.right == null)
			return null;

		if (!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);

		h.right = deleteMax(h.right);

		return balance(h);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 *
	 * @param  key the key
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public void delete(Key key) { 
		if (key == null) throw new NullPointerException("argument to delete() is null");
		if (!contains(key)) return;

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the given key rooted at h
	private Node delete(Node h, Key key) { 
		// assert get(h, key) != null;

		if (key.compareTo(h.key) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}

	/***************************************************************************
	 *  Red-black tree helper functions.
	 ***************************************************************************/

	// make a left-leaning link lean to the right
	private Node rotateRight(Node h) {
		// assert (h != null) && isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}

	// make a right-leaning link lean to the left
	private Node rotateLeft(Node h) {
		// assert (h != null) && isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}

	// flip the colors of a node and its two children
	private void flipColors(Node h) {
		// h must have opposite color of its two children
		// assert (h != null) && (h.left != null) && (h.right != null);
		// assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
		//    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private Node moveRedLeft(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Node moveRedRight(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// restore red-black tree invariant
	private Node balance(Node h) {
		// assert (h != null);

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}


	/***************************************************************************
	 *  Utility functions.
	 ***************************************************************************/

	/**
	 * Returns the height of the BST (for debugging).
	 * @return the height of the BST (a 1-node tree has height 0)
	 */
	public int height() {
		return height(root);
	}
	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/***************************************************************************
	 *  Ordered symbol table methods.
	 ***************************************************************************/

	/**
	 * Returns the smallest key in the symbol table.
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	} 

	// the smallest key in subtree rooted at x; null if no such key
	private Node min(Node x) { 
		// assert x != null;
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 

	/**
	 * Returns the largest key in the symbol table.
	 * @return the largest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	} 

	// the largest key in the subtree rooted at x; null if no such key
	private Node max(Node x) { 
		// assert x != null;
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 


	/**
	 * Returns the largest key in the symbol table less than or equal to <tt>key</tt>.
	 * @param key the key
	 * @return the largest key in the symbol table less than or equal to <tt>key</tt>
	 * @throws NoSuchElementException if there is no such key
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public Key floor(Key key) {
		if (key == null) throw new NullPointerException("argument to floor() is null");
		if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
		Node x = floor(root, key);
		if (x == null) return null;
		else           return x.key;
	}    

	// the largest key in the subtree rooted at x less than or equal to the given key
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0)  return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t; 
		else           return x;
	}

	/**
	 * Returns the smallest key in the symbol table greater than or equal to <tt>key</tt>.
	 * @param key the key
	 * @return the smallest key in the symbol table greater than or equal to <tt>key</tt>
	 * @throws NoSuchElementException if there is no such key
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public Key ceiling(Key key) {
		if (key == null) throw new NullPointerException("argument to ceiling() is null");
		if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
		Node x = ceiling(root, key);
		if (x == null) return null;
		else           return x.key;  
	}

	// the smallest key in the subtree rooted at x greater than or equal to the given key
	private Node ceiling(Node x, Key key) {  
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0)  return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null) return t; 
		else           return x;
	}

	/**
	 * Return the kth smallest key in the symbol table.
	 * @param k the order statistic
	 * @return the kth smallest key in the symbol table
	 * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
	 *     <em>N</em> &minus; 1
	 */
	public Key select(long k) {
		if (k < 0 || k >= size()) return null;
		Node x = select(root, k);
		return x.key;
	}

	// the key of rank k in the subtree rooted at x
	private Node select(Node x, long k) {
		// assert x != null;
		// assert k >= 0 && k < size(x);
		int t = size(x.left); 
		if      (t > k) return select(x.left,  k); 
		else if (t < k) return select(x.right, k-t-1); 
		else            return x; 
	} 

	/**
	 * Return the number of keys in the symbol table strictly less than <tt>key</tt>.
	 * @param key the key
	 * @return the number of keys in the symbol table strictly less than <tt>key</tt>
	 * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
	 */
	public int rank(Key key) {
		if (key == null) throw new NullPointerException("argument to rank() is null");
		return rank(key, root);
	} 

	// number of keys less than key in the subtree rooted at x
	private int rank(Key key, Node x) {
		if (x == null) return 0; 
		int cmp = key.compareTo(x.key); 
		if      (cmp < 0) return rank(key, x.left); 
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
		else              return size(x.left); 
	} 

	/***************************************************************************
	 *  Range count and range search.
	 ***************************************************************************/

	/**
	 * Returns all keys in the symbol table as an <tt>Iterable</tt>.
	 * To iterate over all of the keys in the symbol table named <tt>st</tt>,
	 * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
	 * @return all keys in the sybol table as an <tt>Iterable</tt>
	 */


	/**
	 * Returns all keys in the symbol table in the given range,
	 * as an <tt>Iterable</tt>.
	 * @return all keys in the sybol table between <tt>lo</tt> 
	 *    (inclusive) and <tt>hi</tt> (exclusive) as an <tt>Iterable</tt>
	 * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
	 *    is <tt>null</tt>
	 */

	// add the keys between lo and hi in the subtree rooted at x
	// to the queue

	/**
	 * Returns the number of keys in the symbol table in the given range.
	 * @return the number of keys in the sybol table between <tt>lo</tt> 
	 *    (inclusive) and <tt>hi</tt> (exclusive)
	 * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
	 *    is <tt>null</tt>
	 */
	public int size(Key lo, Key hi) {
		if (lo == null) throw new NullPointerException("first argument to size() is null");
		if (hi == null) throw new NullPointerException("second argument to size() is null");

		if (lo.compareTo(hi) > 0) return 0;
		if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else              return rank(hi) - rank(lo);
	}


	/***************************************************************************
	 *  Check integrity of red-black tree data structure.
	 ***************************************************************************/


	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	private boolean isBST() {
		return isBST(root, null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, Key min, Key max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	} 


	private boolean isBalanced() { 
		int black = 0;     // number of black links on path from root to min
		Node x = root;
		while (x != null) {
			if (!isRed(x)) black++;
			x = x.left;
		}
		return isBalanced(root, black);
	}

	// does every path from the root to a leaf have the given number of black links?
	private boolean isBalanced(Node x, int black) {
		if (x == null) return black == 0;
		if (!isRed(x)) black--;
		return isBalanced(x.left, black) && isBalanced(x.right, black);
	} 


	/**
	 * Unit tests the <tt>RedBlackBST</tt> data type.
	 */

}