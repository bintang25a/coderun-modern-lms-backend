import java.util.Scanner;

public class TugasPertemuan4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== MENU ===");
        System.out.println("[1] FPB");
        System.out.println("[2] KPK");
        System.out.println("[3] Perkalian Rusia");
        System.out.println("[4] Akar");
        System.out.println("[0] Keluar");

        while (true) {
            // Meminta input bilangan A dan B
            System.out.print("\nMasukan bilangan A: ");
            int a = scanner.nextInt();
            System.out.print("Masukan bilangan B: ");
            int b = scanner.nextInt();


            System.out.print("\nMasukan pilihan: ");
            int pilihan = scanner.nextInt();

            if (pilihan == 0) {
                break;
            }


            switch (pilihan) {
                case 1:
                    System.out.println("Hasil FPB dari " + a + " " + b + " = " + fpb(a, b));
                    break;
                case 2:
                    System.out.println("Hasil KPK dari " + a + " " + b + " = " + kpk(a, b));
                    break;
                case 3:
                    System.out.println("Hasil perkalian dari " + a + " " + b + " = " + perkalianRusia(a, b));
                    break;
                case 4:
                     System.out.println("Akar dari " + a + " = " + findSquareRoot(a));
                     System.out.println("Akar dari " + b + " = " + findSquareRoot(b));
                    break;
                default:
                    System.out.println("Pilihan salah, silakan ulangi dari awal");
                    continue;
            }
            
            System.out.print("\nLanjut menghitung? [1/0] : ");
            char lanjut = scanner.next().charAt(0);
            if (lanjut != '1' && lanjut != '1'){
                break;
            }
        }

        scanner.close();
        
}
    

    public static int fpb(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int kpk(int a, int b) {
        return (a * b) / fpb(a, b);
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

    public static int square(int x){
        return x*x;
    }
    
    public static int findSquareRoot(int n) {
        int r=0;
        while (square(++r) <= n);
        return --r;
    }
}