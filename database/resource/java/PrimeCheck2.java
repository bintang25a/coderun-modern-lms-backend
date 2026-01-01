import java.util.Scanner; // Mengimpor kelas Scanner untuk input pengguna

public class Quiz {

    // Perbaikan: 'viod' menjadi 'void', 'itn' menjadi 'int'
    public static void cekBilPrima(int n) {
        int i;
        boolean angkaPrima = true;

        // Perbaikan: 'N = 0' menjadi 'n == 0', 'N = 1' menjadi 'n == 1'
        if (n == 0 || n == 1) {
            angkaPrima = false; // Perbaikan: 'angka_prima' menjadi 'angkaPrima'
        } else {
            // Perbaikan: 'i--' menjadi 'i++', karena kita harus meningkatkan nilai i
            for (i = 2; i <= n / 2; i++) { 
                if (n % i == 0) {
                    angkaPrima = false;
                    break; // Perbaikan: Menambahkan titik koma
                }
            }
        }

        // Perbaikan: 'angka_prima' menjadi 'angkaPrima'
        if (angkaPrima) {
            System.out.println(n + " adalah angka prima");
        } else {
            // Perbaikan: Menambahkan titik koma dan memperbaiki penulisan 'System.out.println'
            System.out.println(n + " bukan angka prima");
        }
    }

    // Perbaikan: 'stattic' menjadi 'static'
    public static void main(String[] args) {
        // Perbaikan: 'Scanner' menjadi 'new Scanner(System.in)' untuk objek input
        Scanner input = new Scanner(System.in); 

        // Perbaikan: Penulisan 'System.out.println' dan 'System.out.println'
        System.out.println("##  Program Java Cek Bilangan Prima  ##");
        System.out.println("=======================================\n\n");

        // Perbaikan: Penulisan 'System.out.print'
        // penambahan kata bilangan 
        System.out.print("Input sebuah angka bilangan bulat: ");
        // Perbaikan: 'nextint()' menjadi 'nextInt()' sesuai dengan konvensi penulisan metode
        int n = input.nextInt();
        // Perbaikan: Menambahkan titik koma dan memperbaiki penulisan 'cekBilPrima'
        cekBilPrima(n);
    }
}

