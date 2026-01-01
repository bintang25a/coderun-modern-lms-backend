import java.util.Scanner;

public class msdinamis {

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; 
        }

        int mid = array.length / 2; 
        int[] left = new int[mid]; 
        int[] right = new int[array.length - mid]; 

        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        
        mergeSort(left);
        mergeSort(right);
        
        merge(array, left, right);
    }
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Meminta pengguna untuk memasukkan jumlah elemen
    System.out.print("Masukkan jumlah elemen array: ");
    int n = scanner.nextInt();

    // Membuat array dengan ukuran n
    int[] array = new int[n];

    // Memasukkan elemen ke dalam array
    System.out.println("Masukkan " + n + " elemen:");
    for (int i = 0; i < n; i++) {
        System.out.print("Elemen ke-" + (i + 1) + ": ");
        array[i] = scanner.nextInt();
    }

    // Menampilkan array sebelum diurutkan
    System.out.println("Array sebelum diurutkan:");
    for (int i = 0; i < n; i++) {
        System.out.print(array[i] + " ");
    }

    // Memanggil fungsi mergeSort
    mergeSort(array);

    // Menampilkan array setelah diurutkan
    System.out.println("\n\nArray setelah diurutkan:");
    for (int i = 0; i < n; i++) {
        System.out.print(array[i] + " ");
    }
}
}