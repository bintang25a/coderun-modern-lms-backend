import java.util.Scanner;

public class exam30 {

    static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah data (HARUS GENAP): ");
        int n = input.nextInt();

        // Validasi genap
        if (n <= 0 || n % 2 != 0) {
            System.out.println("Error: Jumlah data harus bilangan GENAP dan > 0.");
            return;
        }

        double[] data = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Data ke-" + (i + 1) + ": ");
            data[i] = input.nextDouble();
        }

        // Sorting dengan Merge Sort
        mergeSort(data, 0, n - 1);

        // Rata-rata
        double total = 0;
        for (double d : data)
            total += d;
        double rataRata = total / n;

        // Median
        double median;
        if (n % 2 == 1)
            median = data[n / 2];
        else
            median = (data[n / 2 - 1] + data[n / 2]) / 2;

        // Min, Max, Jarak
        double min = data[0];
        double max = data[n - 1];
        double jarak = max - min;

        // Output
        System.out.println("\nData setelah diurutkan:");
        for (double d : data)
            System.out.print(d + " ");

        System.out.println("\n\nRata-rata          : " + rataRata);
        System.out.println("Median             : " + median);
        System.out.println("Nilai Minimum      : " + min);
        System.out.println("Nilai Maximum      : " + max);
        System.out.println("Jarak Min dan Max  : " + jarak);

        input.close();
    }
}
