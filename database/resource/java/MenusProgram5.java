import java.util.Scanner;

public class perhitungan {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int pilihan;
            do {
                System.out.println("=== MENU ===");
                System.out.println("[1] FPB");
                System.out.println("[2] KPK");
                System.out.println("[3] Perkalian Rusia");
                System.out.println("[4] Akar");
                System.out.println("[0] Keluar");
                System.out.print("Masukan pilihan: ");
                pilihan = scanner.nextInt();
                
                if (pilihan == 0) {
                    System.out.println("Keluar dari program.");
                    break;
                }
                
                if (pilihan < 1 || pilihan > 4) {
                    System.out.println("Pilihan salah, silahkan ulangi dari awal\n");
                    continue;
                }
                
                System.out.print("Masukan bilangan A: ");
                int a = scanner.nextInt();
                System.out.print("Masukan bilangan B: ");
                int b = scanner.nextInt();
                
                switch (pilihan) {
                    case 1: // FPB
                        System.out.println("Hasil FPB dari " + a + " dan " + b + " = " + hitungFPB(a, b));
                        break;
                    case 2: // KPK
                        System.out.println("Hasil KPK dari " + a + " dan " + b + " = " + hitungKPK(a, b));
                        break;
                    case 3: // Perkalian Rusia
                        System.out.println("Hasil Perkalian Rusia dari " + a + " dan " + b + " = " + perkalianRusia(a, b));
                        break;
                    case 4: // Akar
                        System.out.println("Akar dari " + a + " = " + Math.sqrt(a));
                        System.out.println("Akar dari " + b + " = " + Math.sqrt(b));
                        break;
                }
                
                System.out.print("Lanjut menghitung? [1/0] : ");
            } while (scanner.nextInt() == 1);
        }
    }

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