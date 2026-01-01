/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perkalian.matriks;

/**
 *
 * @author mohamadfajarmutaqin
 */
import java.util.Arrays;

public class PerkalianMatriks {
   

    
    public static int[] multiplyArrays(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Array harus memiliki ukuran yang sama");
        }

        int[] result = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            result[i] = array1[i] * array2[i];
        }
        return result;
    }

    public static void main(String[] args) {
        // Deklarasi array
        int[] array1 = {2, 4, 6};
        int[] array2 = {1, 3, 5};

        try {
            // Panggil fungsi untuk perkalian
            int[] result = multiplyArrays(array1, array2);

            // Cetak hasil
            System.out.println("Hasil perkalian array:");
            System.out.println(Arrays.toString(result));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


    