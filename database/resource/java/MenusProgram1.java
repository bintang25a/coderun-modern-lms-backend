import java.util.Scanner;

public class MenuProgram {
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

            System.out.print("Masukan pilihan: ");
            pilihan = scanner.nextInt();

            if (pilihan < 0 || pilihan > 4) {
                System.out.println("Pilihan salah, silahkan ulangi dari awal");
                continue;
            }

            if (pilihan == 0) {
                System.out.println("Keluar dari program.");
                break;
            }

            switch (pilihan) {
                case 1: // FPB
                    System.out.println("Hasil FPB dari " + a + " dan " + b + " = " + hitungFPB(a, b));
                    break;
                case 2: // KPK
                    System.out.println("Hasil KPK dari " + a + " dan " + b + " = " + hitungKPK(a, b));
                    break;
                case 3: // Perkalian Rusia
                    System.out.println("Hasil perkalian dari " + a + " dan " + b + " = " + perkalianRusia(a, b));
                    break;
                case 4: // Akar (dibulatkan ke bilangan bulat)
                    System.out.println("Akar dari " + a + " = " + (int) Math.sqrt(a));
                    System.out.println("Akar dari " + b + " = " + (int) Math.sqrt(b));
                    break;
            }

            System.out.print("Lanjut menghitung? [1/0]: ");
            int lanjut = scanner.nextInt();
            if (lanjut == 0) {
                break;
            }
        } while (true);

        scanner.close();
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
        while (b > 0) {
            if (b % 2 == 1) {
                hasil += a;
            }
            a *= 2;
            b /= 2;
        }
        return hasil;
    }
}
