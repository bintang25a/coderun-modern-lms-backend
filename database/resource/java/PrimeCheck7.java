import java.util.Scanner;

public class Quiz {

    public static void cekBilPrima(int n) {
        int i;
        boolean angkaPrima = true;

        if (n == 0 || n == 1) { // Perbaikan: Mengganti '=' dengan '==' untuk perbandingan
            angkaPrima = false;
        } else {
            for (i = 2; i <= n / 2; i++) { // Perbaikan: Mengubah 'i--' menjadi 'i++' agar iterasi benar
                if (n % i == 0) {
                    angkaPrima = false;
                    break;
                }
            }
        }

        if (angkaPrima) {
            System.out.println(n + " adalah angka prima");
        } else {
            System.out.println(n + " bukan angka prima");
        }
    }

    public static void main(String[] args) { // Perbaikan: Mengubah 'stattic' menjadi 'static'
        Scanner input = new Scanner(System.in);

        System.out.println("## Program Java Cek Bilangan Prima ##");
        System.out.println("=======================================\n");

        System.out.print("Input sebuah angka bulat: ");
        int n = input.nextInt(); // Perbaikan: Mengubah 'nextint()' menjadi 'nextInt()'

        cekBilPrima(n); // Perbaikan: Menambahkan tanda titik koma (;)
    }
}