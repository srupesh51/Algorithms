public class BinaryHeap {

static class Heap {
 int arr[];
 int size;
 int capacity;
 int i;
 int j;
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

private static void mergeHeaps(int a[], int b[]){
  int result[] = new int[a.length + b.length];
  int i ;
  for(i = 0; i < a.length; i++){
    result[i] = a[i];
  }
  for(int i1 = i, j = 0; i1 < a.length + b.length; i1++, j++){
    result[i1] = b[j];
  }
  buildMaxHeap(result, result.length);

  for(int j = 0; j < result.length; j++){
    System.out.print(result[j]+" ");
  }
}
private static void maxHeapify(int arr[], int n, int i){
  int largest = i;
  int left = getLeft(i);
  int right = getRight(i);
  if(left < n && arr[left] > arr[i]){
    largest = left;
  }
  if(right < n && arr[right] > arr[largest]){
    largest = right;
  }

  if(largest != i){
    swap(i, arr, largest);
    maxHeapify(arr, n, largest);
  }
}

private static void swap(int i, int arr[], int j){
  int tmp = arr[i];
  arr[i] = arr[j];
  arr[j] = tmp;
}

private static void buildMaxHeap(int res[], int n){
  for(int i = (n - 1)/2; i>= 0 ; i--){
    maxHeapify(res, n, i);
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
   int A[] = {10,5,6,2};
   int B[] = {12,7,9};
   mergeHeaps(A, B);

 }

}
