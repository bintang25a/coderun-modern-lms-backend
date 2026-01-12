
import java.util.Scanner;


public class mergeSort36 {

    
    public static void merge(int[] arr, int l, int m, int r){
        int n1 = m - l + 1; 
        int n2 = r - m;     

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Menyalin data ke array sementara
        for (int i = 0; i < n1; i++){
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        System.out.println("-> Menggabungkan (Merge):");
        System.out.print("Kiri: [");
        for (int val : L) { System.out.print(val + " "); }
        System.out.println("]");
        System.out.print("Kanan: [");
        for (int val : R) { System.out.print(val + " "); }
        System.out.println("]");
        
        int i = 0, j = 0; 
        int k = l;        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;} 
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
       while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
       while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
       System.out.print("Hasil Merge: [");
        for (int idx = l; idx <= r; idx++) {
            System.out.print(arr[idx] + (idx == r ? "" : ", "));
        }
        System.out.println("]");
        // ---------------------------------------------
    }
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            // Menentukan titik tengah
            int m = l + (r - l) / 2;
            
            // --- Tambahan: Tampilkan proses pembagian ---
            System.out.print("Membagi (Divide): [");
            for (int i = l; i <= r; i++) {
                System.out.print(arr[i] + (i == r ? "" : ", "));
            }
            System.out.println("]");
            sort(arr, l, m);
            sort(arr, m + 1, r);
            
            // Menggabungkan hasil
            merge(arr, l, m, r);
        } else {
            // --- Tambahan: Tampilkan Base Case ---
            System.out.print("Base Case (Terurut): [");
            System.out.print(arr[l]);
            System.out.println("]");
            // -----------------------------------
        }
    }
    
    // Fungsi untuk mencetak array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    // Fungsi utama
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int [] arr;
        int bil;
        
        System.out.print("Masukkan Angka : ");  
        bil=input.nextInt();
        System.out.println();
       
        arr=new int[bil];
        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-"+(i+1)+" = ");
            arr[i]=input.nextInt();
        }
        
        System.out.println("\nArray Belum Terurut : ");
        for(int i:arr){
            System.out.print(i+" ");
        }        
        
        System.out.println("\n--- Memulai Proses Merge Sort ---");
        // Memanggil fungsi sort
        sort(arr, 0, arr.length - 1);
        
        System.out.println("\n--- Proses Selesai ---");
        System.out.println("Array terurut :");
        printArray(arr);
      
    }
    
}
