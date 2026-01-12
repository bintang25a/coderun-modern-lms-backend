/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class heapAlgorithm13 {
    public static int getParentIndex(int i) {
        return (i-1)/2;
    }

    public static int getLeftChildIndex(int i) {
        return 2*i+1;
    }

    public static int getRightChildIndex(int i) {
        return 2*i+2;
    }

    public static void heapify(int[] array, int size, int i) {
        int largest=i;
        int left= getLeftChildIndex(i);
        int right= getRightChildIndex(i);
        if (left < size && array[left] > array[largest]) {
            largest =left;
        }
        if (right < size && array[right] > array[largest]) {
            largest =right;
        }
        if (largest != i) {
            int temp= array[i];
            array[i]= array[largest];
            array[largest]= temp;
            heapify(array,size, largest);
        }
    }

    public static void buildMaxHeap(int[] array) {
        int size= array.length;
        for(int i=size/2-1; i>=0; i--) {
            heapify(array, size,i);
        }
    }

    public static void heapSort(int[] array) {
        int size= array.length;
        buildMaxHeap(array);
        for(int i=size-1; i>0; i--){
            int temp= array[0];
            array[0]= array[i];
            array[i]= temp;
            heapify(array, i, 0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== PROGRAM HEAP SORT DINAMIS ===");
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan jumlah elemen array: ");
        int n = input.nextInt();
        int[] array = new int[n];
        System.out.println("Masukkan elemen:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i+1) + ": ");
            array[i] = input.nextInt();
        }
        System.out.println("Array sebelum di-sorting dengan Algoritma Heap:");
        System.out.println(Arrays.toString(array));
        buildMaxHeap(array);
        System.out.println("Max-Heap:");
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println("Array setelah di-sorting dengan Algoritma Heap (Ascending):");
        System.out.println(Arrays.toString(array));
    }
}