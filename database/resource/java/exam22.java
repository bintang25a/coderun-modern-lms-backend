
import java.util.Scanner;
public class exam22 {

    public static void main(String[] args) {
        // Data array awal
        int[] rakaat = {12, 104, 14, 23, 34};

        System.out.println("Data awal:");
        niatSholat(rakaat);

        // Memanggil method sorting
        limaWaktu(rakaat);

        System.out.println("\nData setelah diurutkan:");
        niatSholat(rakaat);
    }

    // Method untuk menampilkan isi array
    public static void niatSholat(int[] dibaca) {
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.print(dibaca[wajib] + " ");
        }
        System.out.println();
    }

    // Method sorting (Insertion Sort)
    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int islam = salam[takbir]; // Nilai yang sedang dicek
            int rukun = takbir - 1;

            // Geser elemen yang lebih besar ke kanan
            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            // Letakkan nilai pada posisi yang benar
            salam[rukun + 1] = islam;
        }
    }
}
    

