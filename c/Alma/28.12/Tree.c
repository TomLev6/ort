#pragma warning (disable:4996)
#include <stdio.h>
#include "tree.h"
TreePtr Tree_init(void)
{

	return NULL;

}

TreePtr tree_build(TreePtr lsub, TreePtr rsub, TREE_ITEM data)

{

	TreePtr t;

	t = (TreePtr)malloc(sizeof(struct node_type));

	t->info = data;

	t->left = lsub;

	t->right = rsub;

	return t;

}
TreePtr tree_lsub(TreePtr t)

{

	return t->left;

}

TreePtr tree_rsub(TreePtr t)

{

	return t->right;

}

void tree_delete(TreePtr* t)

{

	if (!tree_empty(*t))

	{

		tree_delete(&((*t)->left));

		tree_delete(&((*t)->right));

		free(*t);

	}

}
void tree_change_lsub(TreePtr* t, TreePtr new_tree)

{

	tree_delete(&((*t)->left));

	(*t)->left = new_tree;

}

TREE_ITEM tree_root_retrieve(TreePtr t)

{

	return t->info;

}

void tree_root_modify(TreePtr* t, TREE_ITEM data)

{

	(*t)->info = data;

}

int is_empty(TreePtr t)

{

	return (t == NULL);

}