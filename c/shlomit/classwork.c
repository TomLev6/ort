#include <stdio.h>
#pragma warning(disable:4996)
#define N 5;

void print(int a[][5]);
void print90cw(int a[][5]);
int main() {
	int arr2d[5][5];
	int i, j;
	for (i = 0; i < 5; i++)
	{
		for ( j = 0; j < 5; j++)
		{
			scanf("%d", &arr2d[i][j]);
		}
	}
	print(arr2d);
	printf("\n");
	print90cw(arr2d);
	return 0;
}
void print(int a[][5]) {
	int row, col;

	for ( col = 0; col < 5; col++)
	{
		for ( row = 0; row < 5; row++)
		{
			printf("%i\t", a[row][col]);
		}
		printf("\n");
	}
}
void print90cw(int a[][5]) {
	int row, col;

	for (col = 0; col < 5; col++)
	{
		for (row = 4; row >= 0; row--)
		{
			printf("%i\t", a[row][col]);
		}
		printf("\n");
	}
}
