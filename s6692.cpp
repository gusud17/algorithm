#include <iostream>

using namespace std;

int N;

float probability[20];
int pay[20];

float solution() {
	float ans = 0;

	for (int i = 0; i < N; i++) {
		ans += probability[i] * pay[i];
	}
	return ans;
}

int main() {
	cout << fixed; cout.precision(6);
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;

		for (int n = 0; n < N; n++) {
			cin >> probability[n] >> pay[n];
		}

		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}