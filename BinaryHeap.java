public class BinaryHeap {

static class Heap {
 int arr[];
 int size;
 int capacity;
 Heap(){}
 Heap(int sz){
   this.capacity = sz;
   this.size = 0;
   arr = new int[capacity];
 }
};

private static int getParent(int i){
  return (i - 1)/2;
}

private static int getLeft(int i){
  return ((2 * i) + 1);
}

private static int getRight(int i){
  return ((2 * i) + 2);
}

private static void swap(int i, Heap h, int j){
  int tmp = h.arr[i];
  h.arr[i] = h.arr[j];
  h.arr[j] = tmp;
}

private static void insert(Heap h, int d){
  if(h.size == h.capacity){
    return;
  }
  h.size++;
  int i = h.size - 1;
  h.arr[i] = d;
  while(i > 0 && h.arr[i] < h.arr[getParent(i)]){
    swap(i, h, getParent(i));
    i = getParent(i);
  }
}

private static void decreaseKey(Heap h, int i, int d){
  if(h.size == h.capacity){
    return;
  }
  h.arr[i] = d;
  while(i > 0 && h.arr[i] < h.arr[getParent(i)]){
    swap(i, h, getParent(i));
    i = getParent(i);
  }
}

private static int extractMin(Heap h){
  if(h.size == 0){
    return Integer.MIN_VALUE;
  }
  if(h.size == 1){
    h.size = 0;
    return h.arr[0];
  }
  int min = h.arr[0];
  h.arr[0] = h.arr[h.size - 1];
  h.size--;
  minHeapify(h, 0);
  return min;
}

private static void minHeapify(Heap h, int i){
  int smallest = i;
  int left = getLeft(i);
  int right = getRight(i);
  if(left < h.size && h.arr[left] < h.arr[i]){
    smallest = left;
  }
  if(right < h.size && h.arr[right] < h.arr[smallest]){
    smallest = right;
  }

  if(smallest != i){
    swap(i, h, smallest);
    minHeapify(h, smallest);
  }
}

 public static void main(String args[]){
   Heap h = new Heap(4);
   insert(h, 1);
   insert(h, 3);
   insert(h, -1);
   insert(h, 5);
   int min = extractMin(h);
   min = extractMin(h);
   min = extractMin(h);
   min = extractMin(h);
   min = extractMin(h);
   System.out.print(min+" ");
 }

}
