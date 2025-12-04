public class U001 {

  // Metode untuk mencetak elemen array 2D
  public static void cetakArray2D(int[][] A) {
      System.out.println("--- Array 2D (Inisialisasi Langsung) ---");
      // Loop untuk baris
      for (int i = 0; i < A.length; i++) {
          // Loop untuk kolom
          for (int j = 0; j < A[i].length; j++) {
              System.out.print(A[i][j] + "\t"); // \t untuk tabulasi
          }
          System.out.println(); // Pindah baris
      }
  }

  public static void main(String[] args) {
      // Deklarasi dan inisialisasi array 2D (3x3)
      int[][] matriksA = {
          {10, 20, 30},
          {40, 50, 60},
          {70, 80, 90}
      };
      
      cetakArray2D(matriksA);
  }
}