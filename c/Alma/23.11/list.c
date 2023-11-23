#include "list.h"
#include <stdio.h>

void init(Node** list) {
	*list = NULL;
}

void getNode(Node** node, list_info x) {
	*node = (Node*)malloc(sizeof(Node));
	if (*node == NULL) {
		printf("Overflow");
	}
	else {
		(*node)->info = x;
		(*node)->next = NULL;
	}
}

void insertToHead(Node** list, list_info x) {
	Node* newNode;
	getNode(&newNode, x);
	if (newNode != NULL) {
		newNode->next = *list;
		*list = newNode;
	}
}

list_info deleteHead(Node** list) {
	Node* temp;
	if (*list != NULL) {
		temp = *list;
		*list = (*list)->next;
		list_info x = temp->info;
		free(temp);
		return x;
	}
}

void insertAfter(Node* pos, list_info x) {
	Node* newNode;
	getNode(&newNode, x);
	if (newNode != NULL) {
		newNode->next = pos->next;
		pos->next = newNode;
	}
}

list_info deleteAfter(Node* pos) {
	Node* temp;
	if (pos->next != NULL) {
		temp = pos->next;
		pos->next = pos->next->next;
		return temp->info;
	}
}

void insertEnd(Node* list, list_info x) {
	while (list->next != NULL) {
		list = list->next;
	}
	Node* newNode;
	getNode(&newNode, x);
	list->next = newNode;
}

int isEmpty(Node* list) {
	return list == NULL;
}

void printList(Node* list) {
	while (list != NULL) {
		if (list->next != NULL) {
			printf("%d -> ", list->info);
		}
		else {
			printf("%d", list->info);
		}
		list = list->next;
	}
	puts("");
}