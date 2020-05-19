#include <iostream>

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct food {
	int time, index;
};

vector<food> nFoodTimes;

int solution(vector<int> food_times, long long k) {
	int len = food_times.size();
	for (int i = 0; i < len; i++) {
		nFoodTimes.push_back({food_times[i], i + 1});
	}

	sort(nFoodTimes.begin(), nFoodTimes.end(), [](food f1, food f2) -> bool {
		if (f1.time == f2.time) return f1.index < f2.index;
		return f1.time < f2.time;
	});

	for (int i = 0; i < len;) {
		int base = i ? nFoodTimes[i - 1].time : 0;
		long long eatingTime = ((long long)nFoodTimes[i].time - base) * (len - i);

		if (k < eatingTime) {
			sort(nFoodTimes.begin() + i, nFoodTimes.end(), [](food f1, food f2) -> bool {
				return f1.index < f2.index;
			});

			return nFoodTimes[i + k % (len - i)].index;
		}

		while (base >= nFoodTimes[++i].time);
		k -= eatingTime;
	}

	return -1;
}

int main() {
	vector<int> f = { 3, 1, 2 };
	int k = 5;
	cout << solution(f, k) << endl;
	return 0;
}