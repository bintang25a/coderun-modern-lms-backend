import java.util.Scanner;

public class exam65 {
    public static void main(String[] tunjuk) {
        Scanner cetak = new Scanner(System.in);

        System.out.print("Masukkan jumlah rakaat: ");
        int jmlhRka = cetak.nextInt();

        int[] listRakaat = new int[jmlhRka];
        for (int idx = 0; idx < jmlhRka; idx++) {
            System.out.print("Input rakaat ke-" + (idx + 1) + ": ");
            listRakaat[idx] = cetak.nextInt();
        }

        System.out.println("\nNiat Sholat:");
        pancingNiat(listRakaat);

        System.out.println("\nSetelah diurutkan:");
        beberLimaWaktu(listRakaat);
        pancingNiat(listRakaat);

        cetak.close();
    }

    public static void pancingNiat(int[] bukti) {
        for (int tampung = 0; tampung < bukti.length; tampung++) {
            System.out.print(bukti[tampung] + " ");
        }
        System.out.println();
    }

    public static void beberLimaWaktu(int[] saji) {
        for (int b = 1; b < saji.length; b++) {
            int mengunci = saji[b];
            int kunci = b - 1;

            while (kunci >= 0 && saji[kunci] > mengunci) {
                saji[kunci + 1] = saji[kunci];
                kunci--;
            }
            saji[kunci + 1] = mengunci;
        }
    }
}