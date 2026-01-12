/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

public class exam26 {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=======================");
            System.out.println("PROGRAM SERBA GUNA JAVA ZOELLL");
                        System.out.println("=======================");
            System.out.println("[1] Perkalian Rusia");
            System.out.println("[2] Hitung FPB");
            System.out.println("[3] Hitung KPK");
            System.out.println("[4] Program Matriks");
            System.out.println("[5] Keluar Program");
            System.out.println("----------------------------");
            System.out.print("Pilih menu : ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    perkalianRusia();
                    break;
                case 2:
                    hitungFPB();
                    break;
                case 3:
                    hitungKPK();
                    break;
                case 4:
                    programMatriks();
                    break;
                case 5:
                    System.out.println("PProgram selesai !");
                    break;
                default:
                    System.out.println("Menu tidak ditemukan !");
                    System.out.println("Menu yang anda masukkan = "+pilihan+" Tidak ditemukan ! ");
            }
        } while (pilihan != 5);
    }

    public static void perkalianRusia() {
        System.out.println("\nProgram Perkalian Rusia Terpilih !");
        System.out.println("----------------------------------------");
    System.out.print("Masukkan Nilai Pertama : ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai Kedua : ");
        int b = input.nextInt();
        System.out.println("\nProses Perkalian:");
        int total = 0;
        int currentA = a;
        int currentB = b;
        while (currentA > 0) {
            String keterangan = "";
            if (currentA % 2 != 0) {
                total += currentB;
                keterangan = "ambil " + currentB;
            }
            
            System.out.printf("%-5d %-5d   %s\n", currentA, currentB, keterangan);
            currentA /= 2;
            currentB *= 2;
        }        System.out.println("\nHasil Perkalian = " + total);
    }


    public static void hitungFPB() {
        System.out.println("\nProgram FPB Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai kedua: ");
        int b = input.nextInt();

        while (b != 0) {
            int sisa = a % b;
            a = b;
            b = sisa;
        }
        System.out.println("\nCetak Hasil FPB = " + a);
         System.out.println("--------------------------------");
    }

    public static void hitungKPK() {
        System.out.println("\nProgram KPK Terpilih !");
        System.out.println("----------------------------------------");
        System.out.print("Masukkan Nilai pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan Nilai kedua: ");
        int b = input.nextInt();

        int x = a, y = b;
        while (y != 0) {
            int sisa = x % y;
            x = y;
            y = sisa;
        }
        int fpb = x;
        int kpk = (a * b) / fpb;

        System.out.println("\nCetak Hasil KPK = " + kpk);
                 System.out.println("--------------------------------");
    }

    public static void programMatriks() {
        System.out.println("\n--- Program Matriks Dinamis ---");
        
        System.out.print("Masukkan jumlah baris pada matriks = ");
        int baris =  input.nextInt();
        System.out.print("Masukkan jumlah kolom pada matriks = ");
        int kolom = input.nextInt();

        int[][] matriks = new int[baris][kolom];

        System.out.println(); 

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Matriks [" + (i + 1) + "][" + (j + 1) + "]  = ");
                matriks[i][j] = input.nextInt();
            }
        }

        System.out.println("\nNilai Matriks :");
        for (int i = 0; i < baris; i++) {
            System.out.print("| "); 
            for (int j = 0; j < kolom; j++) {
                System.out.print(matriks[i][j] + " "); 
            }
            System.out.println("|"); 
        }
        System.out.println("--------------------------------------------------");
    }

}

