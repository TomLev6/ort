#define _CRT_SECURE_NO_WARNINGS_
#include <stdio.h>
int main() {
	int num = 0x5cb9;
	short digit, align = 12;
	unsigned int mask = 0xf000;
	int arr[4] = { 0 };
	// %x for hexa

	for (int i = 0; i < 4; i++)
	{
		digit = num & mask >> align -i*4;
		//printf("%x\t", digit);	
		arr[i] = digit;
	}
	for (int i = 3; i > -1; i--)
	{
		printf("%x\t", arr[i] >> i*4);
	}

	return 0;
}