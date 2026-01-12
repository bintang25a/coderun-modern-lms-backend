import java.util.Scanner;

public class exam50 {
     static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("=================================");
            System.out.println("      PROGRAM SERBA GUNA JAWA     ");
            System.out.println("=================================");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("=================================");
            System.out.print("Pilih Menu : ");
            pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    perkalianRusia();
                    break;
                case 2:
                    hitungFPB();
                    break;
                case 3:
                    hitungKPK();
                    break;
                case 4:
                    programMatriks();
                    break;
                case 5:
                    System.out.println("THANK YOU");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
            }
            System.out.println();
        } while (pilih != 5);
    }

    
    static void perkalianRusia() {
        System.out.println("\nProgram Perkalian Rusia !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int hasil = 0;

        System.out.println("\nProses Perkalian:");
        while (a > 0) {
            System.out.println(a + "\t" + b + (a % 2 != 0 ? "  -> ambil " + b : ""));
            if (a % 2 != 0) {
                hasil += b;
            }
            a /= 2;
            b *= 2;
        }
        System.out.println("\nHasil Perkalian = " + hasil);
    }

   
    static void hitungFPB() {
        System.out.println("\nProgram FPB !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int x = a, y = b;
        while (y != 0) {
            int sisa = x % y;
            x = y;
            y = sisa;
        }
        System.out.println("Cetak Hasil FPB = " + x);
    }

    
    static void hitungKPK() {
        System.out.println("\nProgram KPK !");
        System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua   : ");
        int b = input.nextInt();

        int fpb = a, temp = b;
        while (temp != 0) {
            int sisa = fpb % temp;
            fpb = temp;
            temp = sisa;
        }

        int kpk = (a * b) / fpb;
        System.out.println("Cetak Hasil KPK = " + kpk);
    }

    
    static void programMatriks() {
        System.out.println("\nProgram Matriks Dinamis !");
        System.out.print("Masukkan jumlah baris matriks : ");
        int baris = input.nextInt();
        System.out.print("Masukkan jumlah kolom matriks : ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        System.out.println("\nInput Matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + i + "][" + j + "] = ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nHasil Matriks:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
    
   
    

