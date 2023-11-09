#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>

#define N 6
int Neigbers(int a[N][N], int i, int j);
void GameOfLife(int a[N][N]);
int main() {
	int a[N][N] = { 0 };
	
	a[2][3] = 1;
	a[4][2] = 1;
	a[3][2] = 1;

	a[2][4] = 1;
	a[3][1] = 1;
	a[3][4] = 1;

	a[2][1] = 1;

	for (int i = 0; i < N-1; i++)
	{
		for (int j = 0; j < N-1; j++)
		{
			printf("%i\t", a[i][j]);
		}
		printf("\n");
	}
	printf("\n\n");
	int b[N][N] = { 0 };
	GameOfLife(a, b);
	for (int i = 0; i < N-1 ; i++)
	{
		for (int j = 0; j < N-1; j++)
		{
			printf("%i\t", b[i][j]);
		}
		printf("\n");
	}

	return 0;
}
int Neigbers(int a[N][N],int i, int j) {
	int neigbers = 0;	
	if (a[i - 1][j] == 1)
	{
		neigbers++;
	}
	if (a[i][j + 1] == 1)
	{
		neigbers++;
	}
	if (a[i][j - 1] == 1)
	{
		neigbers++;
	}
	if (a[i + 1][j + 1] == 1)
	{
		neigbers++;
	}
	if (a[i - 1][j + 1] == 1)
	{
		neigbers++;
	}
	if (a[i + 1][j] == 1)
	{
		neigbers++;
	}
	if (a[i - 1][j - 1] == 1)
	{
		neigbers++;
	}
	if (a[i + 1][j - 1] == 1)
	{
		neigbers++;
	}						
	
	return neigbers;
}
void GameOfLife(int a[N][N], int b[N][N]) {
	for (int i = 1; i < N-1; i++) {
		for (int j = 1; j < N-1; j++) {
			if (a[i][j]==1)
			{
				if (Neigbers(a, i, j) < 2) {
					b[i][j] = 0;
				}	
				if (Neigbers(a, i, j) > 3) {
					b[i][j] = 0;
				}
			}
			if (a[i][j] == 0)
			{
				if (Neigbers(a, i, j) == 3) {
					b[i][j] = 1;
				}
			}
		}
	}
}