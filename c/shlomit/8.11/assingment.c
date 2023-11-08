#include <stdio.h>
//#include <string.h>
#define _CRT_SECURE_NO_WARNING_ 

void q1(char str[]);
int strLen(char str[]);
int main() {
	char str[50];
	gets(str);
	q1(str);
	printf("%s\n", str);
	printf("%i\n", strLen(str));
	return 0;
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