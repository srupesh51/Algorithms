import java.util.*;
public class quickSelect {

  private static int median(int arr[], int l, int n){
      Arrays.sort(arr,l,n);
      int mid = (l)+(n - l)/2;
      return arr[mid];
  }

  private static int nthSmallest(int arr[], int l, int r,int k){
    if(k > 0 && k <= (r - l + 1)){
      int n = (r - l + 1);
      int median[] = new int[(n+4)/5];
      int i;
      for(i = 0; i < (n/5); i++){
        median[i] = median(arr, l+(i*5), l+(i*5)+5);
      }
      if(i * 5 < n){
        median[i] = median(arr,l+(i*5), l+(i*5)+n%5);
        i++;
      }
      int medOfMedian = (i == 1)? median[i-1] : nthSmallest(median,0,i-1,i/2);
      int pos = partition(arr,l,r,medOfMedian);
      if (pos-l == k-1)
            return arr[pos];
      if (pos-l > k-1)
            return nthSmallest(arr, l, pos-1, k);
        return nthSmallest(arr, pos+1, r, k-pos+l-1);
    }
    return Integer.MAX_VALUE;
  }

  private static void swap(int arr[], int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static int partition(int arr[], int l, int r, int x){
    int i;
    for (i=l; i<r; i++)
        if (arr[i] == x)
           break;
    swap(arr, i, r);

    i = l;
    for (int j = l; j <= r - 1; j++)
    {
        if (arr[j] <= x)
        {
            swap(arr,i, j);
            i++;
        }
    }
    swap(arr,i, r);
    return i;
  }
  public static void main(String args[]) {
    int arr[] = {12, 3, 5, 7, 4, 19, 26};
    for(int i = 0; i < arr.length; i++){
      int nthSmallest = nthSmallest(arr,0,arr.length-1,i+1);
      System.out.print(nthSmallest+" ");
    }
  }
}
