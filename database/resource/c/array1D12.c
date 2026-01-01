#include <stdio.h>

// Fungsi untuk mencari nilai maksimum dalam array 
int findMax(int arr[], int size) {
	int max = arr[0]; // Asumsikan elemen pertama adalah yang terbesar
	for (int i = 1; i < size; i++) {
		if (arr[i] > max) {
			max = arr[i]; // Update nilai maksimum jika ditemukan yang lebih besar
		}
	}
	return max; // Kembalikan nilai maksimum
}

int main() {
	int n;
	
	// Input ukuran array 
	printf("Masukkan jumlah elemen dalam array: ");
	scanf("%d", &n);
	
	int arr[n]; // Deklarsi array dengan ukuran n
	
	// Input elemen array
	printf("Masukkan %d elemen array:\n", n);
	for (int i = 0; i < n; i++) {
		printf("Elemen %d: ", i + 1);
		scanf("%d", &arr[i]);
	}
	
	// Mencari nilai maksimum
	int max = findMax(arr, n);
	
	// Menampilkan hasil
	printf("Nilai maksimum dalam array adalah: %d\n", max);
	
	return 0;
}