public class countingSort {

    private static void countingSort(int arr[], int n){
        int c[] = new int[256];
        for(int i = 0; i < n; i++){
            c[arr[i]]++;
        }
        for(int i = 1; i < 256; i++){
            c[i] += c[i - 1];
        }
        int i = 0;
        int output[] = new int[n];
        while(i < n){
            output[c[arr[i]] - 1] = arr[i];
            --c[arr[i]];
            i++;
        }
        i = 0;
        while(i < n){
            arr[i] = output[i];
            i++;
        }
    }

    public static void main(String args[]) {
        int arr[] = {1,4,1,2,7,5,2};
        countingSort(arr,arr.length);
        for(int i = 0; i < arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
