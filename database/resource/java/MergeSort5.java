import java.util.Scanner;
public class Mergedinamis {

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1; // Panjang subarray kiri
        int n2 = right - mid;    // Panjang subarray kanan

        // Buat subarray sementara
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Salin data ke subarray sementara
        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

        // Gabungkan subarray
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Rekursif untuk membagi array
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Gabungkan subarray yang telah diurutkan
            merge(array, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Array acak:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        mergeSort(array, 0, array.length - 1);

        System.out.println("Array urut:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}

