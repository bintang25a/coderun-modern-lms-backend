public class exam53 {

    public static void main(String[] array) {
        int[] rakaat = {12, 104, 14, 23, 34};

        limaWaktu(rakaat);
        niatSholat(rakaat);

    }

    public static void niatSholat(int[] dibaca) {
        for (int wajib = 0; wajib < dibaca.length; wajib++) {
            System.out.println(dibaca[wajib] + " ");
        }
    }

    public static void limaWaktu(int[] salam) {
        for (int takbir = 1; takbir < salam.length; takbir++) {
            int rukun;
            int islam;

            rukun = takbir - 1;
            islam = salam[takbir];

            while (rukun >= 0 && salam[rukun] > islam) {
                salam[rukun + 1] = salam[rukun];
                rukun--;
            }
            salam[rukun + 1] = islam;
        }
        System.out.print("==== No 1 Muhamad Ramzy Pradipta (24040700054) ====\n");
    }
}

