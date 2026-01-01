import java.util.Arrays;
import java.util.Scanner;

public class TugasQuicksortLomuton {
    public static void swap(int[] A, int p, int r) {
        int temp = A[p];
        A[p] = A[r];
        A[r] = temp;
    }

    public static int lomutoPartition(int[] A, int p, int r) {
        int pivot = A[r];  
        int i = p - 1;  
        
        for (int j = p; j < r; j++) {
            if (A[j] < pivot) {
                i++;
                swap(A, i, j);
                System.out.println(Arrays.toString(A) + " <--- " + A[i] + " swap " + A[j]);
            }
        }
        
      
        swap(A, i + 1, r);
        System.out.println(Arrays.toString(A) + " <--- " + A[i + 1] + " swap " + A[r]);
        
        return i + 1;  
    }

  
    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int pivot = lomutoPartition(A, p, r);  
            quicksort(A, p, pivot - 1);  // Sorting bagian kiri
            quicksort(A, pivot + 1, r);  // Sorting bagian kanan
        }
    }

   
    public static int[] getUserInput() {
        Scanner input = new Scanner(System.in);

        System.out.print("Tentukan banyak data = ");
        int batas = input.nextInt();

        int[] data = new int[batas];
        for (int i = 0; i < batas; i++) {
            System.out.print("Masukkan data ke-" + i + " = ");
            data[i] = input.nextInt();
        }

        return data;
    }

    public static void main(String[] args) {
        int[] data = getUserInput();
        
        System.out.println("Data yang diterima: " + Arrays.toString(data)); 
        System.out.println("Data Sebelum Disorting: " + Arrays.toString(data) + "\n");
        quicksort(data, 0, data.length - 1);
        System.out.println("\nData Setelah Disorting: " + Arrays.toString(data));
    }
}