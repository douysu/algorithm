#include <stdio.h>
#include <string.h>
#include <algorithm>
using namespace std;
// PAT A1025 PAT Ranking
struct Student{
	char id[15];
	int score;
	int location_number;
	int local_rank;
}stu[30010];
bool cmp(Student a,Student b){
	if(a.score!=b.score) return a.score>b.score;
	else return strcmp(a.id,b.id)<0;
}
int main(){
	int n,k,num=0,groupId=1;
	scanf("%d",&n);
	for(int i=0;i<n;i++){
		scanf("%d",&k);
		for(int j=0;j<k;j++,num++){
			scanf("%s %d",&stu[num].id,&stu[num].score);
			stu[num].location_number = groupId;
		}
		groupId++;
		
		//对当前组排序 
		sort(stu+num-k,stu+num,cmp);
		//第一名组内排名为第一
		stu[num-k].local_rank = 1; 
		//有可能是并列
		for(int j=num-k+1;j<num;j++){
			if(stu[j].score==stu[j-1].score){
				stu[j].local_rank = stu[j-1].local_rank;
			}else{
				stu[j].local_rank = k-num+j+1;
			}
		}
	}
	
	
	printf("%d\n",num);
	sort(stu,stu+num,cmp);
	int rankAllId=1;
	for(int i=0;i<num;i++){
		if(i>0&&stu[i].score!=stu[i-1].score){
			rankAllId=i+1;
		}
		printf("%s %d %d %d\n",stu[i].id,rankAllId,stu[i].location_number,stu[i].local_rank);	
	}
}