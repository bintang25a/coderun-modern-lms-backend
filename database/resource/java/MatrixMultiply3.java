/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perkalianmatrix;

public class  perkalianmatrix {
    public static void main(String[] args) {
        int[][] A = {
            {4, 3, 1},
            {2, 6, 5}
        };
        
        int[][] B = {
            {2, 5, 4, 6},
            {8, 2, 7, 0},
            {7, 1, 3, 9}
        };
        
        // Menampilkan matriks A
        System.out.println("Matriks A:");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

        // Menampilkan matriks B
        System.out.println("Matriks B:");
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }

        // Memastikan jumlah kolom di A sama dengan jumlah baris di B
        if (A[0].length != B.length) {
            System.out.println("Tidak dapat mengalikan matriks: jumlah kolom di matriks A tidak sama dengan jumlah baris di matriks B.");
            return;
        }

        int[][] C = new int[A.length][B[0].length];

        // Melakukan perkalian matriks
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // Menampilkan hasil perkalian matriks
        System.out.println("Hasil perkalian matriks A dan B:");
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
