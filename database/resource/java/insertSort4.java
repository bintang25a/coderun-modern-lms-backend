import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void insertion(int[] testArray) {
        for (int i = 1; i < testArray.length; i++) {
            int j = i - 1;
            int key = testArray[i];

            while (j >= 0 && testArray[j] < key) {
                testArray[j + 1] = testArray[j];
                j--;
            }
            testArray[j + 1] = key;

            System.out.println("Langkah " + i + " (Menyisipkan " + key + "): " + Arrays.toString(testArray));
        }
    }
    public static void main(String a[]){
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Jumlah Array Disini : ");
        int jumlah = input.nextInt();

        int[] array = new int[jumlah];

        for(int i = 0; i < jumlah; i++){
            System.out.printf("Masukkan array ke %d disini : ",i+1);
            array[i] = input.nextInt();
        }
        int panjang_array = array.length;
        System.out.println("\nArray Sebelum Disorting");
        for(int i = 0; i < panjang_array; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println("\n--- Proses Sorting ---");
        insertion(array);
        System.out.println("\n--- Hasil Sorting ---");
        System.out.println("Sesudah Sorting : \n" + Arrays.toString(array));
    }
}
