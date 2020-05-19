#include <iostream>

using namespace std;

int N;
int pos[20][2];
bool check[20];
long long ans;

long long calAns() {
	long long x = 0, y = 0;
	for (int i = 0; i < N; i++) {
		if (check[i]) {
			x += pos[i][0];
			y += pos[i][1];
		}
		else {
			x -= pos[i][0];
			y -= pos[i][1];
		}
	}
	return x * x + y * y;
}

void go(int index, int cnt) {
	if (cnt == N / 2) {
		long long tmp = calAns();
		if (ans == -1 || tmp < ans) {
			ans = tmp;
		}
		return;
	}

	for (int i = index; i < N; i++) {
		check[i] = true;
		go(i + 1, cnt + 1);
		check[i] = false;
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> pos[j][0] >> pos[j][1];
		}
		ans = -1;
		go(0, 0);
		cout << "#" << i << " " << ans << endl;
	}
	return 0;
}