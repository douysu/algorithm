#include <stdio.h>
//µİ¹é¡ª¡ªFibonacciÊıÁĞ
int f(int n){
	if(n==1 || n==0){
		return 1;
	}
	return f(n-1)+f(n-2);
}
int main(){
	int n=3;
	printf("%d",f(n));	
	return 0;
} 