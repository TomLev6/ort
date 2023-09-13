#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#define PI 3.1415927
#define N 50
int caful(int a, int b);
void Maxin3(int a, int b, int c);
float surface(float r);
float hekef(float r);
int main()
{
	//printf("%d", Q1_no_caful(10, 5));
	Maxin3(2, 5, 33);
	//printf("%f", surface(5));
	return 0;
}
int caful(int a, int b) {
	int i, p = b;
	for (i = 0; i < a-1; i++){
		b += p;
	}
		
	return b;
}
void Maxin3(int a, int b, int c) {
	int max = (a > b? a:b);
	printf("%d",max > c ? max : c);
	
}
float surface(float r) {
	return r * r * PI;
}
float hekef(float r) {
	return 2 * r * PI;
}
