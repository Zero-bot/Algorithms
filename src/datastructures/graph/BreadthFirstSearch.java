/**
 * 
 */
package datastructures.graph;



import datastructures.Queue;
import datastructures.Stack;

/** Comp
 * @author Marimuthu
 *12:26:18 PM Jan 30, 2016
 */
public class BreadthFirstSearch {
	private boolean marked[];
	private int edgeTo[];
	private final int S;
	/**
	 * 
	 */
	public BreadthFirstSearch(UnDirectedGraph graph,int S) {
		// TODO Auto-generated constructor stub
		this.S=S;
		marked=new boolean[graph.V()];
		edgeTo=new int[graph.V()];
		BFS(graph,S);
	}
	private void BFS(UnDirectedGraph graph,int s){
		Queue<Integer> queue=new Queue<>();
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int v=queue.dequeue();
			for(int w:graph.adj(v)){
				if(!marked[w]){
					marked[w]=true;
					edgeTo[w]=v;
					queue.enqueue(w);
				}
			}
		}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> stack=new Stack<>();
		if(hasPathTo(v)){
			for(int x=v;x!=S;x=edgeTo[x]){
				stack.push(x);
			}stack.push(S);
		}
		return stack;
	}

}
