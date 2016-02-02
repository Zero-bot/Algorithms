/**
 * 
 */
package datastructures.graph;

import datastructures.Stack;

/** Comp
 * @author Marimuthu
 *12:59:38 PM Jan 23, 2016
 */
public class DepthFirstSearch {

	/**
	 * @param args
	 */
	private boolean[] marked;
	private int count;
	private int[] pathTo;
	private int souce;
	/**
	 * 
	 */
	public DepthFirstSearch(UnDirectedGraph G,int v) {
		// TODO Auto-generated constructor stub
		marked=new boolean[G.V()];
		pathTo=new int[G.V()];
		this.souce=v;
		dfs(G,v);
	}
	private void dfs(UnDirectedGraph G,int v){ 
		marked[v]=true;
		count++;
		for(int w:G.adj(v)){
			if(!marked[w]) {
				dfs(G,w);
				pathTo[w]=v;
			}
		}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> stack=new Stack<>();
		if(hasPathTo(v)){
		for(int x=v;x!=this.souce;x=pathTo[x])
			stack.push(x);
		stack.push(this.souce);
		}
		return stack;
	}
	public int count(){
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
