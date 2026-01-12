/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class FPBKPK {
    public static int fpb(int x, int y) {
        int temp;
        if(x<y) {
            temp =x;
            x=y;
            y=temp;
        }
        while(y>0) {
            temp=x%y;
            x=y;
            y=temp;
        }
        return x;
    }

    public static int kpk(int x, int y) {
        return (x * y) / fpb(x, y);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int a, b, pilihan;
        System.out.println("Program KPK dan FPB ZOELKIFLIE 24040700053:");
        System.out.println("1. FPB");
        System.out.println("2. KPK");
        System.out.print("Masukkan pilihan (1/2): ");
        pilihan = input.nextInt();
        System.out.print("Input nilai A: ");
        a = input.nextInt();
        System.out.print("Input nilai B: ");
        b = input.nextInt();

        if (pilihan == 1) {
            int fpb = fpb(a, b);
            System.out.println("\nCetak Hasil FPB = " + fpb);
        } else if (pilihan == 2) {
            int kpk = kpk(a, b);
            System.out.println("\nCetak Hasil KPK = " + kpk);
        } else {
            System.out.println("Pilihan tidak valid!");
        } 
    } 
} 