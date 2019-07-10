#include <stdio.h>
#include <math.h>
const int maxn=11;
int n,count=0,P[maxn],hashTable[maxn]={false};
void genf(int index){
	if(index == n+1){
		//判断两个是否在同一个对角线上
		//遍历任意两个皇后
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				if((i-j)!=abs(P[i]-P[j])){
					count++;
				}
			}
		}
		return;
	}
	for(int x=1;x<=n;x++){
		if(hashTable[x]==false){
			P[index]=x;
			hashTable[x]=true;
			genf(index+1);
			hashTable[x]=false;
		}
	}
}
int main(){
	n=8;
	genf(n);
	printf("%d",count);
	return 0;
}
