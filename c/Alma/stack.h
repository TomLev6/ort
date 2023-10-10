# pragma once

#define MAX_STACK_LENGTH 100
typedef int stackItem;

struct stackType {
	stackItem arr[MAX_STACK_LENGTH];
	int sp;
};

typedef struct stackType stack;
typedef struct stackType*pstack;

void stackInit();
void push();
void pop();
void top();
int isEmpty();
int isFull();
