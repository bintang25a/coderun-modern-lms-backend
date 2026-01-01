import java.util.Scanner;

public class InsertionSort {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen: ");
        int n = input.nextInt();

        int[] data = new int[n];
        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            data[i] = input.nextInt();
        }

        System.out.println("\nData sebelum diurutkan:");
        cetakArray(data);

        sortir(data);

        System.out.println("\nData setelah diurutkan:");
        cetakArray(data);
        
        input.close();
    }

    public static void sortir(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
            
            System.out.print("Iterasi ke-" + i + ": ");
            cetakArray(array);
        }
    }

    public static void cetakArray(int[] array) {
        for (int nilai : array) {
            System.out.print(nilai + " ");
        }
        System.out.println();
    }
}