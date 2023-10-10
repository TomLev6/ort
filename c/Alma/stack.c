#include <stdio.h>
#include "stack.h"

void stackInit(stack *ps) {
	ps->sp = 0;
}

void push(stack *s, stackItem num) {
	if (!isFull(*s)) {
		s->arr[s->sp++];
	}
}

stackItem pop(stack *s) {
	if (!isEmpty(*s)) {
		return s->arr[--s->sp];
	}
}

void top(stack s) {
	if (!isEmpty(s)){
		return s.arr[s.sp];
	}
}

int isEmpty(stack s) {
	return !(s.sp);
}

int isFull(stack s) {
	return s.sp == MAX_STACK_LENGTH;
}
