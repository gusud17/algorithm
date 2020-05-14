#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
int nums[1000];

vector<int> LIS() {
	vector<int> ret(N, 0);

	vector<int> tmp;
	tmp.push_back(nums[0]);

	for (int i = 1; i < N; i++) {
		if (nums[i] > tmp.back()) {
			tmp.push_back(nums[i]);
			ret[i] = ret[i - 1] + 1;
		}
		else {
			auto it = lower_bound(tmp.begin(), tmp.end(), nums[i]);
			*it = nums[i];
			ret[i] = ret[i - 1];
		}
	}
	return ret;
}

int solution() {
	vector<int> asc = LIS();
	reverse(nums, nums + N);
	vector<int> desc = LIS();
	
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, asc[i] + desc[N - i - 1]);
	}
	return ans + 1;
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> nums[i];
	}
	cout << solution() << endl;
	return 0;
}