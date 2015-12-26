/**
 * 
 */
package com.algorithms.sort;

import java.util.Random;

/** Comp
 * @author Marimuthu
 *11:56:14 PM Dec 26, 2015
 */
public class Selection {
	public static void sort(Comparable[] a){
		int minIndex=0;
		for(int i=0;i<a.length-1;i++){
			minIndex=getMin(a, i);
			swap(a, minIndex, i);
		}
	}
	public static void swap(Comparable[] array,int i,int j){
		Comparable temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	private static int getMin(Comparable[] a,int start){
		int min=start;
		for(int i=start;i<a.length;i++)
			if(a[i].compareTo(a[min])<0) min=i;
		return min;
	}
	public static void print(Comparable[] array){
		for(Comparable temp:array)
			System.out.print(temp+",");
		System.out.println();
	}
	
	public static void main(String[] args){
		Random ran=new Random();
		Integer[] array=new Integer[10];
		for(int i=0;i<10;i++)
			array[i]=ran.nextInt(100);
		
		print(array);
		sort(array);
		print(array);
	}

}
