#include <stdio.h>
#pragma warning(disable:4996)
#define N 5

void print(int a[][N]);
void print90cw(int a[][N]);
int main() {
	int arr2d[N][N];
	int i, j;
	for (i = 0; i < N; i++)
	{
		for ( j = 0; j < N; j++)
		{
			scanf("%i", &arr2d[i][j]);
		}
	}
	print(arr2d);
	printf("\n");
	print90cw(arr2d);
	return 0;
}
void print(int a[][N]) {
	int row, col;

	for ( col = 0; col < N; col++)
	{
		for ( row = 0; row < N; row++)
		{
			printf("%i\t", a[row][col]);
		}
		printf("\n");
	}
}
void print90cw(int a[][N]) {
	int row, col;

	for (col = 0; col < N; col++)
	{
		for (row = N-1; row >= 0; row--)
		{
			printf("%i\t", a[row][col]);
		}
		printf("\n");
	}
}
