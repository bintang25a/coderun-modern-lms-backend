

import java.util.Scanner;

public class MergeSort {

       // Fungsi untuk menggabungkan dua subarray yang sudah terurut
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
        System.out.println("Langkah Merge: Subarray kiri [" + arrayToString(L) + "], subarray kanan [" + arrayToString(R) + "]");
        // Menggabungkan subarray sementara ke array asli
        int i = 0, j = 0; // Indeks awal untuk L dan R
        int k = l;        // Indeks awal untuk array gabungan
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                System.out.println("  Mengambil " + L[i] + " dari kiri");
                arr[k] = L[i];
                i++;
            } else {
                System.out.println("  Mengambil " + R[j] + " dari kanan");
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // Menyalin elemen sisa di L jika ada
        while (i < n1) {
            System.out.println("  Menyalin sisa " + L[i] + " dari kiri");
            arr[k] = L[i];
            i++;
            k++;
        }
        // Menyalin elemen sisa di R jika ada
        while (j < n2) {
            System.out.println("  Menyalin sisa " + R[j] + " dari kanan");
            arr[k] = R[j];
            j++;
            k++;
        }
        System.out.println("Hasil setelah merge: [" + arrayToString(arr, l, r) + "]");
        System.out.println("Array keseluruhan saat ini: [" + arrayToString(arr) + "]\n");
    }
    // Fungsi untuk membagi array, lalu memanggil merge
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            // Menentukan titik tengah
            int m = l + (r - l) / 2;
            System.out.println("Langkah Sort: Membagi array [" + arrayToString(arr, l, r) + "] pada indeks " + m + " (nilai " + arr[m] + ")");
            System.out.println("  Bagian kiri: [" + arrayToString(arr, l, m) + "]");
            System.out.println("  Bagian kanan: [" + arrayToString(arr, m + 1, r) + "]\n");
            // Membagi menjadi dua bagian, lalu mengurutkan
            sort(arr, l, m);
            sort(arr, m + 1, r);
            
// Menggabungkan hasil
            merge(arr, l, m, r);
        } else {
            System.out.println("Subarray [" + arrayToString(arr, l, r) + "] sudah terurut (ukuran 1)\n");
        }
    }
    // Fungsi untuk mencetak array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    // Fungsi helper untuk mengonversi array ke string (untuk subarray)
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
    // Fungsi helper untuk mengonversi subarray (dari l ke r) ke string
    public static String arrayToString(int[] arr, int l, int r) {
        StringBuilder sb = new StringBuilder();
        for (int i = l; i <= r; i++) {
            sb.append(arr[i]);
            if (i < r) sb.append(", ");
        }
        return sb.toString();
    }
    
// Fungsi utama
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr;
        int bil;
        System.out.print("Masukkan Angka : ");
        bil = input.nextInt();
        System.out.println();
        arr = new int[bil];
        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-" + (i + 1) + " = ");
            arr[i] = input.nextInt();
        }
        System.out.println("\nArray Belum Terurut : ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("\n\nProses pengurutan Merge Sort:\n");
        // Memanggil fungsi sort
        sort(arr, 0, arr.length - 1);
        System.out.println("Array terurut :");
        printArray(arr);
    }
}
