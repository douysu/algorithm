#include <stdio.h>
//三个大写字母，给出N个字符串，给出M个查询字符串，问每个查询字符串在N个字符串中出现的次数 
const int maxn=100;
char S[maxn][5],temp[5];
int hashTable[26*26*26+10];
int hashFunc(char s[],int len){
	int id=0;
	for(int i=0;i<len;i++){
		id=id*26+(s[i]-'A');
	}
	printf("%d",id);
	return id;
}
int main(){
	int m,n;
	scanf("%d%d",&m,&n);
	for(int i=0;i<n;i++){
		scanf("%s",S[i]);
		int id=hashFunc(S[i],3);
		hashTable[id]++;
	}
	for(int i=0;i<m;i++){
		scanf("%d",&temp);
		int id=hashFunc(temp,3);
		printf("%d\n",hashTable[id]);
	}
	return 0;
}