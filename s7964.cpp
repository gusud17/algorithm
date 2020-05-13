#include <iostream>

using namespace std;

int N, D;
bool door[300000];

int solution() {
	int ret = 0;
	int pos = 1, dist = 0;
	
	while (pos < N) {
		if (!door[pos]) {
			if (++dist == D) {
				dist = 0;
				ret++;
			}
		}
		else dist = 0;

		pos++;
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N >> D;
		
		door[0] = true;
		N++;
		for (int j = 1; j < N; j++) {
			cin >> door[j];
		}

		cout << "#" << i << " " << solution() << endl;
	}

	return 0;
}