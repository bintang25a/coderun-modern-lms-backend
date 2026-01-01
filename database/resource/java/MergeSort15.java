public class MergeSortsStatis {
    public static void main(String[] args) {
        int[] postalCodes = {10210, 10220, 10230, 10240, 10250, 10260, 10270};
        String[] subdistricts = {
                "Bendungan Hilir",
                "Karet Tengsin",
                "Kebon Melati",
                "Kebon Kacang",
                "Kampung Bali",
                "Petamburan",
                "Gelora"
        };

        System.out.println("Data sebelum diurutkan:");
        displayData(postalCodes, subdistricts);

        mergeSort(postalCodes, subdistricts, 0, postalCodes.length - 1);

        System.out.println("\nData setelah diurutkan:");
        displayData(postalCodes, subdistricts);
    }

    public static void mergeSort(int[] codes, String[] names, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(codes, names, left, mid);
            mergeSort(codes, names, mid + 1, right);

            merge(codes, names, left, mid, right);
        }
    }

    public static void merge(int[] codes, String[] names, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftCodes = new int[n1];
        int[] rightCodes = new int[n2];
        String[] leftNames = new String[n1];
        String[] rightNames = new String[n2];

        for (int i = 0; i < n1; i++) {
            leftCodes[i] = codes[left + i];
            leftNames[i] = names[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightCodes[i] = codes[mid + 1 + i];
            rightNames[i] = names[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftCodes[i] <= rightCodes[j]) {
                codes[k] = leftCodes[i];
                names[k] = leftNames[i];
                i++;
            } else {
                codes[k] = rightCodes[j];
                names[k] = rightNames[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            codes[k] = leftCodes[i];
            names[k] = leftNames[i];
            i++;
            k++;
        }
        while (j < n2) {
            codes[k] = rightCodes[j];
            names[k] = rightNames[j];
            j++;
            k++;
        }
    }

    public static void displayData(int[] codes, String[] names) {
        for (int i = 0; i < codes.length; i++) {
            System.out.println("Kode Pos: " + codes[i] + ", Kelurahan: " + names[i]);
        }
    }
}