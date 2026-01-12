import java.util.Scanner;

public class perkalianRusia13 {
    
    public static int perkalianRusia (int i, int j) {
        int total=0;
        
        System.out.println("\nA\tB"); 

        while(i>=1){
            if (i%2!=0){
                
                total=total+j;
                System.out.println(i+"\t"+j+"\t"+"ambil "+j);
            } else {
                
                System.out.println(i+"\t"+j);
            }
            
            i/=2; 
            j*=2; 
        }
        
        return total;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int bilangan1, bilangan2;
        int hasil;

        System.out.print("Masukkan Bilangan 1 : ");
        bilangan1 = input.nextInt();

        System.out.print("Masukkan Bilangan 2 : ");
        bilangan2 = input.nextInt();

        hasil = perkalianRusia(bilangan1, bilangan2);

        System.out.println("\n" + bilangan1 + " * " + bilangan2 + " = " + hasil);

        input.close();
    }
}