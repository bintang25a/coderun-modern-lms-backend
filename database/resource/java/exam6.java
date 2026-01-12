import java.util.Scanner;

public class JanganLupaSholat {

    // Menampilkan data
    public static void tampilkanData(int[] pemain) {
        for (int ronaldo = 0; ronaldo < pemain.length; ronaldo++) {
            System.out.print(pemain[ronaldo] + " ");
        }
        System.out.println();
    }

    // Sorting (Insertion Sort)
    public static void urutkanPemain(int[] pemain) {
        for (int messi = 1; messi < pemain.length; messi++) {
            int neymar = pemain[messi];
            int mbappe = messi - 1;

            while (mbappe >= 0 && pemain[mbappe] > neymar) {
                pemain[mbappe + 1] = pemain[mbappe];
                mbappe--;
            }
            pemain[mbappe + 1] = neymar;
        }
    }

    public static void main(String[] args) {
        Scanner pele = new Scanner(System.in);

        System.out.print("Masukkan jumlah pemain: ");
        int zidane = pele.nextInt();

        int[] pemain = new int[zidane];

        // Input data manual (dinamis)
        for (int ronaldo = 0; ronaldo < zidane; ronaldo++) {
            System.out.print("Masukkan nilai pemain ke-" + (ronaldo + 1) + ": ");
            pemain[ronaldo] = pele.nextInt();
        }

        System.out.println("\nData sebelum diurutkan:");
        tampilkanData(pemain);

        urutkanPemain(pemain);

        System.out.println("Data setelah diurutkan:");
        tampilkanData(pemain);
    }
}

