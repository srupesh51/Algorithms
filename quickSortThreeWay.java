public class quickSortThreeWay {

    private static void quickSort(int arr[], int l, int h){
        if(l < h){
            int pivot = partition(arr,l,h);
            quickSort(arr,l,pivot-1);
            quickSort(arr,pivot+1,h);
        }
    }

    private static int partition(int arr[], int l,int h){
        int i = l;
        int j = h;
        while(i < j){
            if(arr[i] > arr[j]){
                swap(arr,i,j);
            }
            i++;
        }
        return j;
    }

    private static void swap(int arr[], int l,int h){
        int tmp = arr[l];
        arr[l] = arr[h];
        arr[h] = tmp;
    }

    public static void main(String args[]) {
        int arr[] = {4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4};
        quickSort(arr,0,arr.length-1);
        for(int i = 0; i < arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
