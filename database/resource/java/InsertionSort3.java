import java.util.Scanner;

public class JavaApplication5 {

    public static void printArray(int[] array) {
  
       for (int value : array) {
           System.out.print(value + "");
           
       }
        System.out.println();
        
    }
    public static void inserttonSort (int[] array) {
     int n =array.length;
     for (int i =1; i< n; i++){
         int key = array [1];
         int j = 1-1;
         
         while (j >=0 && array [j] > key){
             System.out.println("Interasi ke-" + i +"="+array[j] +"Ditukanr dengan "+ key);
             array[j+1]=array[j];
             j=j-1;
         }
         array[j+1]=key;
     }
    }
    public static void main (String[]args){ 
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukan jumlah elmen ");
        int n= scanner.nextInt();
        
        int []array = new int [n];
        System.out.println("Masukan elemen array");
        for (int i =0; i< n; i++){
            System.out.print("Elemen ke-"+(i+1)+": ");
            array[i]= scanner.nextInt();
            
        }
        System.out.println("\nArray sebelum di urutkan :");
        printArray(array);
        
        insertionSort(array);
        
        System.out.println("\nArray setelah di urutkan:");
        printArray(array);
        scanner.close();
    } 
    public static void insertionSort(int[]array){
        for (int i = 1; i< array.length; i++){
            int key = array[i];
            int j =i-1;
            while (j>=0 && array [j]>key){
                array [j+1]=array [j];
                j--;
            }
            array[j+1]=key;
        }
    }
  
    
}
 