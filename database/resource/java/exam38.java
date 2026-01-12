import java.util.Scanner;

public class exam38 {

    static Scanner sc = new Scanner(System.in);
 
    public static void main(String[] args) {
        int pilihanMenu;
        do {
            System.out.println("\nPROGRAM SERBA GUNA JAVA");
            System.out.println("[1] Perkalian Rusia\n[2] Hitung FPB\n[3] Hitung KPK\n[4] Program Matriks\n[5] Keluar Program");
            System.out.print("Pilih Menu: ");
            pilihanMenu = sc.nextInt();

            switch (pilihanMenu) {
                case 1: rusiaMultiply(); break;
                case 2: hitungFPB(); break;
                case 3: hitungKPK(); break;
                case 4: programMatriks(); break;
                case 5: System.out.println("Keluar..."); break;
                default: System.out.println("Menu tidak ditemukan!"); break;
            }
        } while (pilihanMenu != 5);
    }

    static void rusiaMultiply() {
        System.out.print("Masukkan Nilai Pertama: "); int a = sc.nextInt();
        System.out.print("Masukkan Nilai Kedua: "); int b = sc.nextInt();
        int hasil = 0;
        System.out.println("Proses Perkalian:");
        while (a >= 1) {
            if (a % 2 != 0) {
                hasil += b;
                System.out.println(a + "\t" + b + " (ambil)");
            } else {
                System.out.println(a + "\t" + b);
            }
            a /= 2; b *= 2;
        }
        System.out.println("Hasil Perkalian: " + hasil);
    }

    static void hitungFPB() {
        System.out.print("Nilai 1: "); int a = sc.nextInt();
        System.out.print("Nilai 2: "); int b = sc.nextInt();
        int fpbValue = cariFPB(a, b);
        System.out.println("Hasil FPB: " + fpbValue);
    }

    static int cariFPB(int a, int b) {
        return (b == 0) ? a : cariFPB(b, a % b);
    }

    static void hitungKPK() {
        System.out.print("Nilai 1: "); int a = sc.nextInt();
        System.out.print("Nilai 2: "); int b = sc.nextInt();
        int kpk = (a * b) / cariFPB(a, b);
        System.out.println("Hasil KPK: " + kpk);
    }

    static void programMatriks() {
        System.out.print("Baris: "); int r = sc.nextInt();
        System.out.print("Kolom: "); int c = sc.nextInt();
        int[][] m = new int[r][c];
        for(int i=0; i<r; i++) 
            for(int j=0; j<c; j++) {
                System.out.print("Matriks ["+i+"]["+j+"]: ");
                m[i][j] = sc.nextInt();
            }
    }
}
       
