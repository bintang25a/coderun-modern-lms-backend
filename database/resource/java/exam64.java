import java.util.Scanner;

public class exam64 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah data rakaat: ");
        int n = input.nextInt();

        int[] rakaat = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan rakaat ke-" + (i + 1) + ": ");
            rakaat[i] = input.nextInt();
        }

        System.out.println("\nData sebelum diurutkan:");
        niatSholat(rakaat);

        limaWaktu(rakaat);

        System.out.println("\nData setelah diurutkan:");
        niatSholat(rakaat);

        input.close();
    }

    public static void niatSholat(int[] dibaca) {
        for (int i = 0; i < dibaca.length; i++) {
            System.out.print(dibaca[i] + " ");
        }
        System.out.println();
    }

    public static void limaWaktu(int[] salam) {
        for (int i = 1; i < salam.length; i++) {
            int key = salam[i];
            int j = i - 1;

            while (j >= 0 && salam[j] > key) {
                salam[j + 1] = salam[j];
                j--;
            }
            salam[j + 1] = key;
        }
    }
}