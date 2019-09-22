#include <stdio.h>
#include <math.h>
const int maxn=11;
int n,count=0,P[maxn],hashTable[maxn]={false};
void genf(int index){
	//如果可以进入一定满足条件 
	if(index == n+1){
		count++;
		return; 
	}
	for(int x=1;x<=n;x++){
		//在每次给之前都判断是否与前面的皇后重复，不用在想着遍历 
		if(hashTable[x]==false){
			bool flag=false;//没有重复
			//!!!!注意此处不可出现index-index自己的情况 
			for(int pre=1;pre<index;pre++){
				if(abs(index-pre)==abs(x-P[pre])){
					flag=true;
					break;
				}
			}
		 	if(!flag){
			 	P[index]=x;
				hashTable[x]=true;
				genf(index+1);
				hashTable[x]=false;	
		 	}
		}
	}
}
int main(){
	n=8;
	genf(1);
	printf("%d\n",count);
	return 0;
}
