import java.util.Arrays;

public class CountingSort {

    // Fungsi Counting Sort
    public static void countingSort(int[] array) {
        // Mencari nilai maksimum pada array
        int max = Arrays.stream(array).max().orElse(0);
        
        // Mencari nilai minimum pada array
        int min = Arrays.stream(array).min().orElse(0);
        
        // Rentang nilai
        int range = max - min + 1;

        // Array untuk menghitung frekuensi
        int[] count = new int[range];
        Arrays.fill(count, 0);

        // Array untuk hasil akhir
        int[] output = new int[array.length];

        // Menghitung jumlah kemunculan setiap elemen
        for (int num : array) {
            count[num - min]++;
        }

        // Mengubah count menjadi kumulatif
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Menempatkan elemen array asli ke posisi yang benar di array output
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        // Menyalin elemen dari array output ke array asli
        System.arraycopy(output, 0, array, 0, array.length);
    }

    // Fungsi utama untuk menjalankan program
    public static void main(String[] args) {
        // Array input
        int[] array = {4, 2, 2, 8, 3, 3, 1};

        // Menampilkan array sebelum diurutkan
        System.out.println("Array sebelum diurutkan: " + Arrays.toString(array));

        // Memanggil fungsi countingSort
        countingSort(array);

        // Menampilkan array setelah diurutkan
        System.out.println("Array setelah diurutkan: " + Arrays.toString(array));
    }
}