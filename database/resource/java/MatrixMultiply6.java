package perkalianmatriks;
public class PerkalianMatriks {
    public static int[][] perkalianMatriks(int[][] A, int B[][]) throws Exception {
        if (A.length==0 || B.length==0 || A[0].length==0 || B[0].length==0) {
            throw new Exception("matriks A atau B tidak boleh kosong!");
        }
        else if (A[0].length != B.length){
            throw new Exception ("A dan B tidak bisa dikalikan karena dimensi tidakk cocok");
        }
        
        int [][] C = new int[A.length][B[0].length]; //deklarasi matriks baru sebagai tempat penyimapanan hasil perkalian
        
        //program untuk menghitung perkalian matriks
        for (int i=0; i<A.length; i++){
            for (int j=0; j<B[i].length; j++) {
                C[i][j]=0;
                for (int k=0; k<A[i].length; k++){
                    C[i][j] += A[i][k]*B[k][j];
                }
            } 
        }
        return C;
    }
    
    
    public static void cetakArray2D(int A[][]) {
        for (int i=0; i<A.length; i++){
            for (int j=0; j<A[i].length; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    
   
    public static void main(String[] args) {
        int A[][] = {{4, 3, 1}, {2, 6, 5}};
        int B[][] = {{2, 5, 4, 6}, {8, 2, 7, 0}, {7, 1, 3, 9,}};
        
        cetakArray2D(A);
        System.out.println(" ");
        cetakArray2D(B);
        System.out.println(" ");
        
        try {
            int C[][] = perkalianMatriks(A, B);
            cetakArray2D(C);
        }
        catch (Exception ex){
            System.err.println(ex.toString());
        }
    }
    
}