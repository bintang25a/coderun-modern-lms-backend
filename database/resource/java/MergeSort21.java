import java.util.Scanner;

public class Megredinamis {

    public static void cetakArray(int[] myArray) {
       for(int i = 0;i < myArray.length;i++){
           System.out.print(myArray [i]+" ");
       }
       System.out.println();
    }
  
    public static void merge(int[] A, int p, int q,int r){
        int n1 = q-p+1;
        int n2 = r-q;
        int [] L = new int[n1+1];
        for(int i = 0 ; i < n1;i++)
              L [i] = A[p+i];
        L[n1] = Integer.MAX_VALUE;
        int[] R = new int [n2+1];
        for(int i = 0; i < n2;i++)
        R[i] = A[q+i+1];
        R[n2]= Integer.MAX_VALUE;
        int i = 0, j = 0;
        for(int k = p; k <= r; k++){
            if(L[i]<R[j]){
                A [k] = L[i];
                i++;
            }
            else {
                A[k] = R[j];
                j++;
            }
        }
    }
    
    public static void mergesort(int[] A, int p , int R){
        if(p < R){
            int q= ( (p+R)/2);
            mergesort(A,p,q);
            mergesort(A,q+1,R);
            merge(A,p,q,R);
        }
    }
    
    public static void main(String [] args){
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

      
        mergesort(myArray,0,myArray.length -1);

        System.out.print("Array Urut = ");
        cetakArray(myArray);
    }
}
  
