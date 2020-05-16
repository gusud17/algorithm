#include <iostream>

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int weakSize;
vector<int> helper;

int getDiff(int right, int left, int n) {
	if (right >= left) return right - left;
	return n - left + right;
}

bool help(int n, vector<int> weak) {
	sort(helper.begin(), helper.end());

	do {
		int sp = 0;

		while (sp < weakSize) {
			int left = sp, right = sp;
			int cnt = 0;

			for (int hp = 0; hp < helper.size(); hp++) {
				while (getDiff(weak[right], weak[left], n) <= helper[hp]) {
					right = (right + 1) % weakSize;
					cnt++;
					if (cnt == weakSize) return true;
				}

				left = right;
			}

			sp += 1;
		}
	} while (next_permutation(helper.begin(), helper.end()));

	return false;
}

int solution(int n, vector<int> weak, vector<int> dist) {
	weakSize = weak.size();
	sort(dist.begin(), dist.end());

	while (!dist.empty()) {
		helper.push_back(dist.back());
		dist.pop_back();

		if (help(n, weak))  return helper.size();
	}

	return -1;
}

int main() {
	vector<int> weak = { 1, 3, 4, 9, 10 };
	vector<int> dist = { 3, 5, 7 };
	cout << solution(12, weak, dist) << endl;
	return 0;
}