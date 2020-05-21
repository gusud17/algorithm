#include <iostream>
#include <vector>

using namespace std;

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		int N, M;
		cin >> N >> M;

		if (++M < ++N) {
			int tmp = N;
			N = M;
			M = tmp;
		}

		cout << "#" << i;
		while (N <= M) {
			cout << " " << N++;
		}
		cout << endl;
	}
	return 0;
}