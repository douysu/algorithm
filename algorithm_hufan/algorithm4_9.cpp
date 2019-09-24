#include <stdio.h>
#include <math.h>
const int maxn=11;
int n,count=0,P[maxn],hashTable[maxn]={false};
void genf(int index){
	if(index == n+1){
		//判断两个是否在同一个对角线上
		//遍历任意两个皇后
		bool flag = true;
		for(int i=1;i<=n;i++){
			for(int j=i+1;j<=n;j++){
				//遍历所有，如果有重复的就会+1 
				if(abs(i-j)==abs(P[i]-P[j])){//如果在一条对角线上 
					flag=false;	
				}
			}
		}
		if(flag)count++;
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
	genf(1);
	printf("%d\n",count);
	return 0;
}
