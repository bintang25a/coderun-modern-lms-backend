import java.util.Arrays;
import java.util.Scanner;

public class Heapmax {

    public static int getleftchildindex(int i) {
        return 2 * i + 1;
    }

    public static int getrightchildindex(int i){
        return 2 * i + 2; // perbaikan dari versi sebelumnya
    }

    public static void heapify(int[] array, int size, int i){
        int largest = i;
        int left = getleftchildindex(i);
        int right = getrightchildindex(i);

        if(left < size && array[left] > array[largest]){
            largest = left;
        }

        if(right < size && array[right] > array[largest]){
            largest = right;
        }

        if(largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest);
        }
    }

    public static void buildmaxheap(int[] array){
        int size = array.length;
        for(int i = size / 2 - 1; i >= 0; i--){
            heapify(array, size, i);
        }
    }

    public static void heapsort(int[] array){
        int size = array.length;
        buildmaxheap(array);
        for (int i = size - 1; i > 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");
        System.out.print("Masukkan jumlah angka yang ingin diurutkan: ");
        int n = sc.nextInt();
        sc.nextLine(); // bersihkan newline

        int[] array = new int[n];

        System.out.println("Masukkan " + n + " angka:");
        for(int i = 0; i < n; i++){
            System.out.print("Angka ke-" + (i+1) + ": ");
            array[i] = sc.nextInt();
        }

        System.out.println("\n--------------------------------------");
        System.out.println("Array awal: " + Arrays.toString(array));

        buildmaxheap(array);
        System.out.println("Setelah jadi Max-Heap: " + Arrays.toString(array));

        heapsort(array);
        System.out.println("Hasil akhir (sorted): " + Arrays.toString(array));
        System.out.println("--------------------------------------");
    }
}
