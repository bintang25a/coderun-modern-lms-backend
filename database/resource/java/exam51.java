import java.util.Scanner; 

public class exam51 {
    
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)){
            System.out.println("====================================");
            System.out.println("Program Sorting Zoelkiflie Ahmadhani");
            System.out.println("====================================");
         
            System.out.print("Masukkan jumlah array: ");
            int n = input.nextInt();
            int[] rakaat = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Masukkan array ke-" + (i + 1) + ": ");
                rakaat[i] = input.nextInt();
            }   System.out.println("\n[Array Sebelum Diurutkan]");
            niatSholat(rakaat);
        
            limaWaktu(rakaat);
            System.out.println("\n[Array Setelah Diurutkan]");
            niatSholat(rakaat);
       
        }
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