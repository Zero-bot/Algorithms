/**
 * 
 */
package com.algorithms.sort;

/** Comp
 * @author Marimuthu
 *2:06:31 PM Jan 10, 2016
 */
public class Merge {
	private static boolean less(Comparable i,Comparable j){
		return i.compareTo(j)<0;
	}
	public static void sort(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}
	private static void sort(Comparable[] a,Comparable[] aux,int low,int high){
		if(low>=high) return;
		int mid=low+(high-low)/2;
		sort(a,aux,low,mid);
		sort(a,aux,mid+1,high);
		merge(a,aux,low,mid,high);
	}
	private static void merge(Comparable[] a,Comparable[] aux,int low,int mid,int high){
		for(int i=low;i<=high;i++)
			aux[i]=a[i];
		int i=low;
		int j=mid+1;
		for(int k=low;k<=high;k++){
			if		(i>mid) a[k]=aux[j++];
			else if	(j>high) a[k]=aux[i++];
			else if	(less(aux[j], aux[i])) a[k]=aux[j++];
			else 	a[k]=aux[i++];
		}
	}
	public static void print(Comparable[] a){
		for(Comparable temp:a)
			System.out.print(temp.toString()+" ");
		System.out.println();
	}
	public static void main(String[] args){
		Integer[] a=new Integer[]{3,4,2,5,1,7,8};
		print(a);
		sort(a);
		print(a);
	}
}
