#define _CRT_SECURE_NO_WARNINGS
//#pragma once (disable:4996)
#include <stdio.h>

typedef struct {
	char name[20];
	float cost;
}item;

int main()
{
	item itemFromFile;
	item food[5] = { {"oat milk", 15.5},{"rice milk", 12.9},
		{"reg milk", 8.9},{"white choclate", 12.7},
		{"bitter choclare", 11.88} };
	char* filename; //items.bin
	FILE* finput;
	FILE* foutput;
	int i;
	foutput = fopen("items.bin", "wb");
	fwrite(&food, sizeof(item), 5, foutput);
	fclose(foutput);

	finput = fopen("items.bin", "rb");
	fseek(finput, 4 * sizeof(item), 0);
	fread(&itemFromFile , sizeof(item), 1,finput);
	printf("%s %f", itemFromFile.name, itemFromFile.cost);
	fclose(finput);
	

	return 0;
}
