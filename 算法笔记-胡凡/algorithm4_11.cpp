#include <stdio.h>
#include <algorithm>
//注意输入double输入为%f 
struct mooncake{
	double store;
	double sell;
	double price;
}cake[100];
bool cmp(mooncake a,mooncake b){
	return a.price>b.price;
}

int main(){
	int n;
	double all;
	scanf("%d %lf",&n,&all);
	
	for(int i=0;i<n;i++){
		scanf("%lf",&cake[i].store);
	}
	for(int i=0;i<n;i++){
		scanf("%lf",&cake[i].sell);
		cake[i].price=cake[i].sell/cake[i].store; 
	}
	sort(cake,cake+n,cmp);
	double money=0;
	for(int i=0;i<n;i++){
		if(cake[i].store<=all){
			all-=cake[i].store;
			money+=cake[i].sell;
		}else{
			money+=all*cake[i].price;
			break;
		}
	}
	printf("%.2f\n",money);
	return 0;
}
