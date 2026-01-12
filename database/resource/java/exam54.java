import java.util.Scanner;

public class exam54 {

    public static void main(String[] args) {
        Scanner pemindaiUnik = new Scanner(System.in);
        System.out.print("==== No 1 Dinamis Muhamad Ramzy Pradipta (24040700054) ====\n");
        System.out.print("Masukkan jumlah data: ");
        int jumlahElemenUnik = pemindaiUnik.nextInt();

        int[] dataRakaatUnik = new int[jumlahElemenUnik];

        for (int indeksUnik = 0; indeksUnik < jumlahElemenUnik; indeksUnik++) {
            System.out.print("Masukkan angka ke-" + (indeksUnik + 1) + ": ");
            dataRakaatUnik[indeksUnik] = pemindaiUnik.nextInt();
        }

        limaWaktu(dataRakaatUnik);
        niatSholat(dataRakaatUnik);
    }

    public static void niatSholat(int[] dibaca) {
        System.out.println("\nHasil setelah diurutkan:");
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.print(dibaca[wajib] + " ");
        }
    }

    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int rukun = takbir - 1;
            int islam = salam[takbir];

            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
    }
}
