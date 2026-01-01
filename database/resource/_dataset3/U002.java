public class U002 {

  // Metode untuk mencetak elemen array 2D
  public static void cetakArray2D(String[][] B) {
      System.out.println("--- Array 2D (Alokasi & Pengisian Manual) ---");
      // Menggunakan enhanced for loop (For-Each) untuk baris (array 1D)
      for (String[] baris : B) {
          // Menggunakan enhanced for loop (For-Each) untuk kolom (elemen)
          for (String elemen : baris) {
              System.out.print(elemen + "\t");
          }
          System.out.println();
      }
  }

  public static void main(String[] args) {
      int baris = 2;
      int kolom = 4;
      
      // Deklarasi array 2D tipe String dengan alokasi memori
      String[][] dataSiswa = new String[baris][kolom]; 
      
      // Pengisian nilai secara manual
      dataSiswa[0][0] = "Nama";
      dataSiswa[0][1] = "Kelas";
      dataSiswa[0][2] = "Nilai";
      dataSiswa[0][3] = "Status";

      dataSiswa[1][0] = "Andi";
      dataSiswa[1][1] = "XII IPA";
      dataSiswa[1][2] = "85";
      dataSiswa[1][3] = "Lulus";
      
      cetakArray2D(dataSiswa);
  }
}