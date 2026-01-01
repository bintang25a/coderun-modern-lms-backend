package perkalianmatriks;

/**
 * Program untuk menghitung perkalian matriks.
 * @author Afrizal_al
 */
public class PerkalianMatriks {

    /**
     * Fungsi untuk melakukan perkalian dua matriks.
     * @param A matriks pertama
     * @param B matriks kedua
     * @return hasil perkalian matriks
     * @throws Exception jika dimensi matriks tidak cocok atau kosong
     */
    public static int[][] PerkalianMatriks(int[][] A, int[][] B) throws Exception {
        if (A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            throw new Exception("Matriks A atau B Tidak Boleh Kosong");
        } else if (A[0].length != B.length) {
            throw new Exception("A dan B Tidak Bisa Dikalikan Karena Dimensi Tidak Cocok");
        }

        int[][] C = new int[A.length][B[0].length]; // Deklarasi matriks baru untuk hasil perkalian

        // Menghitung perkalian matriks
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) { // Perbaikan indeks dari B[i] ke B[0]
                C[i][j] = 0;
                for (int k = 0; k < A[0].length; k++) { // Perbaikan indeks dari A[i] ke A[0]
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C; // Mengembalikan matriks hasil perkalian
    }

    /**
     * Fungsi untuk mencetak matriks 2D.
     * @param A matriks yang akan dicetak
     */
    public static void cetakArray2D(int[][] A) {
        for (int i = 0; i < A.length; i++) { // Baris
            for (int j = 0; j < A[i].length; j++) { // Kolom
                System.out.print(A[i][j] + " "); // Perbaikan dari println ke print untuk tampilan horizontal
            }
            System.out.println(); // Baris baru setelah mencetak satu baris matriks
        }
    }
    /**
     * Fungsi utama untuk menjalankan program perkalian matriks.
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        int[][] A = {{4, 3, 1}, {2, 6, 5}};
        int[][] B = {{2, 5, 4, 6}, {8, 2, 7, 0}, {7, 1, 3, 9}};

        System.out.println("Matriks A:");
        cetakArray2D(A);
        System.out.println();

        System.out.println("Matriks B:");
        cetakArray2D(B);
        System.out.println();

        try {
            int[][] C = PerkalianMatriks(A, B);
            System.out.println("Hasil Perkalian Matriks A dan B:");
            cetakArray2D(C);
        } catch (Exception ex) {
            System.err.println(ex.getMessage()); // Perbaikan dari toString ke getMessage untuk pesan lebih jelas
        }
    }
}
