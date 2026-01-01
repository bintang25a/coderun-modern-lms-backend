public class Tugaspraktik {

    /**
     * Method untuk mencetak elemen array 2 dimensi.
     * @param array Array 2 dimensi yang akan dicetak.
     */
    public static void printArray2D(int[][] array) {
        // Iterasi melalui setiap baris dalam array
        for (int i = 0; i < array.length; i++) { 
            // Iterasi melalui setiap elemen dalam baris
            for (int j = 0; j < array[i].length; j++) { 
                System.out.print(array[i][j] + " "); // Cetak elemen dengan spasi
            }
            System.out.println(); // Pindah ke baris berikutnya
        }
    }

    /**
     * Method utama (main) untuk menjalankan program.
     * @param args Argumen command line (tidak digunakan dalam program ini).
     */
    public static void main(String[] args) {
        // Deklarasi dan inisialisasi array 2 dimensi
        int[][] array = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Memanggil method untuk mencetak array
        printArray2D(array);

        // Menampilkan pesan BUILD SUCCESSFUL
        System.out.println("\nBUILD SUCCESSFUL (total time: 0 seconds)");
    }
}
