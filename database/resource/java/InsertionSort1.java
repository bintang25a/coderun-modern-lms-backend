import java.util.ArrayList;
import java.util.List;

public class InsertionSortDynamic {
    // Fungsi untuk mengurutkan list menggunakan Insertion Sort
    void insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // Pindahkan elemen list[0..i-1], yang lebih besar dari key, ke satu posisi di depan posisi sekarang
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(11);
        list.add(13);
        list.add(5);
        list.add(6);

        InsertionSortDynamic ob = new InsertionSortDynamic();
        ob.insertionSort(list);

        System.out.println("Sorted list: " + list);
    }
}
