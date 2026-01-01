import java.util.Scanner;

public class Tugas6 { // nama clasnya di perbaiki agar sesuai  
    
    //metod
    public static void cekBilPrima(int n) { // nama parameter dari 'itn' menjadi 'n'
        boolean angkaPrima = true; //nama variabel yang konsisten
        
        // Bilangan 0 dan 1 bukan bilangan prima
        if (n == 0 || n == 1) { // Perbaikan 'N' menjadi 'n' agar sesuai 
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


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         Scanner input = new Scanner(System.in); 
         
        System.out.println("## Program Java Cek Bilangan Prima ##");
        System.out.println("=====================================");

        System.out.print("Input sebuah angka bulat: ");
        int n = input.nextInt(); 
        
        cekBilPrima(n); 
    }
    
}
