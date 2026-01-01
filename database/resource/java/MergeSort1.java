import java.util.ArrayList;
import java.util.Scanner;
public class arraydinamis {

    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> array = new ArrayList<>();

        System.out.print("Masukkan jumlah elemen: ");
        int n = scanner.nextInt();

        System.out.println("Masukkan elemen:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            array.add(scanner.nextInt());
        }

        System.out.println("Array sebelum diurutkan:");
        printArray(array);

     
        array = mergeSort(array);

        System.out.println("Array setelah diurutkan:");
        printArray(array);

        scanner.close();
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> array) {
        if (array.size() <= 1) {
            return array;
        }

        int mid = array.size() / 2;

    dua bagian
        ArrayList<Integer> left = new ArrayList<>(array.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(array.subList(mid, array.size()));

     
        return merge(mergeSort(left), mergeSort(right));
    }

    public static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // Menyalin sisa elemen
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }

    public static void printArray(ArrayList<Integer> array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}