#pragma once


typedef int list_info;
typedef struct node_type
{
	list_info info;
	struct node_type* next;
}Node;

//init
void init_cir(Node** list);

//Creat a new node
void get_node(Node** node, list_info x);

//insert a node to the "end" of the list
void inset_cir(	Node** list, list_info x);

//insert a node to the "head" of the list
void inset_to_head(Node** list, list_info x);

//insert node after a given node
void insert_after(Node* pos, list_info x);

//remove a node after a given node
list_info delet_after(Node** list, Node* pos);

//remove the last node
list_info delet_last(Node** list);

