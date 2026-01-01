import java.util.Arrays;
import java.util.Scanner;

public class Counting {

    static int findgrateelement(int[] myarray){
        int maxval = Integer.MIN_VALUE;
        
        for(int val : myarray){
            if(maxval < val){ 
                maxval = val;
            }
        }
        return maxval;
    }
    
    static int[] countingsort(int[] A){
        int n = A.length;
        int[] B = new int[n];
        int k = findgrateelement(A);
        int[] C = new int[k + 1];
        System.out.println("Langkah - Langkah : ");
        System.out.println(Arrays.toString(C));
        
        for(int i = 0; i < C.length; i++){
            C[i] = 0;
        }
        for(int j = 0; j < n; j++){
            C[A[j]]++;
            System.out.println(Arrays.toString(C));
        }
        for(int i = 1; i < C.length; i++){
            C[i] += C[i - 1];
            System.out.println(Arrays.toString(C));
        }
        for(int j = n-1; j >= 0; j--){
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        } 
        return B;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] data;
        int bil;
        System.out.print("Masukkan angka : ");
        bil=input.nextInt();
        System.out.println("");
        
        data = new int[bil];
        for(int i = 0; i < bil; i++){
            System.out.print("Masukkan data ke-"+(i+1)+" = ");
            data[i]=input.nextInt();
        }
        
        System.out.println("\nData : "+Arrays.toString(data)+"\n");
        System.out.println("\nData setelah di counting sort : \n"+Arrays.toString(countingsort(data)));
    }
}
