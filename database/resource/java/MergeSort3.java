import java.util.ArrayList;
import java.util.Collections;

public class MergeSortDinamis {
    public static void mergeSort(ArrayList<Integer> list, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Sort bagian kiri
            mergeSort(list, left, middle);
            // Sort bagian kanan
            mergeSort(list, middle + 1, right);
            // Gabungkan
            merge(list, left, middle, right);
        }
    }

    private static void merge(ArrayList<Integer> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        // Copy data ke sublist
        for (int i = 0; i < n1; i++)
            leftList.add(list.get(left + i));
        for (int j = 0; j < n2; j++)
            rightList.add(list.get(middle + 1 + j));

        int i = 0, j = 0, k = left;

        // Gabungkan sublist
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i) <= rightList.get(j)) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        // Copy elemen sisa
        while (i < leftList.size()) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightList.size()) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<>();
        Collections.addAll(data, 12, 11, 13, 5, 6, 7);

        System.out.println("ArrayList sebelum disortir: " + data);

        mergeSort(data, 0, data.size() - 1);

        System.out.println("ArrayList setelah disortir: " + data);
    }
}
