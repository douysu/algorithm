#include <stdio.h>
//—°‘Ò≈≈–Ú
int main(){
	int a[10]={1,-5,34,89,110,123,-56,-78,0,18};
	for(int i=0;i<10;i++){
		for(int j=i;j<9;j++){
			int min=a[j];
			int index;
			if(a[j]>a[j+1]){
				min=a[j+1];
				index = j+1;
			}
			int t = a[i];
			a[i] = a[index];
			a[index] = t;
		}
	}
	for(int i=0;i<10;i++){
		printf("%d   ",a[i]); 
	}
}
