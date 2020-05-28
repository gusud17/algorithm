#include <iostream>
#include <algorithm>

using namespace std;

int N;
int nums[1000];

bool check(int n) {
	int t = n % 10;
	n /= 10;
	while (n > 0) {
		int next = n % 10;
		if (next > t) return false;
		n /= 10;
		t = next;
	}
	return true;
}

int solution() {
	sort(nums, nums + N, [](int a, int b) -> bool {
		return a > b;
	});

	int ans = -1;
	for (int i = 0; i < N; i++) {
		for (int j = i + 1; j < N; j++) {
			int n = nums[i] * nums[j];
			if (n < ans) break;
			if (check(n)) {
				ans = max(ans, n);
				break;
			}
		}
	}
	return ans;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> nums[j];
		}
		cout << "#" << i << " " << solution() << endl;
	}

	return 0;
}