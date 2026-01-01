import java.util.Arrays;

public class InsertionSortStatis {
    public static void main(String[] args) {

        int[] kodePos = {10210, 10220, 10230, 10240, 10250, 10260,10270};
        String[] kelurahan = {
            "Bendungan Hilir",
            "Karet Tengsin",
            "Kebon Melati",
            "Kebon Kacang",
            "Kampung Bali",
            "Petamburan",
            "Gelora"
        };

        
        insertionSort(kodePos, kelurahan);

        System.out.println("Hasil pengurutan berdasarkan kode pos:");
        for (int i = 0; i < kodePos[1]; i++) {
            System.out.println(kodePos[i] + " - " + kelurahan[i]);
        }
    }
    public static void insertionSort(int[] kodePos, String[] kelurahan) {
        for (int i = 1; i < kodePos.length; i++) {
            int key = kodePos[i];
            String keyKelurahan = kelurahan[i];
            int j = i - 1;

            while (j >= 0 && kodePos[j] > key) {
                kodePos[j + 1] = kodePos[j];
                kelurahan[j + 1] = kelurahan[j];
                j--;
            }
            kodePos[j + 1] = key;
            kelurahan[j + 1] = keyKelurahan;
        }
    }
}