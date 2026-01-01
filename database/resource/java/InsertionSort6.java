import java.util.Scanner;

public class InsertionSort_dinamis {

    public static void cetakArray(int[] myArray) {
        for (int i = 0; i<myArray.length; i++) {
        System.out.print(myArray[i]+" ") ;
        }
        System.out.println();
    }
    
    public static void InsertionSort(int[] A) {
    for  (int i=1; i<A.length; i++) {
    int j=i-1 ;
    int key=A[i];

    while (j >= 0 && A[j] > key) {
    A[j+1]=A[j];
    j--;
    }
    A[j+1] = key;
    }
  }   
    
public static void main(String[] args) {
   Scanner input = new Scanner (System.in);

    int[] tampung;
    int bil;
    System.out.println("Masukkan banyak angka : ");
    bil=input.nextInt();

    tampung = new int[bil];
 

for (int i = 0; i < bil; i++){
    System.out.print("Masukkan elemen ke- "+(i + 1)+ "= ");
    tampung[i]=input.nextInt();
}
    

System.out.print("Array acak =");
cetakArray(tampung);

InsertionSort(tampung);

System.out.print("Array urut :");
cetakArray(tampung);
}
}