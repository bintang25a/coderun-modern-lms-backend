public class Array2dimensi {

    public static void cetakArray2D(int A[][]) {
        for (int[] row : A) { // Iterasi setiap baris
            for (int element : row) { // Iterasi setiap elemen di dalam baris
                System.out.print(element + " ");
            }
            System.out.println(""); // Pindah ke baris berikutnya
        }
    }

    public static void main(String[] args) {
        // Deklarasi Array 2 dimensi secara statis
        int A[][] = {
            {1, 2, 3},
            {4, 5, 6, 7, 8, 9}
        };

        // Panggil fungsi cetakArray2D untuk menampilkan array 2 dimensi
        cetakArray2D(A);
    }
}