public class U003 {

  // Metode untuk mencetak elemen array 2D (Jagged Array)
  public static void cetakArrayJagged(char[][] C) {
      System.out.println("--- Array Jagged (Kolom Berbeda) ---");
      // Loop standar untuk iterasi
      for (int i = 0; i < C.length; i++) {
          System.out.print("Baris " + i + ": ");
          for (int j = 0; j < C[i].length; j++) {
              System.out.print(C[i][j] + " ");
          }
          System.out.println();
      }
  }

  public static void main(String[] args) {
      // Alokasi memori hanya untuk 3 baris
      char[][] karakter = new char[3][]; 
      
      // Alokasi memori untuk kolom secara terpisah
      karakter[0] = new char[5]; // Baris 0 punya 5 kolom
      karakter[1] = new char[2]; // Baris 1 punya 2 kolom
      karakter[2] = new char[4]; // Baris 2 punya 4 kolom

      // Pengisian nilai (hanya contoh sebagian)
      karakter[0][0] = 'H';
      karakter[0][1] = 'E';
      karakter[0][2] = 'L';
      karakter[0][3] = 'L';
      karakter[0][4] = 'O';

      karakter[1][0] = 'J';
      karakter[1][1] = 'A';

      karakter[2][0] = 'V';
      karakter[2][1] = 'A';
      karakter[2][2] = '2';
      karakter[2][3] = 'D';

      cetakArrayJagged(karakter);
  }
}