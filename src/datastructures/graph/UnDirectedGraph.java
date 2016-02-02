/**
 * 
 */
package datastructures.graph;

import datastructures.Bag;

/** Comp
 * @author Marimuthu
 *11:10:23 PM Jan 22, 2016
 */
public class UnDirectedGraph {
	private final int V;
	private int E;
	private Bag<Integer> adj[];
	/**
	 * 
	 */
	public UnDirectedGraph(int V) {
		this.V=V;
		this.E=0;
		this.adj=(Bag<Integer>[])new Bag[V];
		for(int i=0;i<V;i++)
			adj[i]=new Bag<Integer>();
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	public Iterable<Integer> adj(int i){
		validateVertex(i);
		return adj[i];
	}
	
	public void addEdge(int v,int w){
		validateVertex(v);
		validateVertex(w);
		adj[v].put(w);
		adj[w].put(v);
		E++;
	}
	private void validateVertex(int v){
		if(v<0||v>=V) throw new IndexOutOfBoundsException("Vertex "+v+" is not between 0 and "+(V-1));
	}
	
	public String toString(){
		StringBuilder string=new StringBuilder();
		string.append(V+" Vertices "+E+" Edges"+System.getProperty("line.separator"));
		for(int v=0;v<V;v++){
			string.append(v+":");
			for(int w:adj[v]){
				string.append(w+" ");
			}string.append(System.getProperty("line.separator"));
		}return string.toString();
	}
	
	public int degree(int v){
		return adj[v].size();
	}
	public static void main(String[] args){
		UnDirectedGraph graph=new UnDirectedGraph(10);
		graph.addEdge(3, 2);
		graph.addEdge(3, 5);
		graph.addEdge(3, 9);
		graph.addEdge(4, 0);
		graph.addEdge(0, 2);
		System.out.println(graph);
		DepthFirstSearch dfs=new DepthFirstSearch(graph, 0);
		BreadthFirstSearch bfs=new BreadthFirstSearch(graph,0);
		System.out.println("DFS");
		int i=0;
		System.out.println(dfs.hasPathTo(i));
		for(int v:dfs.pathTo(i))
			System.out.print(v+" ");
		
		System.out.println("\nBFS");
		for(int v:bfs.pathTo(i))
			System.out.print(v+" ");
		System.out.println();
	}

}
