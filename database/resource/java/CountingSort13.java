import java.util.Arrays;
public class Tgscountingsort {
    
    public static void countingSort(int[] arr) {
        System.out.println("Input Array: " + Arrays.toString(arr));

        int max = Arrays.stream(arr).max().orElseThrow();
        int[] count = new int[max + 1];
        
        for (int num : arr) {
            count[num]++;
        }
        System.out.println("Step 1 - Count Array:");
        printArray(count);

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Step 2 - Prefix Sum Array:");
        printArray(count);

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        System.out.println("Sorted Array:");
        printArray(output);
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 2, 8};
        countingSort(arr);
    }
}
