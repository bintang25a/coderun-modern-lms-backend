import java.util.Scanner;

public class Tugas_4 {
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
            
            switch (pilihan) {
                case 1:
                    System.out.println("Hasil FPB dari " + a + " " + b + " = " + gcd(a, b));
                    break;
                case 2:
                    System.out.println("Hasil KPK dari " + a + " " + b + " = " + lcm(a, b));
                    break;
                case 3:
                    System.out.println("Hasil perkalian dari " + a + " " + b + " = " + perkalianRusia(a, b));
                    break;
                case 4:
                    System.out.println("Akar dari " + a + " = " + Math.sqrt(a));
                    System.out.println("Akar dari " + b + " = " + Math.sqrt(b));
                    break;
                default:
                    System.out.println("Pilihan salah, silahkan ulangi dari awal");
                    break;
            }
            
            System.out.print("Lanjut menghitung? [1/0] : ");
            pilihan = scanner.nextInt();
        } while (pilihan == 1);
        
        scanner.close();
    }
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
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
}
