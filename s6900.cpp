#include <iostream>

using namespace std;

int N, M;

string winning[100];
int rewards[100];

int getReward(string lotto) {
	int reward = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 8; j++) {
			if (winning[i][j] != '*' && winning[i][j] != lotto[j]) {
				break;
			}
			if (j == 7) reward += rewards[i];
		}
	}
	return reward;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N >> M;

		for (int n = 0; n < N; n++) {
			cin >> winning[n] >> rewards[n];
		}

		int reward = 0;
		for (int m = 0; m < M; m++) {
			string lotto;
			cin >> lotto;
			reward += getReward(lotto);
		}

		cout << "#" << i << " " << reward << endl;
	}
	return 0;
}