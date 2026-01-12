import java.util.Scanner;


public class Perkalian_rusia {

    public static int perkalian (int i, int j){
        int total = 0;
        
        if(i%2 == 0) {
            System.out.print(i+"\t "+j+"\n");
        }
        while(i>1){
            if(i%2!=0){
                if(i%2==1){
                    // ngambil nilai j
                    System.out.print(i+"\t "+j+"\t "+"ambil "+j+"\n");
                }
                total=total+j;
            }
            
            i/=2;
            j*=2;
            if(i%2==1){
                System.out.print("");
            }
            if(i%2==0){
                System.out.println(i+"\t "+j);
            }
        }
        while(i>=1){
            if(i%2!=0){
                if(i%2==1){
                    // ngambil nilai j akhir
                    System.out.print(i+"\t "+j+"\t "+"ambil "+j+"\n");
                }
                total = total+j;
            }
            i/=2;
            j*=2;
        }
        return total;
    } 

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int a,b;
        System.out.print("Masukkan bilangan 1 : "); a=input.nextInt();
        System.out.print("Masukkan bilangan 2 : "); b=input.nextInt();
        
        System.out.print("\nA \t B \n");
        System.out.println("\n" + a + " * "+ b + " = " + perkalian(a,b));
    }
    
}
