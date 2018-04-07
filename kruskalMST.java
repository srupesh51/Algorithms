import java.util.*;
class subsets {
  int parent;
  int rank;
}
class Edge implements Comparable<Edge>{
  int src;
  int dest;
  int weight;
  Edge[] e;
  int V;
  int E;
  int index;
  Edge(){}
  Edge(int V, int E){
    this.index = 0;
    this.V = V;
    this.E = E;
    e = new Edge[E];
  }
  Edge(int u, int v, int w){
    this.src = u;
    this.dest = v;
    this.weight = w;
  }
  void addEdge(int u, int v, int w) {
    e[index++] = new Edge(u, v, w);
  }
  public int compareTo(Edge weightCmp){
    return this.weight - weightCmp.weight;
  }
  public int find(subsets[] s, int i){
    if(s[i].parent != i){
      s[i].parent = find(s,s[i].parent);
    }
    return s[i].parent;
  }
  public void Union(subsets[] s, int x, int y){
    int xroot = find(s, x);
    int yroot = find(s, y);
    if(s[xroot].rank < s[yroot].rank){
      s[xroot].parent = yroot;
    } else if(s[xroot].rank > s[yroot].rank){
      s[yroot].parent = xroot;
    } else {
      s[yroot].parent = xroot;
      s[xroot].rank++;
    }
  }
  void MST(){
    Edge result[] = new Edge[this.V];
    for(int i = 0; i < this.V; i++){
      result[i] = new Edge();
    }
    Arrays.sort(e);
    subsets s[] = new subsets[this.V];
    for(int i = 0; i < this.V; i++){
      s[i] = new subsets();
      s[i].parent = i;
      s[i].rank = 0;
    }
    int j = 0;
    int i = 0;
    while(j < (V - 1)){
      Edge nextEdge = new Edge();
      nextEdge = e[i++];
      int x = find(s, nextEdge.src);
      int y = find(s, nextEdge.dest);
      if(x != y){
        result[j++] = nextEdge;
        Union(s,x,y);
      }
    }
    for(int k = 0; k < j; k++){
      System.out.print(result[k].src+" "+result[k].dest+" "+
      result[k].weight+" ");
    }
    System.out.println();
  }
}
public class kruskalMST {

  public static void main(String args[]){
    Edge g = new Edge(9,14);
    g.addEdge(0, 1, 4);
    g.addEdge(0, 7, 8);
    g.addEdge(1, 2, 8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 3, 7);
    g.addEdge(2, 8, 2);
    g.addEdge(2, 5, 4);
    g.addEdge(3, 4, 9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6, 2);
    g.addEdge(6, 7, 1);
    g.addEdge(6, 8, 6);
    g.addEdge(7, 8, 7);
    g.MST();
  }

}
