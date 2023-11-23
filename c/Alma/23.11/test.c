#include <stdio.h>
#include <math.h>
#include <string.h>
#include "list.h"
#pragma warning(disable:4996)

void fun(Node** list, Node** res) {
	Node* pos = *list;
	Node* maxp= *list;
	Node* minp= *list;

	Node* bmaxp = NULL;
	Node* bminp = NULL;

	int min= pos->info, max = pos->info;
	while (pos->next!=NULL)
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

	if (bminp!=NULL)
	{
		bminp->next = minp->next;
	}
	if (bmaxp != NULL)
	{
		bmaxp->next = maxp->next;
	}
	if (maxp->info==(*list)->info)
	{
		//getting here!		
		(*list) = (*list)->next;
		
	}
	if (minp->info == (*list)->info)
	{
		(*list) = (*list)->next;
	}
	res = minp;
	minp->next = maxp;
	maxp->next = NULL;

	printList(*list);
	printf("\n");
	printList(res);
}

int main() {
	Node* n;
	Node* res = NULL;
	getNode(&n, 9);
	insertEnd(n, 4);
	insertEnd(n, 1);
	insertEnd(n, 8);
	insertEnd(n, 3);
	printList(n);
	printf("\n");
	fun(&n, &res);

	//int num;
	//Node* n;	
	//scanf("%i", &num);
	//getNode(n,&num);
	//for (int i = 0; i < 2; i++) {
	//	scanf("%i", &num);
	//	insertEnd(n, num);
	//	
	//}

	
	return 0;
}
