#pragma once


typedef int list_info;
typedef struct node_type
{
	list_info info;
	struct node_type* next;
}Node;

//init
void init(Node** list);

//Creat a new node
void getNode(Node** node, list_info x);

//insert a node to the "end" of the list
void insertEnd(Node* list, list_info x);

//insert a node to the "head" of the list
void insertToHead(Node** list, list_info x);

//insert node after a given node
void insertAfter(Node* pos, list_info x);

//remove a node after a given node
list_info deleteAfter(Node* pos);

list_info deleteAfter(Node* pos);

list_info deleteHead(Node** list);

void printList(Node* list);
