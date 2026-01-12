import java.util.Scanner;

public class fpbKpk12 {

    public static int fpb(int x, int y){
        int temp;
        if(x<y){
            temp = x;
            x = y;
            y = temp;
        }while(y>0){
            temp=x%y;
            x=y;
            y=temp;
        } return x;
    }
    
    public static int kpk(int x, int y) {
        int a = x;
        int b = y;
        while(a!=b){
            if(a<b){
                a=a+x;
            }else{
                b=b+y;
            }
        }
        return a;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int a, b;
        System.out.println("Program KPK dan FPB FathoniAdamIlyasa-24040700060 ");
        System.out.print("\nInput nilai A = "); a = input.nextInt();
        System.out.print("Input nilai B = "); b = input.nextInt();
        
        int fpb = fpb(a,b);
        int kpk = kpk(a,b);
        System.out.println("\nCetak hasil FPB = " + fpb);
        System.out.println("\nCetak hasil KPK = " + kpk);
    }
}
