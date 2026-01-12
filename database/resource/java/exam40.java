import java.util.Scanner;

public class WaduhLupaSholat {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Masukkan jumlah data: ");
            int jumlah = input.nextInt();
            
            int[] rakaat = new int[jumlah];
            
            // INPUT DINAMIS (manual)
            for (int i = 0; i < jumlah; i++) {
                System.out.print("Masukkan data ke-" + (i + 1) + ": ");
                rakaat[i] = input.nextInt();
            }
            
            System.out.println("\nData awal:");
            niatSholat(rakaat);
            
            limaWaktu(rakaat);
            
            System.out.println("\nData setelah diurutkan:");
            niatSholat(rakaat);
        }
    }
    public static void niatSholat(int[] dibaca) {
        for (int i = 0; i < dibaca.length; i++) {
            System.out.print(dibaca[i] + " ");
        }
        System.out.println();
    }

    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int islam = salam[takbir];
            int rukun = takbir - 1;

            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
    }
}
