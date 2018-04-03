public class kStacks {

  int arr[];
  int top[];
  int next[];
  int n,k;
  int free;
  kStacks(){}
  kStacks(int n1, int k1){
    n = n1;
    k = k1;
    arr = new int[n];
    top = new int[k];
    next = new int[n];
    free = 0;
    for(int i = 0; i < k;i++){
      top[i] = -1;
    }
    for(int i = 0; i < n-1;i++){
      next[i] = i+1;
    }
    next[n - 1] = -1;
  }

  boolean isFull(){
    return free == -1;
  }

  boolean isEmpty(int sn){
    return top[sn] == -1;
  }

  void push(int sn, int element){
    if(isFull()){
      return;
    }
    int i = free;
    free = next[i];
    next[i] = top[sn];
    top[sn] = i;
    arr[i] = element;
  }

  int pop(int sn){
    if(isEmpty(sn)){
      return Integer.MAX_VALUE;
    }
    int i = top[sn];
    top[sn] = next[i];
    next[i] = free;
    free = i;
    return arr[i];
  }

  public static void main(String args[]){
    kStacks k = new kStacks(6, 3);
    k.push(0, 12);
    k.push(0, 13);
    k.push(0, 14);
    System.out.print(k.pop(0)+" ");
  }

}
