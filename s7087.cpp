#include <iostream>
#include <algorithm>

using namespace std;

int N;
string titles[100];

int solution() {
	sort(titles, titles + N);

	int p = 'A', ret = 0;
	for (int i = 0; i < N; i++) {
		if (p != titles[i++][0]) break;
		while (p == titles[i++][0]) {
			if (i == N) break;
		}
		i -= 2;
		p++;
		ret++;
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> titles[j];
		}
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}