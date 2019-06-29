#include <stdio.h>
//插入排序(个人复杂版本)
int main(){
	int a[10]={1,-5,34,89,110,123,-56,-78,0,18};
	for(int i=1;i<10;i++){
		int temp=a[i];//当前需要判断的元素 		
		for(int j=0;j<i;j++){
			if(a[j]>a[i]){
				//保存原位置值 
				int temp = a[i]; 
				//原位置以后的值后移
				for(int z=i;z>j;z--){
					a[z] = a[z-1];
				} 
				a[j] = temp;
			}
		}
	} 
	for(int i=0;i<10;i++){
		printf("%d   ",a[i]); 
	}	
}