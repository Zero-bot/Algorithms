/**
 * 
 */
package com.algorithms.excercise;

/** Comp
 * @author Marimuthu
 *4:04:49 PM Jan 9, 2016
 */
public class KeyInRotatedSortedArray {
	public static void main(String[] args){
		Integer[] a=new Integer[]{4,5,6,7,8,9,1,2,3};
		System.out.println(binarysearch(a, 3, 0, a.length-1));
	}
	public static int binarysearch(Comparable[] a,Comparable key,int start,int end){
		if(start>end) return -1;
		int mid=start+((end-start)/2);
		int diff=a[mid].compareTo(key);
		if(diff==0) return mid;
		if(a[start].compareTo(a[mid])<=0){
			if(a[start].compareTo(key)<=0&&a[mid].compareTo(key)>=0)
				return binarysearch(a, key, start, mid-1);
			else{
				return binarysearch(a, key, mid+1, end);
			}
		}else{
			if(a[mid].compareTo(key)<=0&&a[end].compareTo(key)>=0){
				return binarysearch(a, key, mid+1, end);
			}
			else
				return binarysearch(a, key, start, mid-1);
						
		}
	}

}
