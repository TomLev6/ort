#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>



void q1(int num);
void q2(char s1, char s2, char s3);
void q3(char name[], float age, float youngerAge);
void q4(char tav);
int q5(int n);
void q6(float a, float b, float c, float d);
void q7();
void q8();
void q9(int num);
void q10(int a, int b, int c);
int main()
{
    //q1(1);
    //q2('a', 'b', 'c');
    //q3("tom",10.5,8.5);
    //q4("$");
    //printf("%d",q5(5));
    //q6(4.999, 6.1, 5.4, 1.02);
    //q7();
    //q8();
    //q9(123);
    //q10(0, 0, 1);
    return 0;

}
void swap(float* a, float* b) {
    float temp = *a;
    *a = *b;
    *b = temp;
}
void q1(int num)
{
    printf("%d\n", num);
    printf("%d\n", num + 2);
    printf("%d\n", num * 3);
    printf("%d\n", num + 2 + num * 3 + num);
}
void q2(char s1, char s2, char s3)
{
    printf("%c %c %c\n", s1, s2, s3);
    printf("%c %c %c", s3, s2, s1);
}
void q3(char name[], float age, float youngerAge)
{
    printf("%s %f", name, age - youngerAge);
}
void q4(char ch)
{
    printf("%c\n", ch);
    printf("%c %c\n", ch, ch);
    printf("%c %c %c\n", ch, ch, ch);
}
int q5(int n)
{
    int sum = 1;
    int i = n;
    for (i = 1; i <= n; i++)
    {
        sum *= i;
    }
    return sum;
}
void q6(float a, float b, float c, float d)
{
    if (a > b) {
        swap(&a, &b);
    }
    if (a > c) {
        swap(&a, &c);
    }
    if (a > d) {
        swap(&a, &d);
    }
    if (b > c) {
        swap(&b, &c);
    }
    if (b > d) {
        swap(&b, &d);
    }
    if (c > d) {
        swap(&c, &d);
    }
    printf("Sorted numbers: A=%.2f, B=%.2f, C=%.2f, D=%.2f\n", a, b, c, d);
}
void q7()
{
    int x = 5;
    float y;
    y = (float)x / 4;
    printf("%f", y);
}
void q8()
{
    int x = 6;
    float y = (float)x / 10;
    printf("%f", (y - 10));
}
void q9(int num)
{
    
    printf("%d\n", num % 10);
    num /= 10;
    printf("%d\n", num % 10);
    num /= 10;
    printf("%d", num % 10);
    
}
void q10(int a, int b, int c)
{
    if (a > 0) 
    {
        a *= 100;
        b *= 10;
    }
    else if (b > 0)
    {       
        b *= 100;
        a *= 10;
    }
        else
        {
            c *= 100;
            b *= 10;
        }

    printf("%d", (a + b + c));

}