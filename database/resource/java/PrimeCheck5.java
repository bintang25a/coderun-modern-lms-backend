import java.util.Scanner;

public class JavaQuiz { // Perbaikan nama class agar sesuai konvensi (huruf besar pada nama class)

    // Method untuk mengecek bilangan prima
    public static void cekBilPrima(int n) { // Perbaikan nama parameter dari 'itn' menjadi 'n'
        boolean angkaPrima = true; // Gunakan nama variabel yang konsisten
        
        // Bilangan 0 dan 1 bukan bilangan prima
        if (n == 0 || n == 1) { // Perbaikan 'N' menjadi 'n' agar sesuai dengan parameter
            angkaPrima = false;
        } else {
            // Periksa bilangan dari 2 hingga n/2
            for (int i = 2; i <= n / 2; i++) { // Perbaikan loop 'i--' menjadi 'i++'
                if (n % i == 0) {
                    angkaPrima = false;
                    break; // Hentikan loop jika ditemukan pembagi
                }
            }
        }

        // Tampilkan hasil
        if (angkaPrima) {
            System.out.println(n + " adalah angka prima");
        } else {
            System.out.println(n + " bukan angka prima");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Perbaikan komentar, dan Scanner sesuai library

        System.out.println("## Program Java Cek Bilangan Prima ##");
        System.out.println("=====================================");

        System.out.print("Input sebuah angka bulat: ");
        int n = input.nextInt(); // Perbaikan tipe Scanner dari 'input.nextint()' menjadi 'input.nextInt()'
        
        cekBilPrima(n); // Memanggil method cekBilPrima
    }
}
