public class insertionsort {

   
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 3, 5};
        
        System.out.println("Array sebelum diurutkan:");
        printArray(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    System.out.println("Iterasi ke-" + (i + 1) + ": " + arr[j] + " Ditukar dengan " + arr[j + 1]);

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println("\nArray setelah diurutkan:");
        printArray(arr);
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
        
    }
   
