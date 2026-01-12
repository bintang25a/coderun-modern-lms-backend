import java.util.Scanner;

public class Merge_sort {
    public static void merge(int[] arr, int l, int m, int r) {
        System.out.println("\n--- Proses Penggabungan (Merge) ---");
        System.out.println("Sub-Array Kiri (L): [" + subArrayToString(arr, l, m) + "]");
        System.out.println("Sub-Array Kanan (R): [" + subArrayToString(arr, m + 1, r) + "]");

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            System.out.print("Membandingkan L[" + i + "]=" + L[i] + " dengan R[" + j + "]=" + R[j] + ". ");

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
                System.out.println(L[i - 1] + " dimasukkan. Array sementara: [" + subArrayToString(arr, l, k) + "]");
            } else {
                arr[k] = R[j];
                j++;
                System.out.println(R[j - 1] + " dimasukkan. Array sementara: [" + subArrayToString(arr, l, k) + "]");
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            System.out.println("Menyalin sisa L[" + (i-1) + "]=" + L[i-1] + ". Array sementara: [" + subArrayToString(arr, l, k - 1) + "]");
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            System.out.println("Menyalin sisa R[" + (j-1) + "]=" + R[j-1] + ". Array sementara: [" + subArrayToString(arr, l, k - 1) + "]");
        }

        System.out.println("Hasil Merge pada indeks " + l + " sampai " + r + ": [" + subArrayToString(arr, l, r) + "]");
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            System.out.println("\n--- Proses Pembagian (Divide) ---");
            System.out.println("Membagi array dari indeks " + l + " hingga " + r + ": [" + subArrayToString(arr, l, r) + "]");

            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static String subArrayToString(int[] arr, int start, int end) {
        if (start > end) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);

        int [] arr;
        int bil;
        System.out.print("Masukkan Jumlah Angka (N) : ");
        bil=input.nextInt();
        System.out.println();

        arr=new int[bil];
        for (int i = 0; i < bil; i++) {
            System.out.print("Masukan Data ke-"+(i+1)+" = ");
            arr[i]=input.nextInt();
        }

        System.out.println("\n==================================");
        System.out.println("Array Belum Terurut : ");
        printArray(arr);
        System.out.println("==================================");

        sort(arr, 0, arr.length - 1);

        System.out.println("\n==================================");
        System.out.println("Array terurut (Hasil Akhir) :");
        printArray(arr);
        System.out.println("==================================");

        input.close();
    }
}