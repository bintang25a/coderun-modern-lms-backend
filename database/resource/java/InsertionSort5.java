import java.util.Scanner;

public class insertionshortDinamis {
    public static void insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Memindahkan elemen yang lebih besar dari key ke satu posisi di depan dari posisi saat ini
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
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

    // Memanggil fungsi insertionSort
    insertionSort(array);

    // Menampilkan array yang telah diurutkan
    System.out.println("\n\nArray setelah diurutkan:");
    for (int i = 0; i < n; i++) {
        System.out.print(array[i] + " ");
    }
}
}

    
