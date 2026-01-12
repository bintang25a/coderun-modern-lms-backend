import java.util.Scanner; 

public class waduhLupaSholat {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            System.out.println("\nProgram Sorting Raihan Abi Nugroho 24040700056");
         
            System.out.print("Masukkan Jumlah Data Angka: ");
            int n = input.nextInt();
         
            int[] rakaat = new int[n];
   
            for (int i = 0; i < n; i++) {
                System.out.print("Masukkan Data ke-" + (i + 1) + ": ");
                rakaat[i] = input.nextInt();
            }   System.out.println("\nData Sebelum Diurutkan");
            niatSholat(rakaat);
        
            limaWaktu(rakaat);
            System.out.println("\nData Setelah Diurutkan");
            niatSholat(rakaat); 
}


    public static void niatSholat(int[] dibaca) {
        for (int wajib = 0; wajib < dibaca.length; ++wajib) {
            System.out.print(dibaca[wajib] + " ");
        }
        System.out.println(); 
    }


    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int rukun = takbir - 1;
            int islam = salam[takbir]; 

            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
    }
}
