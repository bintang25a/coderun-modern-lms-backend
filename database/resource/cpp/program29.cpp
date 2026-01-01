#include <iostream>
using namespace std;

void jumlahMatriks(int matriksA[3][3], int matriksB[3][3], int matriksC[3][3]) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            matriksC[i][j] = matriksA[i][j] + matriksB[i][j];
        }
    }
}

void cetakMatriks(int matriks[3][3]) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            cout << matriks[i][j] << " ";
        }
        cout << endl;
    }
}

int main() {
    // Matriks A dan B diisi dengan 0
    int matriksA[3][3] = { {1, 0, 0}, {0, 0, 0}, {0, 0, 0} };
    int matriksB[3][3] = { {0, 0, 0}, {0, 1, 0}, {0, 0, 1} };
    int matriksC[3][3] = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };

    // Menghitung hasil penjumlahan matriks
    jumlahMatriks(matriksA, matriksB, matriksC);

    // Menampilkan hasil
    cout << "Hasil penjumlahan matriks A dan B:\n";
    cetakMatriks(matriksC);

    return 0;
}
