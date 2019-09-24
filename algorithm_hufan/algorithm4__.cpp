#include <stdio.h>
//ц╟ещеепР 
int main(){
	int a[10]={1,-5,34,89,110,123,-56,-78,0,18};
	for(int i=0;i<10;i++){
		for(int j=i+1;j<9;j++){
			if(a[i]>a[j]){
				int t=a[i];
				a[i] = a[j];
				a[j] = t; 
			} 
		}
	}
	for(int i=0;i<10;i++){
		printf("%d   ",a[i]); 
	}
}
