import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort_dinamis {

    public static void mergeSort(ArrayList<Integer> list) {
        if (list.size() < 2) {
            return; 
        }

        int mid = list.size() / 2;

        ArrayList<Integer> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    private static void merge(ArrayList<Integer> list, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Masukkan jumlah array:");
        int n = scanner.nextInt();

        System.out.println("Masukkan elemen array :");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        mergeSort(list);

        System.out.println("Array setelah diurutkan:");
        System.out.println(list);

        scanner.close();
    }
}