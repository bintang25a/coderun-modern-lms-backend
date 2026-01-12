public class deskriptif statik {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Program Statistika Deskriptif\n");

        System.out.print("Input jumlah data = ");
        int n = input.nextInt();

        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input data " + (i + 1) + " = ");
            data[i] = input.nextInt();
        }

        // Tampilkan data belum terurut
        System.out.print("\n[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("] <--- Data Belum Terurut");

        // Sorting (Bubble Sort)
        bubbleSort(data);

        // Tampilkan data terurut
        System.out.print("\n[");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("] <--- Data Terurut\n");

        // Hitung Statistik
        double rataRata = hitungRataRata(data);
        double median = hitungMedian(data);
        int max = data[n - 1];
        int min = data[0];
        int range = max - min;

        // Output hasil
        System.out.println("Rata - Rata    = " + rataRata);
        System.out.println("Median         = " + median);
        System.out.println("Nilai Max      = " + max);
        System.out.println("Nilai Min      = " + min);
        System.out.println("Range          = " + range);
    }

    // ================= SORTING =================
    static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    // ================= RATA-RATA =================
    static double hitungRataRata(int[] data) {
        int total = 0;
        for (int i : data) {
            total += i;
        }
        return (double) total / data.length;
    }

    // ================= MEDIAN =================
    static double hitungMedian(int[] data) {
        int n = data.length;
        if (n % 2 == 1) {
            return data[n / 2]; // ganjil
        } else {
            return (data[n / 2 - 1] + data[n / 2]) / 2.0; // genap
        }
    }
}

    
