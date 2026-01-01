import java.util.Scanner;

public class Quiz {
    //2. SEBELUM DIGANTI : public static viod cekBilPrima(itn n){
    //salah penulisan viod harusnya void dan itn, harusnya int
        public static void cekBilPrima(int n){
        int i;
        boolean angkaPrima = true;
    //3. SEBELUM DIGANTI : if (N = 0 || N = 1) {
    //sintaks penulisannya salah
    if (n == 0 || n == 1 ) {
    //4. SEBELUM DIGANTI  angka_prima = false;
    //kepenulisannya salah, seharusnya angka_prima diubah jadi angkaPrima
    angkaPrima = false;
        }
        else {
    //5. SEBELUM DIGANTI : for (i = 2; i <= n / 2; i--) { 
    //pengulangannya salah, harusnya i++
         for (i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
    //6. SEBELUM DIGANTI angka_prima = false; 
    //kepenulisannya salah, seharusnya angka_prima diubah jadi angkaPrima
        angkaPrima = false;
        break;  //7. belum ada (;) tadinya
            }
           }
        } 
        if (angkaPrima){
            System.out.println( n + " adalah angka prima");
        }else{
            System.out.println( n + " bukan angka prima"); //8. sebelumnya tidak ada (;)
        }
    }
    
    //9. SEBELUM DIGANTI : public stattic void main(String[] args) {// void bukan viod 
   //penulisan stattic salah, harusnya Static
    public static void main(String[] args) {
    //10. SEBELUM DIGANTI : Scanner input = new scanner(System.in); 
    // penulisannya scanner, Snya kapital.
    Scanner input = new Scanner(System.in); 
    
    //11. SEBELUM DIGANTI : System.out,println("##  Program Java Cek Bilangan Prima  ##");
        // System,out.prlntln("=======================================\n\n");
    //Pemisahnya seharusnya titik bukan koma, dan ada typo println
    
    System.out.println("##  Program Java Cek Bilangan Prima  ##");
    System.out.println("=======================================\n\n");
    System.out.print("Input sebuah angka bulat: ");
    //12. SEBELUM DIGANTI : int n = input.nextint();
    // nextintnya seharusnya Inya kapital
    int n = input.nextInt();
    //SEBELUM DIGANTI : cekbilPrima(n)
    //tidak ada (;) dan Bnya tidak kapital
    cekBilPrima(n);
    }
}

