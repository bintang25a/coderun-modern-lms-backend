public class WaduhLupaSholat {

    public static void dynafibonacci(String[] args) {

        int[] rakaat = {2, 4, 4, 3, 4};

        System.out.println("Data awal:");
        niatSholat(rakaat);

        limaWaktu(rakaat);

        System.out.println("\nSetelah diurutkan:");
        niatSholat(rakaat);
    }
    public static void niatSholat(int[] dibaca) {
        for (int i = 0; i < dibaca.length; i++) {
            System.out.print(dibaca[i] + " ");
        }
        System.out.println();
    }
    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int islam = salam[takbir];
            int rukun = takbir - 1;

            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
    }
}
