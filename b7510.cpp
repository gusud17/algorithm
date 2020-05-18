#include <iostream>

using namespace std;

int N;

bool go(int cur, int val) {
	val += cur;

	if (val == N) return true;
	if (val > N) return false;
	return go(cur + 1, val);
}

int solution() {
	if (N == 1) return 1;

	int n = N / 2 + N % 2;
	int ret = 1;
	
	for (int i = 1; i <= n; i++) {
		if (go(i, 0)) ret++;
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}