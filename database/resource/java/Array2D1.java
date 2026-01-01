public class Array2D {
    /**
     * Fungsi untuk mencetak array 2 dimensi.
     * 
     * @param A array 2 dimensi yang akan dicetak
     */
    public static void cetakArray2D(int[][] A) {
        for (int i = 0; i < A.length; i++) { // Baris
            for (int j = 0; j < A[i].length; j++) { // Kolom
                System.out.print(A[i][j] + " "); // Perbaikan dari println ke print untuk mencetak secara horizontal
            }
            System.out.println(); // Baris baru setelah satu baris array dicetak
        }
    }
    /**
     * Fungsi utama untuk menjalankan program.
     * 
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        }; // Deklarasi array 2 dimensi statis

        System.out.println("Array 2 Dimensi:");
        cetakArray2D(A); // Memanggil fungsi untuk mencetak array
    }
}