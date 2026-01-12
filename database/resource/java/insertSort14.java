import java.util.Scanner;
public class TugasPertemuan1 {

 
    public static void main(String[] args) {
 
        Scanner input = new Scanner(System.in);

        // Input jumlah elemen array
        System.out.print("Masukan Banyak Angka : ");
        int n = input.nextInt();

        int[] data = new int[n];

        // Input nilai elemen array
        for (int i = 0; i < n; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        // Tampilkan sebelum sorting
        System.out.print("\nSebelum Sorting :\n[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println("]");

        // Proses Insertion Sort (descending)
        for (int i = 0; i < n; i++) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] < key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;

            System.out.println("Langkah Ke-" + (i + 1) + " -->" + key + " ditukar dengan " + data[j + 1]);
        }

        // Tampilkan sesudah sorting
        System.out.print("\nSesudah Sorting :\n[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println("]");
    }
}
    

   
