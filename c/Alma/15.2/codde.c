#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define MAX_V 100
typedef struct Graph {
	int** mat;
	int size;
}Graph;

Graph* InitGraph() 
{
	Graph* g;
	// all the row pointers
	g->mat = (int**)calloc(sizeof(int*)*MAX_V);
	g->size = 0;
	return g;
}
void AddVertex(Graph* g,int v) {
	// create the row
	if (!g->mat[v]) {
		g->mat = realloc(g->mat, sizeof(int*) * MAX_V);

	}
	else
	{
		printf("No space left");
	}
}
void AddEdge(Graph* g, int x, int y, int w) {
	if (g->mat[x]) {
		g->mat[x][y] = w;
	}
	else
	{
		printf("source vertex does not exist");
	}
}
void RemoveEdge(Graph* g, int x, int y) {
	if (!g->mat[x]) {
		g->mat[x][y] = 0;
	}
	else
	{
		printf("vertex does not exist");
	}
}
void UpdateEdge(Graph* g, int x, int y, int w) {
	if (g->mat[x]) {
		g->mat[x][y] = w;
	}
	else
	{
		printf("vertex does not exist");
	}
}

/*
* // find the first empty place
	int i;
	for (i = 0; i < g->size; i++)
	{
		if (!g->mat[i]) {
			g->mat = realloc(g->mat, sizeof(int*)*MAX_V);
			
		}
		
	}
*/
