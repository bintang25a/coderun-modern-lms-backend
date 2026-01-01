import java.util.Scanner;

public class InsertionDinamis {

    // Method to print array
    public static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    // Insertion sort method
    public static void Insertionsort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];  // Set key to the current element
            int j = i - 1;
            
            // Shift elements of A[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            
            // Place the key after the last shifted element
            A[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Asking for the size of the array
        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();
        
        // Initialize the array with the specified size
        int[] myArray = new int[n];
        
        // Asking the user to input the elements of the array
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }
        
        System.out.print("Array acak = ");
        cetakArray(myArray);  // Print the unsorted array
        
        Insertionsort(myArray);  // Perform insertion sort
        
        System.out.print("Array urut = ");
        cetakArray(myArray);  // Print the sorted array
        
        scanner.close();  // Close the scanner
    }
}
