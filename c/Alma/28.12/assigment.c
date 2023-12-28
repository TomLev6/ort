#pragma warning(disable:4996)
#include "Tree.h"
#include <stdio.h>
#include <math.h>
#include <string.h>
TreePtr q1() {
	// lsub -> smaller
	// rsub -> bigger
	Tree* t, *root;
	int num,found = 0;
	printf("Enter the first number: ");
	scanf("%i", &num);
	t = tree_build(NULL, NULL, num);
	root = t;
	while (num!=999)
	{
		printf("Enter the number: ");
		scanf("%i", &num);	
		while (!found)
		{
			if (num > t->info)
			{

				if (t->right == NULL)
				{
					set_right(t, num);
					found = 1;
				}
				else
				{
					t = t->right;
				}
			}
			else
			{
				if (t->left == NULL)
				{
					set_left(t, num);
					found = 2;
				}
				else {
					t = t->left;
				}
			}	
		}
		//q = tree_build(NULL, NULL, num);
		//if (found == 1) {
		//	set_right(t, q);
		//}
		t = root;
	}
	return root;
}
int tsamtim(TreePtr t) {
	if (t == NULL) return 0;
	return 1 + tsamtim(t->left) + tsamtim(t->right);
}
int is_ale(TreePtr t){
	return (t->left == NULL && t->right == NULL);
}
int alim(TreePtr t) {
	if (t == NULL) return 0;
	if (t->left == NULL && t->right==NULL) return 1;
	return alim(t->left) + alim(t->right);
}
int minInTree(TreePtr t){
	if (is_ale(t))
	{
		return t->info;
	}
	if (t->left->info > t->right->info) {
		return t->right;
	}
	else
	{
		return t->left;
	}
}
int maxInTree(TreePtr t) {
	if (is_ale(t))
	{
		return t->info;
	}
	if (t->left->info < t->right->info) {
		return t->right;
	}
	else
	{
		return t->left;
	}
}
TreePtr search(TreePtr t, int num) {
	if (t == NULL) return NULL;
	if (t->info==num)
	{
		return t;
	}
	return search(t->left, num) && search(t->right, num);
}
void gova(TreePtr t, int high) {
	if (t==NULL)
	{
		printf("%i", high);
	}
	return gova(t->left, high + 1);
	return gova(t->right, high + 1);
}

int main() {

	return 0;
}
