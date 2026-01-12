import java.util.Scanner;
public class fpbKpk15 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program KPK dan FPB NAMA-NIM\n");

        System.out.print("Input Nilai A = ");

        int a = 12; 
        System.out.println(a); 
 
        System.out.print("Input Nilai B = ");
        int b = 25; 
        System.out.println(b); 
 
        int fpb = hitungFPBAlternatif(a, b);
   
        int kpk = (a * b) / fpb;
   
        System.out.println("\nCetak Hasil FPB = " + fpb);
        System.out.println("\nCetak Hasil KPK = " + kpk);
 
    }

    static int hitungFPBAlternatif(int a, int b) {
        int fpb = 1;
        int batas = (a < b) ? a : b; 

        for (int i = 1; i <= batas; i++) {
            
            if (a % i == 0 && b % i == 0) {
                fpb = i; 
            }
        }
        return fpb;
    }
}