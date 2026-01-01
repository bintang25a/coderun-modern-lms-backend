import java.util.Scanner;

public class Tantangan {

    public static int perkalianrusia(int a, int b){
        
        int total = 0;

        while(a > 0){
            if( a % 2 == 1){
                total = total + b;
            }
            a/=2;
            b*=2;
        }
        return total;
    }
    
    public static int square(int x){
        return x*x;
    }
    
    public static int findsquareroot(int n){
        int r = 0;
        while(square(++r) <= n);
        return --r;
    }
    
    public static int fpb(int x, int y){
        int tmp;
        if(x < y){
            tmp = x;
            x = y;
            y = tmp;
        }
        while(y > 0){
            tmp = x % y;
            x = y;
            y = tmp;
        }
        return x;
    }
    
    public static int kpk(int x, int y){
        int a = x, b = y;
        
        while(a != b){
            if(a < b){
                a += x;
            }
            else{
                b += y;
            }
        }
        return a;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("== MENU ==\n[1] FPB\n[2] KPK\n[3] Perkalian Rusia\n[4] Akar\n[0] Keluar");
        
        boolean o = true;
        while(o){
        
            System.out.print("Masukkan nilai pertama :");
            int a = input.nextInt();
            System.out.print("Masukkan nilai kedua :");
            int b = input.nextInt();

            System.out.print("Masukkan Pilihan : ");
            int w = input.nextInt();
            if(w == 1){

                System.out.println("Hasil FPB : "+ fpb(a, b));
                System.out.print("\nUlang input [1/0]? : ");
                int pil = input.nextInt();
                if (pil == 0){
                    o = false;
                }
                else if (pil == 1){
                    o = true;
                }
                else {
                    o = false;
            }
            System.out.print("\n");
            }
            else if(w == 2){

                System.out.println("Hasil KPK : "+ kpk(a, b));
                System.out.print("\nUlang input [1/0]? : ");
                int pil = input.nextInt();
                if (pil == 0){
                    o = false;
                }
                else if (pil == 1){
                    o = true;
                }
                else {
                    o = false;
                }
                System.out.print("\n");
            }
            else if(w == 3){

                int perkalian = perkalianrusia(a,b);
                System.out.println("Hasil Perkalian Rusia : "+ perkalian);
                System.out.print("\nUlang input [1/0]? : ");
                int pil = input.nextInt();
                if (pil == 0){
                    o = false;
                }
                else if (pil == 1){
                    o = true;
                }
                else {
                    o = false;
                }
                System.out.print("\n");
            }
            else if(w == 4){

                System.out.println("akar dari angka "+ a +" = "+ findsquareroot(a));
                System.out.println("akar dari angka "+ b +" = "+ findsquareroot(b));
                System.out.print("\nUlang input [1/0]? : ");
                int pil = input.nextInt();
                if (pil == 0){
                    o = false;
                }
                else if (pil == 1){
                    o = true;
                }
                else {
                    o = false;
                }
                System.out.print("\n");
            }
            else if(w == 0){
                System.out.println("Terima Kasih\n");
                o = false;
            }
            else {
                System.out.println("Anda Salah Input\n");
                o = true;
            }   
        }
    }
}
