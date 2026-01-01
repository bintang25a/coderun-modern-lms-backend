public class ARRAY_Perkalian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Input panjang array pertama
        System.out.print("Masukkan panjang array pertama: ");
        int n1 = input.nextInt();
        
        // Input panjang array kedua
        System.out.print("Masukkan panjang array kedua: ");
        int n2 = input.nextInt();
        
        // Pastikan kedua array memiliki panjang yang sama
        if (n1 != n2) {
            System.out.println("Array harus memiliki panjang yang sama untuk melakukan perkalian!");
            return;
        }
        
        // Membuat dua array satu dimensi
        int[] array1 = new int[n1];
        int[] array2 = new int[n2];
        
        // Input elemen array pertama
        System.out.println("Masukkan elemen-elemen array pertama:");
        for (int i = 0; i < n1; i++) {
            System.out.print("array1[" + i + "]: ");
            array1[i] = input.nextInt();
        }
        
        // Input elemen array kedua
        System.out.println("Masukkan elemen-elemen array kedua:");
        for (int i = 0; i < n2; i++) {
            System.out.print("array2[" + i + "]: ");
            array2[i] = input.nextInt();
        }
        
        // Array untuk menyimpan hasil perkalian
        int[] hasil = new int[n1];
        
        // Melakukan perkalian elemen yang sesuai antara array1 dan array2
        for (int i = 0; i < n1; i++) {
            hasil[i] = array1[i] * array2[i];
        }
        
        // Menampilkan hasil perkalian
        System.out.println("\nHasil perkalian elemen-elemen array1 dan array2:");
        for (int i = 0; i < n1; i++) {
            System.out.println("Hasil perkalian array1[" + i + "] * array2[" + i + "] = " + hasil[i]);
        }

        input.close(); // Menutup Scanner
    }
}
    
    

