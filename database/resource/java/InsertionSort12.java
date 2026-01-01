import java.util.Scanner;

public class inserty {

    
    public static void cetakArray(int[] myArray) {
       for(int i = 0;i < myArray.length;i++){
           System.out.print(myArray [i]+" ");
       }
       System.out.println();
    }
    public static void insertionSort(int [] A){
        for (int i = 1; i < A.length; i++){
            int j = i-1;
            int key = A[i];
            
            while (j >=0 && A[j] > key){
                A[j + 1] = A [j];
                j--;
            }
            A[j+1] = key;
        }
        
        }
    
    public static void main(String[] args){
       
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah elemen: ");
        int masuk = input.nextInt();

      
        int[] myArray = new int[masuk];

        
        for (int i = 0; i < masuk; i++) {
            System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
            myArray[i] = input.nextInt();
        }

        System.out.print("Array Acak = ");
        cetakArray(myArray);

      
        insertionSort(myArray);

        System.out.print("Array Urut = ");
        cetakArray(myArray);
    }
}
  
