import java.util.Scanner;
import java.util.Arrays;
public class CountSorting {

    public static int findgreeter(int[] Arrayku){
        int maks_val = Integer.MIN_VALUE;
        for(int val : Arrayku){
            if(maks_val<val){
                maks_val = val;
            }
        }
        return maks_val;
    }
    
    public static int[] sorting(int[] a){
        int n = a.length;
        int[] b = new int[n];
        int k = findgreeter(a);
        int[] c = new int[k+1];
        System.out.println("Langkah-Langkah : ");
        System.out.println(Arrays.toString(c));
        
        for(int i = 0; i<c.length; i++){
            c[i] = 0;
        }
        for(int j = 0; j<n; j++){
            c[a[j]]++;
            System.out.println(Arrays.toString(c));
        }
        for(int i = 1; i<c.length; i++){
            c[i] += c[i-1];
            System.out.println(Arrays.toString(c));
        }
        for(int j = n-1; j>=0; j--){
            b[c[a[j]]-1] = a[j];
            c[a[j]]--;
        }
        return b;
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner (System.in);

        System.out.print("Masukkan Jumlah Elemen : ");
        int bil = input.nextInt();
        int[] nilai = new int[bil];

        System.out.println("Elemen Array yang Terinput : ");
        for (int i = 0; i<bil; i++){
            System.out.print("Elemen ke-" + (i+1) + " : ");
            nilai[i] = input.nextInt();
        }
        
        System.out.println("\n Array Belum Terurut : " + Arrays.toString(nilai));
        int[] sortedArray = sorting(nilai);
        System.out.println("\n Array Sudah Terurut : " + Arrays.toString(sortedArray));
    }
}
