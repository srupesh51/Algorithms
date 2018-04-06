import java.util.*;
class weightedGraph {
    int v;
    int weight;
    weightedGraph(){}
    weightedGraph(int v,int w){
      this.v = v;
      this.weight = w;
    }
}
class GraphNode {
  int v;
  LinkedList<weightedGraph> adj[];
  GraphNode(){}
  GraphNode(int v){
    this.v = v;
    adj = new LinkedList[v];
    for(int i = 0; i < v; i++){
      adj[i] = null;
    }
  }
  void addEdge(int u, int v, int w) {
    if(adj[u] == null){
      adj[u] = new LinkedList<weightedGraph>();
    }
    if(adj[v] == null){
      adj[v] = new LinkedList<weightedGraph>();
    }
    adj[u].add(new weightedGraph(v, w));
    adj[v].add(new weightedGraph(u, w));
  }
  void dijkstra(){
    int dist[] = new int[this.v];
    dist[0] = 0;
    minHeap h = new minHeap(this.v);
    h.createHeap(0,0);
    h.count++;
    for(int i = 1; i < this.v ; i++){
      dist[i] = Integer.MAX_VALUE;
      h.createHeap(i,dist[i]);
      h.count++;
    }
    while(!h.isEmpty()){
      minHeapNode h1 = h.deleteMin();
      int u = h1.v;
      Iterator it = adj[u].listIterator();
      while(it.hasNext()){
        weightedGraph weight = (weightedGraph)it.next();
        int v = (int)weight.v;
        int wt = (int)weight.weight;
        if(dist[u] != Integer.MAX_VALUE && dist[u] + wt  < dist[v]){
          dist[v] = dist[u] + wt;
          h.decreaseKey(v, dist[v]);
        }
      }
    }
    for(int i = 0; i < this.v; i++){
      System.out.print(i+" "+dist[i]+" ");
    }
    System.out.println();
  }
};
class minHeapNode {
  int v;
  int dist;
  minHeapNode(){}
  minHeapNode(int v, int d){
    this.v = v;
    this.dist = d;
  }
}
class minHeap {
  int n;
  int count;
  minHeapNode heap[];
  minHeap(){}
  minHeap(int n){
    this.n = n;
    this.count = 0;
    heap = new minHeapNode[n];
    for(int i = 0; i < n; i++){
      heap[i] = new minHeapNode();
    }
  }
  void createHeap(int v, int dist){
    if(this.count == this.n){
      return;
    }
    heap[v] = new minHeapNode(v, dist);
  }
  boolean isEmpty(){
    return this.count == 0;
  }
  void swap(minHeapNode heap[], int i, int j){
    minHeapNode tmp = heap[i];
    heap[i] = heap[j];
    heap[j] = tmp;
  }
  minHeapNode deleteMin(){
    if(this.count == 1){
      this.count--;
      return heap[0];
    }
    if(this.count == 0){
      return null;
    }
    minHeapNode root = heap[0];
    heap[0] = heap[this.count - 1];
    this.count--;
    minHeapify(heap, 0);
    return root;
  }
  void decreaseKey(int i, int d){
    heap[i].dist = d;
    heap[i].v = i;
    while(i > 0 && heap[(i - 1)/2].dist > heap[(i)/2].dist){
      swap(heap,i,(i - 1)/2);
      i = (i - 1)/2;
    }
  }
  void minHeapify(minHeapNode heap[], int i){
      int smallest = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if(left < n && heap[left].dist < heap[smallest].dist){
        smallest = left;
      }
      if(right < n && heap[right].dist < heap[smallest].dist){
        smallest = right;
      }
      if(smallest != i){
        swap(heap, i, smallest);
        minHeapify(heap, smallest);
      }
  }
}
public class Dijkstra {

  public static void main(String args[]){
    GraphNode g = new GraphNode(9);
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
    g.dijkstra();
  }

}
