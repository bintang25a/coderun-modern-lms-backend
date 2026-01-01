import java.util.Scanner; // Perbaikan: Import library Scanner agar dapat digunakan untuk input.

public class Quiz {

    // Perbaikan: Mengganti tipe parameter dan penamaan metode sesuai konvensi Java.
    public static void cekBilPrima(int n) {
        int i;
        boolean angkaPrima = true;

        // Perbaikan: Mengubah operator assignment (=) menjadi perbandingan (==) dan perbaikan penamaan variabel.
        if (n == 0 || n == 1) {
            angkaPrima = false;
        } else {
            // Perbaikan: Mengubah iterasi dari "i--" menjadi "i++" untuk iterasi ke depan dan memperbaiki logika modulus.
            for (i = 2; i <= n / 2; i++) {
                if (n % i == 0) { // Perbaikan: Mengganti "&" menjadi "%" untuk operasi modulus.
                    angkaPrima = false;
                    break; // Perbaikan: Menambahkan titik koma di akhir baris.
                }
            }
        }

        // Perbaikan: Menambahkan titik koma di akhir perintah dan menggunakan penamaan variabel yang konsisten.
        if (angkaPrima) {
            System.out.println(n + " adalah angka prima");
        } else {
            System.out.println(n + " bukan angka prima"); // Perbaikan: Menambahkan titik koma di akhir perintah.
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Perbaikan: Memperbaiki deklarasi Scanner dan nama metode input.
        Scanner input = new Scanner(System.in);

        System.out.println("##  Program Java Cek Bilangan Prima  ##");
        System.out.println("=======================================\n\n"); // Perbaikan: Mengganti "System.out,println" menjadi "System.out.println".

        System.out.print("Input sebuah angka bulat: ");
        int n = input.nextInt(); // Perbaikan: Mengganti "nextint" menjadi "nextInt" untuk sesuai dengan konvensi Java.

        cekBilPrima(n); // Perbaikan: Menambahkan titik koma di akhir baris dan konsistensi huruf kapital dalam nama metode.

        input.close(); // Perbaikan: Menutup Scanner untuk mencegah kebocoran sumber daya.
    }
}
