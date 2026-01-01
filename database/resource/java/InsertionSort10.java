import java.util.Scanner;

public class InsertionSortDinamis1 {
    
    public static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();        
    }
    
    public static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int j = i - 1;
            int key = A[i];
            
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();
        
        int[] myArray = new int[n];
        
        System.out.println("Masukkan " + n + " elemen array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            myArray[i] = scanner.nextInt();
        }

        System.out.println("\nArray acak = ");
        cetakArray(myArray);
        
        insertionSort(myArray);
        
        System.out.println("\nArray terurut = ");
        cetakArray(myArray);

        scanner.close();
    }
}

