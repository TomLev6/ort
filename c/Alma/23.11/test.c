#include <stdio.h>
#include <math.h>
#include <string.h>
#include "list.h"
#pragma warning(disable:4996)

void fun(Node** list, Node** res) {
	Node* pos = *list;
	Node* maxp = *list;
	Node* minp = *list;

	Node* bmaxp = NULL;
	Node* bminp = NULL;

	int min = pos->info, max = pos->info;
	while (pos->next != NULL)
	{
		if (max < pos->next->info)
		{
			max = pos->next->info;
			maxp = pos->next;
			bmaxp = pos;
		}
		else if (pos->next->info < min) {
			min = pos->next->info;
			minp = pos->next;
			bminp = pos;
		}
		pos = pos->next;
	}

	if (bminp != NULL)
	{
		bminp->next = minp->next;
	}
	if (bmaxp != NULL)
	{
		bmaxp->next = maxp->next;
	}
	if (maxp->info == (*list)->info)
	{
		//getting here!		
		(*list) = (*list)->next;

	}
	if (minp->info == (*list)->info)
	{
		(*list) = (*list)->next;
	}
	minp->next = maxp;
	maxp->next = NULL;
	*res = minp;

	printList(*list);
	printf("\n");
	printList(*res);
}
void moreFun(Node* list, Node** res) {
	Node* tmp;
	int first = (*res)->info, last = (*res)->next->info;
	int tmpVal = (first + last)/2;
	getNode(&tmp, tmpVal);
	tmp->next = (*res)->next;
	(*res)->next = tmp;
	tmpVal = first + last;
	tmp->next->info = tmpVal;
	tmpVal = first - last;
	(*res)->info = tmpVal;
	Node* pos = list;
	while (pos->next!=NULL)
	{
		pos = pos->next;
	}
	pos->next = (*res);
}
int main() {
	Node* n;
	Node* res=NULL;
	getNode(&n, 6);
	insertEnd(n, 4);
	insertEnd(n, 1);
	insertEnd(n, 5);
	insertEnd(n, 3);
	printList(n);
	printf("\n");
	fun(&n, &res);
	moreFun(n, &res);
	printf("\n");
	printList(n);
	printf("\n");
	printList(res);
	


	return 0;
}
