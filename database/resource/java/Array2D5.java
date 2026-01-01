public class Array2dimension {
    
    public static void printArray2D(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = { 
            {1, 2, 3}, 
            {4, 5, 6}, 
            {7, 8, 9} 
        };
        
        printArray2D(array);
    }
}
