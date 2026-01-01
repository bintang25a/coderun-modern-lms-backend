import java.util.Scanner;

public class TugasMatematika {

    public static int hitungFPB(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int hitungKPK(int a, int b) {
        return (a * b) / hitungFPB(a, b);
    }

    public static int perkalianRusia(int a, int b) {
        int hasil = 0;
        while (b > 0) {
            if (b % 2 != 0) {
                hasil += a;
            }
            a *= 2;
            b /= 2;
        }
        return hasil;
    }

    public static double hitungAkar(int a) {
        return Math.sqrt(a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("=== MENU ===");
            System.out.println("[1] FPB");
            System.out.println("[2] KPK");
            System.out.println("[3] Perkalian Rusia");
            System.out.println("[4] Akar");
            System.out.println("[0] Keluar");
            System.out.print("Masukan pilihan: ");
            pilihan = scanner.nextInt();

            if (pilihan == 0) {
                System.out.println("Program selesai.");
                break;
            }

            System.out.print("Masukan bilangan A: ");
            int a = scanner.nextInt();
            System.out.print("Masukan bilangan B: ");
            int b = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("Hasil FPB dari " + a + " dan " + b + " = " + hitungFPB(a, b));
                    break;
                case 2:
                    System.out.println("Hasil KPK dari " + a + " dan " + b + " = " + hitungKPK(a, b));
                    break;
                case 3:
                    System.out.println("Hasil perkalian Rusia dari " + a + " dan " + b + " = " + perkalianRusia(a, b));
                    break;
                case 4:
                    System.out.println("Akar dari " + a + " = " + hitungAkar(a));
                    System.out.println("Akar dari " + b + " = " + hitungAkar(b));
                    break;
                default:
                    System.out.println("Pilihan salah, silakan ulangi dari awal.");
            }

            System.out.print("Lanjut menghitung? [1/0]: ");
            int pilihanLanjut = scanner.nextInt();
            lanjut = pilihanLanjut == 1;
        }

        scanner.close();
    }
}