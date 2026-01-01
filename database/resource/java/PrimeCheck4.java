public class tugas {

    public class CekBilanganPrima {

    // Perbaikan penulisan dan parameter
    public static void cekBilPrima(int n) { 
        int i;
        boolean angkaPrima = true;

        // Koreksi logika pada kondisi awal
        if (n == 0 || n == 1) {
            angkaPrima = false;
        } else {
            // Perbaikan pada loop (i++) bukan i--
            for (i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    angkaPrima = false;
                    break; // Tambahkan titik koma setelah break
                }
            }
        }

        // Koreksi variabel dan tambahkan tanda kurung {} jika diperlukan
        if (angkaPrima) {
            System.out.println(n + " adalah angka prima");
        } else {
            System.out.println(n + " bukan angka prima");
        }
    }

    public static void main(String[] args) { // Perbaikan void bukan viod
        Scanner input = new Scanner(System.in); // Perbaikan huruf besar Scanner

        System.out.println("##  Program Java Cek Bilangan Prima  ##");
        System.out.println("=======================================\n\n");

        System.out.print("Input sebuah angka bulat: ");
        int n = input.nextInt(); // Perbaikan nextInt() dengan huruf besar "I"

        cekBilPrima(n); // Tambahkan titik koma setelah pemanggilan method

        input.close(); // Tutup Scanner untuk mencegah kebocoran sumber daya
    }
}

    
}
