public class perkalianMetriks {

public static int[][] perkalianMetriks(int[][] A, int B[][]) throws Exception {
    if(A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            throw new Exception("Matriks A atau B tidak boleh kosong!");
        } else if (A[0].length != B.length) {
            throw new Exception("A dan B tidak bisa dikalikan karena dimensi tidak cocok");
        }

        int[][] C = new int[A.length][B[0].length]; 
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                C[i][j] = 0;
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

  public static void cetakArray2D(int[][] A) {
        for (int i = 0; i < A.length; i++) { // Baris
            for (int j = 0; j < A[i].length; j++) { // Kolom
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        int A[][] = {{4, 3, 1}, {2, 6, 5}};
        int B[][] = {{2, 5, 4, 6}, {8, 2, 7, 0}, {7, 1, 3, 9}};
        
        cetakArray2D(A);
        System.out.println(" ");
        cetakArray2D(B);
        System.out.println(" ");

        try {
        int C[][] = perkalianMetriks(A, B);
        cetakArray2D(C);
        
    } 
        catch (Exception ex) {
        System.err.println(ex.toString());
        }
    }
}