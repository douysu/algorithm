#include <stdio.h>
//散列初步，空间换时间，这点空间对于计算机来说都不算什么，检测数组M、N中是否出现过 
 int main(){
 	const int maxn=10010; 
 	bool hashTable[maxn]={false};
	int m,n,x;
	scanf("%d %d",&m,&n);
	for(int i=0;i<m;i++){
		scanf("%d",&x);
		hashTable[x]=true;
	}
	for(int i=0;i<n;i++){
		scanf("%d",&x);
		if(hashTable[x]==true){
			printf("重复过");
		}else{
			printf("没有重复");
		}
	} 
 }