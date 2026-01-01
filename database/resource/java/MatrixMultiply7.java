public class Matriks {
    public static void main(String[] args) {
        // Membuat dua array dua dimensi (matriks)
        int[][] matriksA = {
            {1, 2, 3},
            {4, 5, 6}
        };          

        int[][] matriksB = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        // Mengalikan matriks A dan B
        int[][] hasil = perkalianMatriks(matriksA, matriksB);

        // Menampilkan hasil
        System.out.println("Hasil Perkalian Matriks A dan B:");
        tampilkanMatriks(hasil);
    }

    // Metode untuk mengalikan dua matriks
    public static int[][] perkalianMatriks(int[][] a, int[][] b) {
        int barisA = a.length;
        int kolomA = a[0].length;
        int barisB = b.length;
        int kolomB = b[0].length;

        // Memastikan matriks dapat dikalikan
        if (kolomA != barisB) {
            throw new IllegalArgumentException("Jumlah kolom matriks A harus sama dengan jumlah baris matriks B.");
        }

        // Matriks hasil perkalian
        int[][] hasil = new int[barisA][kolomB];

        // Melakukan perkalian matriks
        for (int i = 0; i < barisA; i++) {
            for (int j = 0; j < kolomB; j++) {
                for (int k = 0; k < kolomA; k++) {
                    hasil[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return hasil;
    }

    // Metode untuk menampilkan matriks
    public static void tampilkanMatriks(int[][] matriks) {
        for (int[] baris : matriks) {
            for (int elemen : baris) {
                System.out.print(elemen + " ");
            }
            System.out.println();
        }
    }
}