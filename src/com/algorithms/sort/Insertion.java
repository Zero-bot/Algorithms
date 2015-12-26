/**
 * 
 */
package com.algorithms.sort;

import java.util.Random;

/** Algorithms
 * @author Marimuthu
 *11:36:41 AM Dec 25, 2015
 */
public class Insertion {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array){
		for(int j=1;j<array.length;j++){
			Comparable key=array[j];
			int i=j-1;
			while(i>=0&&less(key, array[i])){
				//swap(array,i,i+1);
				array[i+1]=array[i];
				i--;
			}array[i+1]=key;
		}
	}

	public static void swap(Comparable[] array,int i,int j){
		Comparable temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
	public static boolean less(Comparable i,Comparable j){
		return i.compareTo(j)<0;
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
