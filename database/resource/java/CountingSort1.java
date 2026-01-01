import java.util.Arrays;
public class Tugas2 {

        
       public static void countingSort(int[] arr) {
        System.out.println("Array input: " + Arrays.toString(arr));

        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        
        for (int num : arr) {
            count[num]++;
        }
        System.out.println("Count array (frekuensi): " + Arrays.toString(count));

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Count array (kumulatif): " + Arrays.toString(count));

        int[] output = new int[arr.length];
 
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            output[count[num] - 1] = num; 
            count[num]--; 
            System.out.println("Iterasi setelah menempatkan " + num + ": " + Arrays.toString(output));
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
        System.out.println("Array hasil akhir: " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
      
        int[] arr = {9, 8, 7, 5, 2, 3};
 
        countingSort(arr);
    }
}
    
    

