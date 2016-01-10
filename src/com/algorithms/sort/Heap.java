/**
 * 
 */
package com.algorithms.sort;

import java.util.Iterator;

/** Comp
 * @author Marimuthu
 *11:07:43 PM Jan 8, 2016
 */
public class Heap<Key extends Comparable<Key>> implements Iterable<Key>{
	private Key a[];
	private int N;
	public Heap(int initialCapacity){
		a=(Key[]) new Comparable[initialCapacity];
		N=0;
	}
	public Heap(){
		this(1);
	}
	public boolean isEmpty(){
		return N==0;
	}
	private int left(int i){
		return i<<2;
	}
	private int right(int i){
		return i<<2+1;
	}
	private int parent(int i){
		return i>>1;
	}
	private boolean less(int j,int k){
		return a[j].compareTo(a[k])<0;
	}
	private void swap(int i,int j){
		Key temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	private void sink(int i){
		while(i*2<=N){
			int j=i*2;
			if(j<N&&less(j+1, j)) j++;
			if(less(i, j)) break;
			swap(i, j);
			i=j;
		}
	}
	private void swim(int i){
		while(i>1){
			int j=i/2;
			if(!less(j,i)){
				swap(i, j);
			}
			i=j;
		}
	}
	
	public void insert(Key k){
		a[++N]=k;
		swim(N);
	}
	
	public Key removeMin(){
		swap(1,N);
		Key temp=a[N];
		N--;
		sink(1);
		return temp;
	}
	public static void main(String[] args){
		Heap<Integer> heap=new Heap<Integer>(10);
		heap.insert(10);
		heap.insert(2);
		heap.insert(3);
		heap.insert(9);
		Iterator itr=heap.iterator();
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new HeapIterator();
	}
	private class HeapIterator implements Iterator<Key>{

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 * 
		 */
		Heap<Key> copy;
		public HeapIterator() {
			// TODO Auto-generated constructor stub
			copy=new Heap<Key>(N+1);
			for(int i=1;i<=N;i++)
				copy.insert(a[i]);
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !copy.isEmpty();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Key next() {
			// TODO Auto-generated method stub
			return copy.removeMin();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
