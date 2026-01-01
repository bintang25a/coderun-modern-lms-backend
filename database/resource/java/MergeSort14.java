import java.util.Scanner;
public class MergeDinamis {

    public static void cetak (int[] A){
        System.out.print("A[]: ");
        for(int p=0; p<A.length; p++){
            System.out.print(A[p]+" ");
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
    
    public static void banyakarr(int n, int[] A){
        Scanner input = new Scanner(System.in);
        for(int l=0; l<n;l++){
            System.out.print("Data Sort "+ (l+1) +" = ");
            A[l] = input.nextInt();
        }
    }
    
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       
       System.out.println("Merge Sort - Dinamis :");
       System.out.print("Masukkan Banyak Elemen Array (A) : ");
       int n = input.nextInt();
       int[] A = new int[n];
       
       banyakarr(n, A);
       System.out.println("Array (A) masih Acak : ");
       cetak(A);
       
       mergeSort(A, 0, A.length - 1);
       
       System.out.println("Array (A) Sudah Terurut : ");
       cetak(A);
    }
}