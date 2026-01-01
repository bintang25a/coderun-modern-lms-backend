import java.util.ArrayList;
import java.util.Scanner;

public class InsertionSortDinamis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> postalCodes = new ArrayList<>();
        ArrayList<String> subdistricts = new ArrayList<>();
        
        System.out.println("Masukkan jumlah data: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline
        
        for (int i = 0; i < n; i++) {
            System.out.println("Masukkan kode pos untuk data ke-" + (i + 1) + ": ");
            int code = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline
            System.out.println("Masukkan nama kelurahan untuk kode pos " + code + ": ");
            String subdistrict = scanner.nextLine();
            
            postalCodes.add(code);
            subdistricts.add(subdistrict);
        }
        
        System.out.println("\nData sebelum diurutkan:");
        displayData(postalCodes, subdistricts);
        
        insertionSort(postalCodes, subdistricts);
        
        System.out.println("\nData setelah diurutkan:");
        displayData(postalCodes, subdistricts);
    }
    
    public static void insertionSort(ArrayList<Integer> codes, ArrayList<String> names) {
        for (int i = 1; i < codes.size(); i++) {
            int key = codes.get(i);
            String keyName = names.get(i);
            int j = i - 1;
            
            while (j >= 0 && codes.get(j) > key) {
                codes.set(j + 1, codes.get(j));
                names.set(j + 1, names.get(j));
                j--;
            }
            
            codes.set(j + 1, key);
            names.set(j + 1, keyName);
        }
    }
    
    public static void displayData(ArrayList<Integer> codes, ArrayList<String> names) {
        for (int i = 0; i < codes.size(); i++) {
            System.out.println("Kode Pos: " + codes.get(i) + ", Kelurahan: " + names.get(i));
        }
    }
}
