#include <stdio.h>
#include <string.h>
#define _CRT_SECURE_NO_WARNING_ 

void swap(char* c1, char* c2);
void sort(char** ptr, int size);
void q1(char str[]);
int strLen(char str[]);
void strCat(char str1[], char str2[]);
int main() {
	enum{ARR_SIZE = 8};
	int i;
	char** colors[ARR_SIZE] = { "red", "black", "white","purple",
		"yellow","pink","green","blue" };
	sort(colors, ARR_SIZE);
	for (i = 0; i < ARR_SIZE; i++)
	{
		puts(colors[i]);
	}
	return 0;
}
void swap(char* c1, char* c2) {
	char* tmp;
	tmp = *c1;
	*c1 = *c2;
	*c2 = tmp;
}
void q1(char str[]) {
	//return strrev(str);
	int len = strLen(str);
	int start=0, end=len-1, count = 0;
	char* ptr;
	if (len%2==0)
	{
		while (start<(len/2)+1)
		{
			ptr = str[end];
			str[end] = str[start];
			str[start] = ptr;
			start++;
			end--;
		}
	}
	else
	{
		while (start < (len / 2))
		{
			ptr = str[end];
			str[end] = str[start];
			str[start] = ptr;
			start++;
			end--;
		}
	}
}
int strLen(char str[]) {
	int len = 0;
	char c=str[len];
	while (c!='\0')
	{		
		len++;	
		c = str[len];
	}
	return len;
}
void strCat(char str1[], char str2[]) {
	int end = strLen(str1);
	char* ptr = str1[end];
	ptr++;
	ptr = str2[0];

}
void sort(char** ptr, int size) {
	int i, j;
	char** tmp;
	for (i = 0; i < size - 1; i++) {
		for ( j = 0; j < size-1; j++)
		{
			if (strcmp(*(ptr + j), *(ptr + 1 + j))>0) { swap(&ptr + j, &ptr + 1 + j); printf("True!\n"); }
		

			//if (strcmp(ptr, ptr + 1)){ *tmp = *(ptr + 1); *(ptr + 1) = *ptr; *ptr = *tmp; }
		}
	}
}