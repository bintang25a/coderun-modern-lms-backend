// NAMA : FATHONI ADAM ILYASA
// NIM  : 24040700060
import java.util.Scanner;

public class exam24 {

    // Main method utama
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input jumlah elemen array
        System.out.print("Masukkan jumlah rakaat: ");
        int masukeuy = sc.nextInt();

        int[] rakaat = new int[masukeuy];

        // Input nilai array
        for (int i = 0; i < masukeuy; i++) {
            int urutan = i+1;
            System.out.print("Masukkan nilai rakaat ke-" + urutan + ": ");
            rakaat[i] = sc.nextInt();
        }

        System.out.println("\nSebelum diurutkan:");
        niatSholat(rakaat);

        // Panggil fungsi sorting
        limaWaktu(rakaat);

        System.out.println("\nSetelah diurutkan:");
        niatSholat(rakaat);

        sc.close();
    }

    // Fungsi untuk mencetak isi array
    public static void niatSholat(int[] dibaca) {
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.print(dibaca[wajib] + " ");
        }
        System.out.println();
    }

    // Fungsi untuk mengurutkan array (insertion sort)
    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int islam = salam[takbir];
            int rukun = takbir - 1;

            // geser elemen yang lebih besar ke kanan
            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
    }
}
