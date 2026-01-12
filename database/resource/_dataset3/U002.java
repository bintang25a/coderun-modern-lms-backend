/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Adam
 */
public class U002 {

    //3 Fungsi untuk menggabungkan dua subarray yang sudah terurut
    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // Panjang subarray kiri
        int n2 = r - m;     // Panjang subarray kanan

        // Membuat array sementara untuk subarray kiri dan kanan
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Menyalin data ke array sementara
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        
        // Tampilkan proses Merge
        System.out.println("-> Menggabungkan (Merge):");
        System.out.println("   Kiri: " + Arrays.toString(L));
        System.out.println("   Kanan: " + Arrays.toString(R));

        // Menggabungkan subarray sementara ke array asli
        int i = 0, j = 0; // Indeks awal untuk L dan R
        int k = l;        // Indeks awal untuk array gabungan
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // Menyalin elemen sisa di L jika ada
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // Menyalin elemen sisa di R jika ada
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
        
        // Cetak hasil merge
        int[] hasil = Arrays.copyOfRange(arr, l, r + 1);
        System.out.println("   Hasil Merge: " + Arrays.toString(hasil));
    }
    // Fungsi untuk membagi array, lalu memanggil merge
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            // Menentukan titik tengah
            int m = l + (r - l) / 2;
            
            // Tampilkan proses Divide
            System.out.print("Membagi (Divide): [");
            for (int i = l; i <= r; i++) {
                System.out.print(arr[i] + (i < r ? ", " : ""));
            }
            System.out.println("]");
            
            // Membagi menjadi dua bagian, lalu mengurutkan
            sort(arr, l, m);
            sort(arr, m + 1, r);
            // Menggabungkan hasil
            merge(arr, l, m, r);
            
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
        //int[] arr = {5, 2, 3, 1, 4, 8, 9};
        int [] arr;
        int bil;
        System.out.print("Masukkan Angka : ");  bil=input.nextInt();
        System.out.println();
       /* System.out.println("Array semula:");
        printArray(arr); */
       arr=new int[bil];
        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-"+(i+1)+" = ");
            arr[i]=input.nextInt();
        }
        System.out.println("\nArray Belum Terurut : ");
        for(int i:arr){
            System.out.print(i+" ");
        }        
        // Memanggil fungsi sort
        sort(arr, 0, arr.length - 1);
        System.out.println("\nArray terurut :");
        printArray(arr);
    }
    
}
