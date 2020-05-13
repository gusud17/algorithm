#include <iostream>
#include <algorithm>

using namespace std;

int n;
int nums[1000];

bool isValid(int num) {
	int right = num % 10;
	num /= 10;
	while (num > 0) {
		int left = num % 10;
		if (right != left + 1) return false;
		right = left;
		num /= 10;
	}
	return true;
}

int solution() {
	int ret = -1;
	for (int i = 0; i < n - 1; i++) {
		for (int j = i + 1; j < n; j++) {
			int tmp = nums[i] * nums[j];
			if (isValid(tmp)) ret = max(ret, tmp);
		}
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> n;
		for (int j = 0; j < n; j++) {
			cin >> nums[j];
		}
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}