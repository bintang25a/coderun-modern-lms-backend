import java.util.Scanner;

public class fpbKpk11 {
    public static int fpb(int x, int y) {
        int temp;
        // Memastikan y selalu lebih besar dari x di awal
        if (x < y) {
            temp = x;
            x = y;
            y = temp;
        }
        while (y > 0) {
            temp = x % y; // kunci rumus fpb
            x = y;
            y = temp;
        }
        return x;
    }

    // --- Fungsi Menghitung KPK ---
    public static int kpk(int x, int y) {
        int a = x;
        int b = y;
        while (a != b) {
            if (a < b) {
                a = a + x;
            } else {
                b = b + y;
            }
        }
        return a;
    }

    // --- Main Program ---
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a, b;

        // 1. Mencetak Judul sesuai request
        System.out.println("Program KPK dan FPB Raihan Abi Nugroho_24040700056");

        // 2. Input Nilai
        System.out.print("\nInput Nilai A = "); 
        a = input.nextInt();
        
        System.out.print("Input Nilai B = "); 
        b = input.nextInt();

        // 3. Menghitung Hasil
        int hasilFpb = fpb(a, b);
        int hasilKpk = kpk(a, b);

        // 4. Mencetak Output dengan format jarak baris (\n)
        System.out.println("\nCetak Hasil FPB = " + hasilFpb);
        System.out.println("\nCetak Hasil KPK = " + hasilKpk);
    }
    
}
