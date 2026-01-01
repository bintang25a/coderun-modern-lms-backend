import java.util.Scanner;

public class MergingSort {

    public static void cetak(int[] Array) {
        for (int i = 0; i < Array.length; i++) {
            System.out.print(Array[i] + " ");
        }
        System.out.println();
    }

    public static void mergeSort(int[] A, int down, int up){
        if (down < up){
            int middle = (down + up)/2;
            mergeSort(A, down, middle);
            mergeSort(A, middle + 1, up);
            merge(A, down, middle, up);
        }   
    }

    public static void banyakarr(int n, int[] Array) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Data Sort ke-" + (i + 1) + " = ");
            Array[i] = input.nextInt();
        }
    }

    public static void merge(int[] A, int down, int middle, int up){
        int part1 = middle - down + 1;
        int part2 = up - middle;
        
        int[] d = new int[part1];
        int[] u = new int[part2];
        
        for(int i=0; i<part1; i++){
            d[i] = A[down + i];
        }
        for(int j=0; j<part2; j++){
            u[j] = A[middle + 1 + j];
        }
        
        int i=0, j=0;
        int k = down;
        while(i < part1 && j < part2){
            if(d[i] <= u[j]){
                A[k] = d[i];    i++;
            } else {
                A[k] = u[j];    j++;
            }
            k++;
        }
        
        while(i < part1){
            A[k] = d[i];    i++;    k++;
        }
        while(j < part2){
            A[k] = u[j];    j++;    k++;
        }
    }

    public static void main(String[] args) {
        System.out.println("MergeSort - Statis :");
        int[] A = {6, 4, 1, -3, 8};
        
        System.out.print("Array acak A[] = ");
        cetak(A);
        
        mergeSort(A, 0, A.length - 1);
        
        System.out.print("Array urut A[] = ");
        cetak(A);
    }
}
