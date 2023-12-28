#pragma once
typedef int TREE_ITEM;

struct node_type {

	TREE_ITEM info;

	struct node_type* left, * right;

};
typedef struct node_type Tree, * TreePtr;

//functions:
TreePtr tree_init(void);

TreePtr tree_build(TreePtr lsub, TreePtr rsub, TREE_ITEM data);

void set_left(TreePtr tree, TREE_ITEM data);

void set_right(TreePtr tree, TREE_ITEM data);

TreePtr tree_lsub(TreePtr tree);

TreePtr tree_rsub(TreePtr tree);

void tree_change_lsub(TreePtr* t, TreePtr new_tree);

void tree_change_rsub(TreePtr* t, TreePtr new_tree);

TREE_ITEM tree_root_retrieve(TreePtr tree);

void tree_root_modify(TreePtr* t, TREE_ITEM data);

int is_empty(TreePtr t);