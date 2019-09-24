#include <stdio.h>
int main(){
	const int maxn=100000;
	int n;
	scanf("%d",&n);
	int allScore[maxn]={0};
	int id,score; 
	for(int i=0;i<n;i++) 
	{
		scanf("%d%d",&id,&score);
		allScore[id-1]+=score;
	} 
	int k=-1,max=-1;
	for(int i=0;i<n;i++){
		if(allScore[i]>max){
			max=allScore[i];
			k=i+1;
		}
	}
	printf("%d %d",k,max);
}
