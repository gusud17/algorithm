#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		int N, M;
		cin >> N >> M;
		
		int score = 0, popu = 0;

		for (int n = 0; n < N; n++) {
			int cnt = 0;
			for (int m = 0; m < M; m++) {
				bool sol;
				cin >> sol;
				if (sol) cnt++;
			}

			if (cnt < score) continue;
			else if (cnt == score) popu++;
			else {
				score = cnt;
				popu = 1;
			}
		}
		cout << "#" << i << " " << popu << " " << score << endl;
	}
	return 0;
}