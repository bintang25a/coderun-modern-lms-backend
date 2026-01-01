import java.util.Scanner;

public class CountingSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input jumlah elemen
        System.out.print("Masukkan Array : ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // Input elemen array
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        // Tampilkan array awal
        System.out.print("Array Acak: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Proses Counting Sort
        countingSort(arr);
    }

    public static void countingSort(int[] arr) {
        int max = findMax(arr);
        int[] count = new int[max + 1];

        // Inisialisasi array count
        System.out.print("Proses Counting Sort:");
        for (int c : count) {
            System.out.print(" " + c);
        }
        System.out.println();

        // Hitung frekuensi setiap elemen
        for (int num : arr) {
            count[num]++;
        }

        // Tampilkan proses penghitungan
        for (int i = 0; i < count.length; i++) {
            System.out.println("Elemen " + i + " --->" + printArray(count));
        }

        // Modifikasi array count
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Array sementara untuk hasil
        int[] output = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Menempatkan elemen ke array hasil
        System.out.print("Array Elemen : ");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();

        // Cetak elemen satu per satu ke dalam array terurut
        System.out.println("Array Terurut:");
        for (int num : output) {
            System.out.print(num + " ");
        }
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static String printArray(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}