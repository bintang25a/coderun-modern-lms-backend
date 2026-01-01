public class Array2D_KodePerkalian_Array {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
public class MatrixMultiplication 
    public static void main(String[] args) {
        // Mendefinisikan dua array 2 dimensi
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        // Menghitung hasil perkalian
        int[][] result = multiplyMatrices(matrixA, matrixB);

        // Menampilkan hasil
        System.out.println("Hasil Perkalian Matriks:");
        printMatrix(result);
    }

    // Metode untuk mengalikan dua matriks
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length; // Jumlah baris matriks A
        int colsA = a[0].length; // Jumlah kolom matriks A
        int rowsB = b.length; // Jumlah baris matriks B
        int colsB = b[0].length; // Jumlah kolom matriks B

        // Memastikan bahwa kolom A sama dengan baris B
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Jumlah kolom matriks A harus sama dengan jumlah baris matriks B.");
        }

        // Membuat matriks hasil dengan ukuran yang sesuai
        int[][] result = new int[rowsA][colsB];

        // Melakukan perkalian matriks
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // Metode untuk mencetak matriks
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
