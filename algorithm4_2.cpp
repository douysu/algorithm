#include <stdio.h>
//≤Â»Î≈≈–Ú
int main(){
	int a[10]={1,-5,34,89,110,123,-56,-78,0,18};
	for(int i=1;i<10;i++){
		int temp = a[i],j = i;
		while(j > 0 && temp < a[j-1]){
			a[j] = a[j-1];
			j--;
		}
		a[j] = temp;
	} 
	for(int i=0;i<10;i++){
		printf("%d   ",a[i]); 
	}	
}