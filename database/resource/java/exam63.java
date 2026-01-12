import java.util.Scanner;

public class waduhLupaSholat {

    public static void niatSholat(int[] dibaca) {
        System.out.print("Data rakaat: ");
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.print(dibaca[wajib] + " ");
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah data rakaat: ");
        int n = input.nextInt();

        int[] rakaat = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan rakaat ke-" + (i + 1) + ": ");
            rakaat[i] = input.nextInt();
        }

        niatSholat(rakaat);

        limaWaktu(rakaat);

        System.out.println("Setelah diurutkan:");
        niatSholat(rakaat);

        input.close();
    }
}