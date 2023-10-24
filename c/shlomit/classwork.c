#include <stdio.h>
#include <math.h>
#pragma warning(disable:4996)
#define N 5
#define ARRAY_SIZE 7

void print(int a[][N]);
void print90cw(int a[][N]);
void bubbleSort(int arr[], int length);
void magicSquare(int a[][N]);
int main() {
	/*
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
	
	int arr[ARRAY_SIZE],i;
	for (i = 0; i < ARRAY_SIZE; i++)
	{
		scanf("%i", &arr[i]);
	}
	bubbleSort(arr, ARRAY_SIZE);
	for (i = 0; i < ARRAY_SIZE; i++)
	{
		printf("%i\t",arr[i]);
	}
	*/
	int arr2d[N][N];
	int i, j;
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			arr2d[i][j] = 0;
		}
	}
	magicSquare(arr2d);
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
void bubbleSort(int arr[], int length) {
	int i, j;
	int tmp;
	for (i = 0; i < length - 1; i++)
	{
		for (j = 0; j < length - 1 - i; j++) {
			if (arr[j]>arr[j+1])
			{
				tmp = arr[j + 1];
				arr[j + 1] = arr[j];
				arr[j] = tmp;
			}
		}
	}
}
void magicSquare(int a[][N]) {
	int counter = 1,row=0,col=N/2+1;
	while (counter< pow(N, 2)+1)
	{
		if(a[row][col]==0)
		a[row][col] = counter;
		row++;
		col++;
		counter++;
		if (row==N)
		{
			row = 0;
		}
		if (col==N)
		{
			col = 0;
		}
		
	}
	print(a);
}
/*
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
*/
