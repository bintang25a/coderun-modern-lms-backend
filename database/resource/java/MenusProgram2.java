import java.util.Scanner;
public class TugasAkarBilanganBulat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("=== MENU ===");
            System.out.println("[1] FPB");
            System.out.println("[2] KPK");
            System.out.println("[3] Perkalian Rusia");
            System.out.println("[4] Akar");
            System.out.println("[0] Keluar");
            
            System.out.print("Masukan bilangan A: ");
            int a = scanner.nextInt();
            System.out.print("Masukan bilangan B: ");
            int b = scanner.nextInt();

            System.out.print("\nMasukan pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("Hasil FPB dari " + a + " dan " + b + " = " + hitungFPB(a, b));
                    break;
                case 2:
                    System.out.println("Hasil KPK dari " + a + " dan " + b + " = " + hitungKPK(a, b));
                    break;
                case 3:
                    System.out.println("Hasil perkalian dari " + a + " dan " + b + " = " + perkalianRusia(a, b));
                    break;
                case 4:
                    System.out.println("Akar dari " + a + " = " + Math.sqrt(a));
                    System.out.println("Akar dari " + b + " = " + Math.sqrt(b));
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan salah, silahkan ulangi dari awal.");
                    break;
            }

            if (pilihan != 0) {
                System.out.print("\nLanjut menghitung? [1/0] : ");
                pilihan = scanner.nextInt();
            }
        } while (pilihan != 0);

        scanner.close();
    }

    // Fungsi untuk menghitung FPB menggunakan algoritma Euclidean
    public static int hitungFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Fungsi untuk menghitung KPK
    public static int hitungKPK(int a, int b) {
        return (a * b) / hitungFPB(a, b);
    }

    // Fungsi untuk Perkalian Rusia
    public static int perkalianRusia(int a, int b) {
        int hasil = 0;
        while (a > 0) {
            if (a % 2 != 0) {
                hasil += b;
            }
            a /= 2;
            b *= 2;
        }
        return hasil;
    }
}

