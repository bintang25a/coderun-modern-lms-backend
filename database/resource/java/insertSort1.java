import java.util.Arrays; 
import java.util.Scanner;

public class TugasPertemuan1 {

    public static void insertion(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) { 
            int j = i - 1;
            int key = testArray[i]; 

            /* untuk mengubah dari urutan ascending menjadi descending hanya mengganti
            simbol ">" di samping variabel key jadi "<"
            */
            while (j >= 0 && testArray[j] < key) {
                testArray[j + 1] = testArray[j]; 
                j--;
            }
            testArray[j + 1] = key;
            
            System.out.println("Langkah " + i + " (Menyisipkan " + key + "): " + Arrays.toString(testArray));
        }
    }
    public static void main(String a[]) {
        Scanner input = new Scanner(System.in);
        
        // untuk menginput banyaknya elemen array
        System.out.print("Masukkan banyak angka : ");
        int n = input.nextInt();
        int arr[] = new int[n];
        
        // perulangan untuk menginput angka per elemen array
        for (int i = 0; i<n; i++) {
            int urutan = i+1;
            System.out.print("Masukkan data ke - " + urutan + ": ");
            arr[i] = input.nextInt();
        }
        
        System.out.println("Sebelum Sorting : ");
        System.out.println(Arrays.toString(arr));
        System.out.println("\n--- Proses Sorting ---");

        insertion(arr);
        
        System.out.println("\n--- Hasil Sorting ---");
        System.out.println("Sesudah Sorting : \n" + Arrays.toString(arr));
    }
}
