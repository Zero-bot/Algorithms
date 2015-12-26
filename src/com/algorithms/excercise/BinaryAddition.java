/**
 * 
 */
package com.algorithms.excercise;

/** Algorithms
 * @author Marimuthu
 *9:47:45 PM Dec 26, 2015
 */
public class BinaryAddition {
	public enum bits{
		ONE{
			@Override
			public String toString(){
				return "1";
			}
		},
		ZERO{
			@Override
			public String toString(){
				return "0";
			}
		},
	}
	private bits[] A;
	private bits[] B;
	private bits[] C;
	/**
	 * @param a
	 * @param b
	 */
	public BinaryAddition(bits[] A, bits[] B) {
		super();
		this.A = A;
		this.B = B;
		this.C=new bits[A.length+1];
	}
	public bits[] add(){

		bits remainder=bits.ZERO;
		for(int i=A.length-1;i>=0;i--){
			bits current=value(value(this.A[i],this.B[i]),remainder);
			remainder=remainder(this.A[i],this.B[i],remainder);
			C[i+1]=current;
		}
		C[0]=remainder;
		return C;
	}
	private bits value(bits first,bits next){
		if(first==bits.ONE&&next==bits.ONE) return bits.ZERO;
		else if(first==bits.ONE||next==bits.ONE) return bits.ONE;
		else return bits.ZERO;
	}
	private bits remainder(bits first,bits next,bits remainder){
		if(first==bits.ONE&&next==bits.ONE&&(remainder==bits.ZERO||remainder==bits.ONE)) return bits.ONE;
		else if((first==bits.ONE||next==bits.ONE)&&remainder==bits.ONE) return bits.ONE;
		return bits.ZERO;
	}
	public void print(){
		for(int i=0;i<C.length;i++)
			System.out.print(C[i]);
		System.out.println();
	}

	public static void main(String[] args){
		bits[] A=new bits[]{bits.ONE,bits.ZERO,bits.ONE,bits.ZERO};
		bits[] B=new bits[]{bits.ONE,bits.ONE,bits.ZERO,bits.ONE};
		BinaryAddition addition=new BinaryAddition(A,B);
		addition.add();
		addition.print();
	}
}
