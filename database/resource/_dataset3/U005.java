public class U005 {

    // Metode untuk mencetak array
    public static void cetakArray(int[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

    // Metode untuk menggabungkan dua bagian array
    public static void merge(int[] A, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1]; // Array sementara untuk bagian kiri
        int[] R = new int[n2]; // Array sementara untuk bagian kanan

        // Menyalin data ke array sementara
        for (int i = 0; i < n1; i++) L[i] = A[left + i];
        for (int j = 0; j < n2; j++) R[j] = A[mid + 1 + j];

        // Menggabungkan kedua bagian
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // Menyalin elemen yang tersisa, jika ada
        while (i < n1) {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            A[k] = R[j];
            j++;
            k++;
        }
    }

    // Metode rekursif untuk membagi array
    public static void mergeSort(int[] A, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(A, left, mid); // Urutkan bagian kiri
            mergeSort(A, mid + 1, right); // Urutkan bagian kanan

            merge(A, left, mid, right); // Gabungkan kedua bagian
        }
    }

    public static void main(String[] args) {
        int[] myArray = {6, 4, 2, 1, 8};

        System.out.print("Array acak = ");
        cetakArray(myArray);  // Menampilkan array yang belum diurutkan

        mergeSort(myArray, 0, myArray.length - 1);  // Melakukan merge sort

        System.out.print("Array urut = ");
        cetakArray(myArray);  // Menampilkan array setelah diurutkan
    }
}
