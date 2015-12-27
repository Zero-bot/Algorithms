/**
 * 
 */
package com.algorithms.excercise;


/** Comp
 * @author Marimuthu
 *12:03:26 PM Dec 27, 2015
 */
public class MaxSubArray {
	public static SubArray MaxSub(int[] a,int low,int high){
		if(high==low) return new SubArray(low,high,a[low]);
		int mid=low+(high-low)/2;
		SubArray leftMax=MaxSub(a, low, mid);
		SubArray rightMax=MaxSub(a, mid+1, high);
		SubArray crossMax=MaxCrossSubArray(a, low, mid, high);
		if(leftMax.sum>=rightMax.sum&&leftMax.sum>=crossMax.sum)
			return leftMax;
		else if(rightMax.sum>=leftMax.sum&&rightMax.sum>=crossMax.sum)
			return rightMax;
		else return crossMax;
	}
	
	public static SubArray  MaxCrossSubArray(int[] a,int low,int mid,int high){
		int leftSum=Integer.MIN_VALUE;
		int rightSum=Integer.MIN_VALUE;
		int sum=0;
		int max_left_index=-1;
		int max_right_index=-1;
		for(int i=mid;i>=low;i--){
			sum=sum+a[i];
			if(sum>leftSum){
				leftSum=sum;
				max_left_index=i;
			}
		}
		sum=0;
		for(int i=mid+1;i<=high;i++){
			sum=sum+a[i];
			if(sum>rightSum){
				rightSum=sum;
				max_right_index=i;
				}
		}
		return new SubArray(max_left_index,max_right_index,leftSum+rightSum);
	}
	public static void main(String[] args){
		int[] a=new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		//int[] a=new int[]{-1,6,-2,8,-9};
		SubArray max=MaxSub(a, 0, a.length-1);
		System.out.println(max);
	}
}
class SubArray{
	int low;
	int high;
	int sum;
	public SubArray() {
		low=Integer.MIN_VALUE;
		high=Integer.MIN_VALUE;
		sum=0;
	}
	public SubArray(int low,int high,int sum){
		this.low=low;
		this.high=high;
		this.sum=sum;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubArray [low=" + low + ", high=" + high + ", sum=" + sum + "]";
	}
	
}


