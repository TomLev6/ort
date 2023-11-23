#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <math.h>
#include "list.h"
#pragma warning(disable:4996)
void TakeReservation(Node Ordr) {

	char str[20];
	int num;
	enum CoffeeType typ = Nesspreso;
	printf("Enter Your Name:");
	fgets(str,sizeof(str),stdin);
	*Ordr.info.name = str;

	printf("Enter Your Coffee Type 0 for Nesspreso, 1 for Espresso, 2 Americano, 3 Black:");
	scanf("%i",&num);
	if (num == 1) typ = Espresso;
	if (num == 2) typ = Americano;
	if (num == 3) typ = Black;
	Ordr.info.type = typ;
}
int main() {
	
	return 0;
}