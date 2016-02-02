/**
 * 
 */
package com.algorithms.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Comp
 * @author Marimuthu
 *3:19:54 PM Jan 15, 2016
 */
public class FOO_AND_EXAMS {
	private static final Long limit=(long) Math.pow(10, 18);
	public static long solve(long A,long B,long C,long D,Long t){
		
		return  (long) (A*Math.pow(t, 3)+B*Math.pow(t, 2)+C*Math.pow(t, 1)+D);
	}
	public static long search(long start,long end,long K,long A,long B,long C,long D){
		if(D>=K) return 0;
		if(start>end){
			return 0;
		}
		long mid=start+(end-start)/2;
		Long solved=solve(A, B, C, D,mid);
		int cmp=solved.compareTo(K);
		if(cmp==0) return mid;
		if(cmp>0){
			return search(start, mid-1, K, A, B, C, D);
		}
		else{
			solved=solve(A, B, C, D, mid+1);
			if(solved.compareTo(K)<0)
				return search(mid+1, end, K, A, B, C, D);
			else
				return mid;
		}
		
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
        	String campers[]=br.readLine().split(" ");
        	long A=Long.parseLong(campers[0]);
			long B=Long.parseLong(campers[1]);
			long C=Long.parseLong(campers[2]);
			long D=Long.parseLong(campers[3]);
			long K=Long.parseLong(campers[4]);
			long result=search(1, limit, K, A, B, C, D);
			System.out.println(result);
        }
	}
 
}
