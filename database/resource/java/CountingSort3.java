import java.util.Arrays;

public class CountingSortdinamis {
    public static void main(String[] args) {
        // Array input
        int[] A = {6, 4, 1, 2, 0};
        System.out.println("Array acak = " + Arrays.toString(A));

        // Langkah 1: Cari nilai maksimum
        int max = Arrays.stream(A).max().getAsInt();

        // Inisialisasi counting array (C) dan array hasil (B)
        int[] C = new int[max + 1];
        int[] B = new int[A.length];

        // Langkah 2: Hitung frekuensi elemen dalam array A
        System.out.println("\nIterasi counting array (Langkah 1):");
        for (int i = 0; i < A.length; i++) {
            C[A[i]]++;
            System.out.printf("A[%d] = %d --> C = %s%n", i, A[i], Arrays.toString(C));
        }

        // Langkah 3: Kalkulasi prefix sum pada counting array
        System.out.println("\nPrefix sum pada counting array (Langkah 2):");
        for (int i = 1; i < C.length; i++) {
            C[i] += C[i - 1];
        }
        System.out.println("C setelah prefix sum = " + Arrays.toString(C));

        // Langkah 4: Iterasi mundur untuk membangun array hasil
        System.out.println("\nIterasi membangun array hasil (Langkah 3):");
        for (int i = A.length - 1; i >= 0; i--) {
            int index = C[A[i]] - 1;  // Posisi elemen di array hasil
            B[index] = A[i];
            System.out.printf("A[%d] = %d --> index = %d --> B = %s%n", i, A[i], index, Arrays.toString(B));
            C[A[i]]--;  // Kurangi count pada C
            System.out.println("C = " + Arrays.toString(C));
        }

        // Output array hasil
        System.out.println("\nArray terurut = " + Arrays.toString(B));
    }
}
