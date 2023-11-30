#include <stdio.h>
#include <math.h>
#include <string.h>
#pragma warning(disable:4996)
#define N 16
int previousMove;
void setBoardWalls(char board[N][N]) {
	/*
	sets the board to the starting values ,
	then recieves row and col for the seleted places for the walls
	*/
	for (int i = 0; i < N - 1; i++)
	{
		for (int j = 0; j < N - 1; j++) {
			board[i][j] = '.'; 
		}
	}
	int row=0, col=0;
	while (row!=-999)
	{
		printf("Enter the wall row (enter -999 to end the loop):\n");
		scanf("%d", &row);
		printf("Enter the wall col:\n");
		scanf("%d", &col);
		if ((row <= 20) && (row > 0)) {
			board[row][col] = '*';
		}
	}
}
void printBoard(char board[N][N]) {
	/*
	* prints the board.
	*/
	for (int i = 0; i < N - 1; i++)
	{
		for (int j = 0; j < N - 1; j++) {
			printf("%c\t", board[i][j]);
		}
		printf("\n");
	}
}


int moveBooli(char board[N][N], int booliRow, int booliCol) {
	/*
	gets if the player still alive, and his current location.
	moving the player if the move is valid, else prints that the move didn't made .
	went through walls or the bear got him.
	*/
	int alive = 1;
	int points = 0;
	int directionNum;
	int firstMove = 0;
	while (alive != 0)
	{
		printf("Enter direction number(1/2/3/4) 0 for the previous direction, only on the second move:\n");
		scanf("%d", &directionNum);
		while ((directionNum >= 0) && (directionNum < 5)) {
			printf("Enter direction number(1/2/3/4) 0 for the previous direction, only on the second move:\n");
			scanf("%d", &directionNum);
		}		
		if ((firstMove == 0) && (directionNum == 0)) {
			printf("unvalid move!\n");
			while ((directionNum > 0) && (directionNum < 5))
			{
				printf("Enter direction number(1/2/3/4):\n");
				scanf("%d", &directionNum);
			}
		}
		firstMove = 1;
		if (directionNum == 0) {
			directionNum = previousMove;
		}
		switch (directionNum)
		{
		
		case 1:
			if ((board[booliRow][booliCol+1] != "*")&&(booliCol + 1 < N -1)) // && (board[booliRow][booliCol + 1] != "D"))
			{
				booliCol++;
			}	
			else
			{
				printf("Move havn't occurred! (wall)\n");
			}
			break;
		case 2:
			if ((board[booliRow][booliCol - 1] != "*") && (booliCol - 1 > 0))
			{
				booliCol--;
			}
			else
			{
				printf("Move havn't occurred! (wall)\n");
			}

			break;
		case 3:
			if ((board[booliRow -1][booliCol] != "*") && (booliRow - 1 > 0))
			{
				booliRow--;
			}
			else
			{
				printf("Move havn't occurred! (wall)\n");
			}
			break;
		case 4:
			if ((board[booliRow+1][booliCol] != "*") && (booliRow + 1 < N - 1))
			{
				booliRow++;
			}
			else
			{
				printf("Move havn't occurred! (wall)\n");
			}
			break;
		}
		if (board[booliRow][booliCol]=="D")
		{
			alive = 0;
			printf("Game over! You lost");
		}
		else if (board[booliRow][booliCol] == ".")
		{
			points++;
		}
		previousMove = directionNum;
	}
	return points;
}
void moveBear(char board[N][N]) {
					  // N	 S	 W	 E	 NW  NE SE	 SW
	int xdirections[] = {0	,0	,-1	,1	,-1	,1	,1	,-1};
	int ydirections[] = {-1	,1	,0	,0	,-1	,-1	,1	,1};
}
void placeBooli(char board[N][N]) {
	int ok = 0;
	int row = 0,col = 0;
	while (ok==0)
	{
		printf("Enter the wall row (enter -999 to end the loop):\n");
		scanf("%d", &row);
		printf("Enter the wall col:\n");
		scanf("%d", &col);
		if ((row <= 20) && (row > 0)) {
			board[row][col] = 'B';
			ok = 1;
		}
	}
}



int main()
{
	int score;
	char board[N][N];
	setBoardWalls(board);
	placeBooli(board);
	printBoard(board);
	return 0;
}