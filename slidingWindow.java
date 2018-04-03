import java.util.*;
public class slidingWindow {


  private static void printMax(int arr[], int n,int k){
    Deque<Integer> q = new LinkedList<Integer>();
    int i = 0;
    while(i < k){
      while(!q.isEmpty() && arr[i] >= arr[q.peekLast()]){
        q.removeLast();
      }
      q.addLast(i);
      i++;
    }
    while(i < n){
      System.out.print(arr[q.peek()]+" ");
      while(!q.isEmpty() && q.peek() <= (i - k) ){
        q.removeFirst();
      }
      while(!q.isEmpty() && arr[i] >= arr[q.peekLast()]){
        q.removeLast();
      }
      q.addLast(i);
      i++;
    }
    System.out.print(arr[q.peek()]+" ");
  }



  public static void main(String args[]){
    int arr[]={12, 1, 78, 90, 57, 89, 56};
    int k=3;
    printMax(arr, arr.length,k);
  }
}
