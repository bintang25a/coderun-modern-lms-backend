import java.util.Arrays;
        
        
public class CountingSort {
    public static void countingSort(int[] array) {
        int max = Arrays.stream(array).max().orElse(0);
        
        int[] count = new int [max + 1];
        
        
        System.out.println("Proses menghitung frekuensi elemen:");
        for (int num : array) {
            count[num]++;
            System.out.println("Frekuensi elemen " + ": " + Arrays.toString(count));
            
        }
        
        System.out.println("\nProses menyusun elemen ke dalam array hasil:");
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[array[i]] - 1] = array[i];
            count[array[i]]--;
            System.out.println("Array hasil sementara: " + Arrays.toString(sortedArray));
            
        }

        
        System.arraycopy(sortedArray, 0, array, 0, array.length);
        

    }
    
    public static void main(String[] args) {
        int[] array = {5, 6, 3, 3, 4, 2, 2};
        System.out.println("Array sebelum diurutkan: " + Arrays.toString(array));
        
        countingSort(array);
        
        System.out.println("\nArray setelah diurutkan: " + Arrays.toString(array));
    }
    
}
