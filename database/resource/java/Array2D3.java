import java.util.Arrays;

public class Array {
    

    // Fungsi untuk mengalikan dua array 2D
    public static int[][] multiplyArrays(int[][] array1, int[][] array2) {
        // Cek apakah dimensi array sesuai
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            throw new IllegalArgumentException("Array harus memiliki dimensi yang sama");
        }

        int rows = array1.length;
        int cols = array1[0].length;
        int[][] result = new int[rows][cols];

        // Perkalian elemen array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = array1[i][j] * array2[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Deklarasi array 2D
        int[][] array1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] array2 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        try {
            // Panggil fungsi untuk perkalian
            int[][] result = multiplyArrays(array1, array2);

            // Cetak hasil
            System.out.println("Hasil perkalian array 2D:");
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}