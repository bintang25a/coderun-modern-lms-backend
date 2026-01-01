import java.util.Arrays;

public class CountingSort {

    // Metode untuk menemukan elemen terbesar dalam array
    static int findGreatestElement(int[] myArray) {
        int maxVal = Integer.MIN_VALUE;
        for (int val : myArray) {
            if (maxVal < val) {
                maxVal = val;
            }
        }
        return maxVal;
    }

    // Metode untuk melakukan counting sort
    static int[] countingSort(int[] A) {
        int n = A.length;
        int k = findGreatestElement(A);  // Temukan elemen terbesar
        int[] B = new int[n];            // Array untuk hasil
        int[] C = new int[k + 1];        // Array untuk menghitung frekuensi

        // Langkah 1: Inisialisasi C dengan 0
        System.out.println("Jumlah kolom yang dibuat = " + (k + 1));
        System.out.println("C = " + Arrays.toString(C));

        // Langkah 2: Hitung frekuensi setiap elemen dalam array A
        for (int j = 0; j < n; j++) {
            C[A[j]]++;
            System.out.println("C = " + Arrays.toString(C));
        }

        // Langkah 3: Update C sehingga C[i] berisi posisi elemen yang sesuai
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i - 1];
            System.out.println("C = " + Arrays.toString(C));
        }

        // Langkah 4: Bangun array B berdasarkan array C
        for (int j = n - 1; j >= 0; j--) {
            int element = A[j];
            int indexInB = C[element] - 1;  // Hitung indeks untuk elemen A[j] pada array B
            B[indexInB] = element;
            C[element]--;  // Decrement posisi yang tersedia untuk elemen tersebut

            // Output per langkah
            System.out.println ();
            System.out.println("A = " + Arrays.toString(A) + " <---" + element);
            System.out.println("C = " + Arrays.toString(C));
            System.out.println("Indeks = " + (C[element]) + " - 1 = " + indexInB);
            System.out.println("B = " + Arrays.toString(B));
        }

        return B;
    }

    public static void main(String[] args) {
        // Array yang akan diurutkan
        int[] data = {6, 4, 2, 1, 8};

        // Tampilkan array sebelum sorting
        System.out.println("Array acak = " + Arrays.toString(data) + "\n");

        // Proses counting sort dan tampilkan hasilnya
        int[] sortedData = countingSort(data);
        System.out.println("\nArray terurut = ");
        System.out.println(Arrays.toString(sortedData));
    }
}
