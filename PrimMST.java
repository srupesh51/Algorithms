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
  void MST(){
    int dist[] = new int[this.v];
    dist[0] = 0;
    minHeap h = new minHeap(this.v);
    h.pos[0] = 0;
    h.heap[0] = new minHeapNode(0, dist[0]);
    h.count++;
    for(int i = 1; i < this.v ; i++){
      dist[i] = Integer.MAX_VALUE;
      h.heap[i] = new minHeapNode(i, dist[i]);
      h.pos[i] = i;
      h.count++;
    }
    while(!isEmpty(h)){
      minHeapNode h1 = deleteMin(h);
      int u = h1.v;
      Iterator it = adj[u].listIterator();
      while(it.hasNext()){
        weightedGraph weight = (weightedGraph)it.next();
        int v = (int)weight.v;
        int wt = (int)weight.weight;
        if(isInMinHeap(h,v) && wt < dist[v]){
          dist[v] = wt;
          decreaseKey(h, v, dist[v]);
        }
      }
    }
    for(int i = 0; i < this.v; i++){
      System.out.print(i+" "+dist[i]+" ");
    }
    System.out.println();
  }

  boolean isEmpty(minHeap h){
    return h.count == 0;
  }

  boolean isInMinHeap(minHeap h, int v)
  {
     if (h.pos[v] < h.count)
       return true;
     return false;
  }

  minHeapNode deleteMin(minHeap h){
    if (isEmpty(h))
        return null;

    minHeapNode root = h.heap[0];

    minHeapNode lastNode = h.heap[h.count - 1];
    h.heap[0] = lastNode;

    h.pos[root.v] = h.count-1;
    h.pos[lastNode.v] = 0;

    h.count--;
    minHeapify(h, 0);

    return root;
  }
  void decreaseKey(minHeap h, int v, int d){
    int i = h.pos[v];
    h.heap[i].dist = d;
    while(i > 0 && h.heap[i].dist < h.heap[(i - 1)/2].dist){
      h.pos[h.heap[i].v] = (i-1)/2;
      h.pos[h.heap[(i-1)/2].v] = i;
      //swap(heap,i,(i - 1)/2);
      minHeapNode t = h.heap[i];
      h.heap[i] = h.heap[(i - 1)/2];
      h.heap[(i - 1)/2] = t;
      i = (i - 1)/2;
    }
  }
  void minHeapify(minHeap h, int i){
      int smallest = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if(left < h.count && h.heap[left].dist < h.heap[smallest].dist){
        smallest = left;
      }
      if(right < h.count && h.heap[right].dist < h.heap[smallest].dist){
        smallest = right;
      }
      if(smallest != i){
        minHeapNode  smallestNode = h.heap[smallest];
        minHeapNode  idxNode = h.heap[i];

        h.pos[smallestNode.v] = i;
        h.pos[idxNode.v] = smallest;

        minHeapNode t = h.heap[smallest];
        h.heap[smallest] = h.heap[i];
        h.heap[i] = t;
        minHeapify(h, smallest);
      }
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
  int pos[];
  minHeapNode heap[];
  minHeap(){}
  minHeap(int n){
    this.n = n;
    this.pos = new int[this.n];
    this.count = 0;
    heap = new minHeapNode[n];
    for(int i = 0; i < n; i++){
      heap[i] = new minHeapNode();
    }
  }
}
public class PrimMST {

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
    g.MST();
  }

}
