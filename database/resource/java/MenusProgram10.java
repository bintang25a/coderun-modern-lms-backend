import java.util.Scanner;

public class Menu {

   public static int fpb(int x,int y){
        int temp;
        if (x<y){
        temp = x;
        x=y;
        y = temp;
        
     }
        while (y>0){
            temp = x%y;
            x = y ;
            y = temp;
        } 
        return x;
    } 
   
   public static int kpk (int x, int y){
        int a=x, b=y;
        while (a!=b){
            if (a<b){
                a+=x;
                
            }
            else {
                b+=y;
            }
        }
        return a;
    }
   public static int square(int x){
        return x*x;
    }
    
    public static int findSquareRoot(int n) {
       int r = 0;
       while (square(++r) <= n);
       return --r;
    }
    
    public static int perkalianRusia(int x , int y)
    {
     
        int total = 0;
        
        while (x>0){
            if (x%2==1)
                total+=y;
            
        x/=2;
        y*=2;
   }        
     return total;
    }
    
    
    public static void main(String[] args) {
        int pos;
        boolean i = true;
        
        
        Scanner input = new Scanner (System.in);
        System.out.println(" === MENU === ");
        System.out.println(" [1] FPB ");
        System.out.println(" [2] KPK ");
        System.out.println(" [3] Perkalian Rusia ");
        System.out.println(" [4] Akar ");
        System.out.println(" [0] Keluar ");
        
        System.out.println();
        while (i){
        
        System.out.print(" Masukan Bilangan A : ");
        int x = input.nextInt();
        
        System.out.print(" Masukan Bilangan B : ");
        int y = input.nextInt();
        
        System.out.print(" Masukan Kode Pilihan : ");
        pos = input.nextInt();
        
        
       if (pos == 1){
           System.out.println(" Hasil FPB dari " + x + " " +y+" = "+ fpb(x,y));
            
       }else if (pos == 2){
           System.out.println(" Hasil KPK dari " + x + " "+ y+" = "+ kpk(x,y));
            
       }else if (pos == 3){
           System.out.println(" Hasil Perkalian dari "+ x+"  "+ y+ " = "+ perkalianRusia(x,y));
            
        }else if (pos == 4){
            System.out.println(" Hasil Akar dari "+ x + " = "+ findSquareRoot(x));
            System.out.println(" Hasil Akar dari "+ y + " = "+ findSquareRoot(y));
             
        }else if (pos == 0){
            System.out.println(" Terimakasih ");
            break;
        }else {
            System.out.println(" Pilihan gak ada, ulangi dari awal ");
           continue;
        }
       
            System.out.print(" Lanjut Menghitung? (ketik ya/tidak): ");
            input.nextLine(); 
            String userResponse = input.nextLine().toLowerCase();

            if (userResponse.equals("tidak") || userResponse.equals("no")) {
                i = false;
            } else if (!userResponse.equals("ya") && !userResponse.equals("yes")) {
                System.out.println("Input tidak dikenali. Program akan berhenti.");
                i = false;
            }
        }
        input.close();
    }
}

