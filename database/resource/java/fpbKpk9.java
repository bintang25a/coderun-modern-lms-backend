import java.util.Scanner;

public class fpbKpk9 {                
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int a, b, x, y, fpb, kpk;
        
        System.out.println("Program KPK dan FPB NAMA-NIM");
        System.out.println();
        
        System.out.print("Input Nilai A = ");
        a = input.nextInt();
        
        System.out.print("Input Nilai B = ");
        b = input.nextInt();
        
        // Menghitung FPB (Algoritma Euclidean)
        x = a;
        y = b;
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        fpb = x;
        
        // Menghitung KPK
        kpk = (a * b) / fpb;
        
        System.out.println();
        System.out.println("Cetak Hasil FPB = " + fpb);
        System.out.println("Cetak Hasil KPK = " + kpk);
        
        input.close();
    }
}