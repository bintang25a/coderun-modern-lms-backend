public class PerkalianMatrix {
    public static int[][] perkalianMatriks(int[][] A, int[][] B) throws Exception {
        // Validasi matriks tidak boleh kosong
        if (A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            throw new Exception("Matriks A atau B tidak boleh kosong!");
        }

        // Validasi dimensi matriks untuk perkalian
        if (A[0].length != B.length) {
            throw new Exception("Matriks A dan B tidak bisa dikalikan karena dimensi tidak cocok!");
        }

        // Deklarasi matriks hasil
        int[][] C = new int[A.length][B[0].length];

        // Perhitungan perkalian matriks
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                C[i][j] = 0; // Inisialisasi nilai awal
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C; // Mengembalikan matriks hasil
    }

    public static void cetakArray2D(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Deklarasi matriks A dan B
        int[][] A = {
            {4, 3, 1},
            {2, 6, 5}
        };

        int[][] B = {
            {2, 5, 4, 6},
            {8, 2, 7, 0},
            {7, 1, 3, 9}
        };

        try {
            // Melakukan perkalian matriks
            int[][] C = perkalianMatriks(A, B);

            // Menampilkan hasil perkalian matriks
            cetakArray2D(C);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}

