#pragma warninig (disable:4996)
#include <stdio.h>

int mul_row_by_col(int** matrix1, int**  matrix2, int row, int col);
int main() {

	int n, k;
	int matrix1[][3] = { {1,2,0},{4,3,-1} };
	int matrix2[][2] = { {5,1},{2,3},{3,4 }};
	int m_res[3][3] = { 0 };
	for (int row = 0; row < 2; row++)
	{
		for (int col = 0; col < 2; col++)
		{
			m_res[row][col] = mul_row_by_col((*matrix1)[3], (*matrix2)[2], row, col);
			printf("%i\t", m_res[row][col]);
		}
		printf("\n\n");
	}
	
	return 0;
}
int mul_row_by_col(int(* matrix1)[3], int(*matrix2)[2], int row, int col) {
	int sum = 0;
	for (int n = 0; n < 3; n++)
	{			
		sum += (*(*(matrix1 +row) + n)) * (*(*(matrix2+n)+col));		
	}
	return sum;
}