#include <stdio.h>
//ШЋЪ§Са
const int maxn=11;
int n,P[maxn],hashTable[maxn]={false};
void f(int index){
	if(index==n+1){
		for(int i=1;i<=n;i++){
			printf("%d",P[i]);
		}
		printf("\n");
		return; 
	}
	for(int x=1;x<=n;x++){
		if(hashTable[x]==false){
			P[index]=x;
			hashTable[x]=true;
			f(index+1);
			hashTable[x]=false;
		}
	}
	
}
 
int main(){
	n=3;
	f(1);
	return 0;
}
