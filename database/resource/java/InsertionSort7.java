import java.util.Scanner;
public class Insertsort {

    public static void cetak (int[] Array){
        for(int i=0; i<Array.length; i++){
            System.out.print(Array[i]+" ");
        }
        System.out.println();
    }
    
    public static void sorting(int[] A){  //insertion sort
        for(int i=1; i<A.length;i++){
            int j = i-1;
            int key = A[i];
            
            while (j>=0 && A[j] > key){
                A[j+1]=A[j];
                j--;
            }
            A[j+1] = key;
        }
    }
        
    public static void banyakarr(int n, int[] Array){
        Scanner input = new Scanner(System.in);
        for(int i=0; i<n;i++){
            System.out.print("Data Sort "+ (i+1) +" = ");
            Array[i] = input.nextInt();
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Insertion Sort - Dinamis: ");
        System.out.print("Masukkan Banyak Elemen Array : ");
        int n = input.nextInt();
        int[] Array = new int[n];
        
        banyakarr(n, Array);
        System.out.print("\nArray acak = ");
        cetak(Array);
        
        sorting(Array);
        
        System.out.print("Array urut = ");
        cetak(Array);
    }
}