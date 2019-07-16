#include <stdio.h>
#include <vector>
using namespace std;
//vector的访问方式 
int main(){
	vector<int> vi;
	for(int i=0;i<3;i++){
		vi.push_back(i);
	}
	//可以直接使用索引访问
	printf("%d\n",vi[0]); 
	
	//通过迭代器访问
	vector<int>::iterator it=vi.begin();
	for(int i=0;i<3;i++){
		printf("%d\n",*(it+i));
	}
	return 0;
}
