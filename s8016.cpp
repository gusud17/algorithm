#include <iostream>

using namespace std;

int main() {
	int tc;
	cin >> tc;

	long long N;
	for (int i = 1; i <= tc; i++) {
		cin >> N;
		long long ans1 = 1 + 2* (N - 1) * (N-1);
		long long ans2 = 2 * N * N - 1;
		cout << "#" << i << " " << ans1 << " " << ans2 << endl;
	}
	return 0;
}