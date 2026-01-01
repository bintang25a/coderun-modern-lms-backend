import java.util.Scanner;

public class TQuiz {

    public static void cekBilPrima(int n) {
        System.out.println("Memulai pengecekan bilangan: " + n); // Debugging
        int i;
        boolean angkaPrima = true;

        if (n == 0 || n == 1) {
            angkaPrima = false;
        } else {
            for (i = 2; i <= n / 2; i++) {
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("## Program Java Cek Bilangan Prima ##");
        System.out.println("=======================================\n");

        System.out.print("Input sebuah angka bulat: ");
        if (input.hasNextInt()) { // Memastikan input valid
            int n = input.nextInt();
            System.out.println("Anda memasukkan angka: " + n); // Debugging
            cekBilPrima(n);
        } else {
            System.out.println("Input tidak valid! Harap masukkan angka bulat.");
        }
        System.out.println("Program selesai."); // Debugging
    }
}
