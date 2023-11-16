#include <stdio.h>
#include <math.h>
#include "list.h"
#pragma warning(disable:4996)

int main() {
	Node* n;
	getNode(&n,1);
	insertEnd(n, 3);
	insertEnd(n, 5);
	insertEnd(n, 7);
	Node* n2;
	getNode(&n2, 1);
	insertEnd(n2, 4);
	insertEnd(n2, 5);



	int num = q2(n,n2);
	printf("%i", num);
	return 0;
}
int warmupQ(Node* n) {
	int sum = 0;
	Node* pos = n;
	while (pos!=NULL)
	{
		sum = (sum * 10) + pos->info;
		pos = pos->next;
	}
	return sum;
		
}
int reverse(int num) {
	int newnum = 0;
	while (num > 9)
	{
		newnum = (newnum*10) + num % 10;
		num /= 10;
	}
	newnum += num;
}
int q1(Node* n) {
	if (n->next==NULL)
	{
		return n->info;
	}
	return n->info + 10 * q1(n->next);
}
int q2(Node* n1, Node* n2) {
	if (n2==NULL)
	{
		return 1;
	}
	if (n1->info==n2->info)
	{
		return q2(n1->next, n2->next);
	}
	if (n1->next==NULL)
	{
		return 0;
	}
	return q2(n1->next,n2);
}