public class Array2D {
    public static void cetakArray2D(int A[][]){
        for (int i=0; i<A.length; i++) {
            for(int j=0; j<A[i].length; j++){
                System.out.println(A[i][j] + " ");
            }
            System.out.println(" ");    
        }
    
    }
    public static void main(String[] args) {
        int A[][] = {{1,2,3}, {4,5,6}, {7,8,9}};
        
        cetakArray2D(A);
    }
    
}
