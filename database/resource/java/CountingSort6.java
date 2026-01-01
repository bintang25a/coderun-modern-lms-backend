import java.util.Arrays;

public class countingSort {
    public static void main(String[] args) {
        int[] array = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Array sebelum diurutkan: " + Arrays.toString(array));
        
        countingSort(array);
        
        System.out.println("Array setelah diurutkan: " + Arrays.toString(array));
    }

    public static void countingSort(int[] array) {
        // Mencari nilai maksimum dalam array
        int max = Arrays.stream(array).max().getAsInt();
        
        // Membuat array counting
        int[] count = new int[max + 1];
        
        // Menghitung frekuensi setiap elemen
        System.out.println("Menghitung frekuensi setiap elemen:");
        for (int num : array) {
            count[num]++;
            System.out.println("Elemen: " + num + ", Count: " + Arrays.toString(count));
        }
        
        // Menampilkan array count setelah penghitungan
        System.out.println("Array frekuensi (count) setelah penghitungan: " + Arrays.toString(count));
        
        // Mengubah array count menjadi array posisi
        System.out.println("Mengubah array count menjadi posisi:");
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
            System.out.println("Count[" + i + "] = " + count[i]);
        }
        
        // Menampilkan array count setelah pengubahan posisi
        System.out.println("Array posisi (count) setelah pengubahan: " + Arrays.toString(count));
        
        // Array output untuk menyimpan hasil pengurutan
        int[] output = new int[array.length];
        
        // Membangun array output
        System.out.println("Membangun array output:");
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
            System.out.println("Menempatkan " + array[i] + " pada output[" + (count[array[i]]) + "]");
        }
        
        // Menyalin array output ke array asli
        System.arraycopy(output, 0, array, 0, array.length);
    }
}