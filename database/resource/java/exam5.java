import java.util.Scanner;

public class JanganLupaSholat {

    public static void main(String[] args) {
        Scanner inputKhususSholat = new Scanner(System.in);

        int[] rakaatSholatWajib = new int[5];
        String[] namaSholat = {"Shubuh", "Zuhur", "Ashar", "Maghrib", "Isya"};

        System.out.println("Masukkan jumlah rakaat untuk masing-masing sholat wajib:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Rakaat " + namaSholat[i] + " = ");
            rakaatSholatWajib[i] = inputKhususSholat.nextInt();
        }

        System.out.println("\nData rakaat sebelum diurutkan:");
        niatSholat(rakaatSholatWajib);

        limaWaktu(rakaatSholatWajib);

        System.out.println("\nData rakaat setelah diurutkan (ascending):");
        niatSholat(rakaatSholatWajib);

        inputKhususSholat.close();
    }

    public static void niatSholat(int[] dibaca) {
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
}