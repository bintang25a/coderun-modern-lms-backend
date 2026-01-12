public class WaduhLupaSholat {

    public static void main(String[] args) {
        int[] rakaat = {12, 104, 14, 23, 34};

        niatSholat(rakaat);

        limaWaktu(rakaat);

        niatSholat(rakaat);
    }

    public static void niatSholat(int[] dibaca) {
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.print(dibaca[wajib] + " ");
        }
        System.out.println();
    }
–
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
