import java.util.Scanner;
 
public class Perhitungan {
    
    public static int fpb(int x, int y) {
        int tmp;
        
        if (x<y) {
            tmp=x;
            x=y;
            y=tmp;
        }
        
        while (y>0) {
            tmp = x%y;
            x=y;
            y=tmp;
        }
        
        return x;
        
    }
    
    public static int kpk(int x, int y) {
        int a=x, b=y;
        
        while (a!=b) {
            
            if (a<b) {
                a+=x;
            }
            else {
                b+=y;
            }
        }
        return a;
    }
    
    public static int perkalianRusia(int a, int b) {
        
        int total=0;
        
        while (a>0) {
            if (a%2==1)
                total+=b;
            a/=2;
            b*=2;
        }
        return total;
    }
    public static int square(int x) {
        return x*x;
    }
    
    
    public static int findSquareRoot(int n) {
        int r=0;
        while (square(++r) <= n);
        return --r;
    }
   
    
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        

        do {
            // Menampilkan menu
            System.out.println("=== Menu ===");
            System.out.println("1. FPB");
            System.out.println("2. KPK");
            System.out.println("3. Perkalian Rusia");
            System.out.println("4. Akar");
            System.out.println("0. Keluar");
            System.out.print("Masukkan Bilangan A: ");
            int a = scanner.nextInt(); 
            System.out.print("Masukkan Bilangan B: ");
            int b = scanner.nextInt();
            System.out.print("Masukkan pilihan Anda: ");
            pilihan = scanner.nextInt();
            
            if (pilihan < 0 || pilihan > 4){
                System.out.println("Pilihan salah, silahkan ulangin dari awal");
                continue;
            }
            if (pilihan == 0) {
                System.out.println("Keluar program");
                break;
            }

            // Menjalankan pilihan
            switch (pilihan) {
                case 1:
                    System.out.println("Hasil FPB: " + fpb(a, b));
                    break;
                
                    
                case 2:
                    System.out.println("Hasil KPK: " + kpk(a, b));
                    break;
                     
                    
                case 3:
                    System.out.println("\nHasil = " + perkalianRusia(a, b));
                    
                    break;
                case 4:
                    System.out.println("Akar dari angka " + a + " = " + findSquareRoot(a));
            
                    break;
                
            }

            // Menanyakan apakah pengguna ingin melanjutkan
            System.out.print("Apakah ingin lanjut menghitung[1/0]?");
            int lanjut = scanner.nextInt();
            if (lanjut == 0){
                break;
            }
        }while(true);

        scanner.close();
    }
}