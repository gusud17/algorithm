#include <iostream>

using namespace std;

int ans[1001];

int solution(int n) {
	if (ans[n] != 0) return ans[n];
	ans[n] = (solution(n - 1) + solution(n - 2))%10007;
	return ans[n];
}

int main() {
	ans[1] = 1;
	ans[2] = 2;
	int N;
	cin >> N;
	cout << solution(N) << endl;

	return 0;
}