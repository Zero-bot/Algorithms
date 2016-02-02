/**
 * 
 */
package com.algorithms.problems;

import java.util.Map;
import java.util.TreeMap;

/** Comp
 * @author Marimuthu
 *11:33:58 AM Jan 31, 2016
 */
public class Permutation {
	public static void permute(char[] input){
		Map<Character, Integer> map=new TreeMap<>();
		for(char c:input){
			if(map.containsKey(c))
				map.put(c, map.get(c)+1);
			else
				map.put(c, 1);
		}
		char str[]=new char[map.size()];
		int count[]=new int[map.size()];
		int index=0;
		for(Map.Entry<Character, Integer> entry:map.entrySet()){
			str[index]=entry.getKey();
			count[index]=entry.getValue();
			index++;
		}
		char result[]=new char[input.length];
		permuteUtils(str, count, result, 0);
	}
	private static void  permuteUtils(char[] str,int[] count,char result[],int level){
		if(level==result.length){
			print(result);
			return;
		}
		for(int i=0;i<str.length;i++){
			if(count[i]==0){
				continue;
			}
			result[level]=str[i];
			count[i]--;
			permuteUtils(str, count, result, level+1);
			count[i]++;
		}
	}
	private static void print(char[] str){
		for(int i=0;i<str.length;i++)
			System.out.print(str[i]);
		System.out.println();
	}
	public static void main(String[] args){
		permute("aabc".toCharArray());
	}

}
