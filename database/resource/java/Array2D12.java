import java.util.Scanner;

public class T7_ARRAY2D {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     // Membuat objek Scanner untuk membaca input
        Scanner input = new Scanner(System.in);
        
        // Input jumlah baris dan kolom dari array 2D
        System.out.print("Masukkan jumlah baris: ");
        int baris = input.nextInt();
        
        System.out.print("Masukkan jumlah kolom: ");
        int kolom = input.nextInt();
        
        // Membuat array 2 dimensi dengan ukuran [baris][kolom]
        int[][] array2D = new int[baris][kolom];
        
        // Menginput nilai untuk array 2D
        System.out.println("Masukkan elemen-elemen array:");
        for (int i = 0; i < baris; i++) {  // Mengiterasi baris
            for (int j = 0; j < kolom; j++) {  // Mengiterasi kolom
                System.out.print("Elemen [" + i + "][" + j + "]: ");
                array2D[i][j] = input.nextInt();  // Menginput elemen ke dalam array
            }
        }
        
        // Menampilkan array 2D
        System.out.println("\nArray 2 Dimensi yang Anda masukkan:");
        tampilkanArray(array2D);
        
        input.close(); // Menutup Scanner
    }
    public static void tampilkanArray(int[][] array) {
        // Mengiterasi baris
        for (int i = 0; i < array.length; i++) {
            // Mengiterasi kolom
            for (int j = 0; j < array[i].length; j++) {
                // Menampilkan elemen
                System.out.print(array[i][j] + " ");
            }
            // Pindah ke baris berikutnya
            System.out.println();
        }
    }
    }   
    
    

