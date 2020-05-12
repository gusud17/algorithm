#include <iostream>

using namespace std;

int N;

int cols[15];

bool isValid(int x, int y) {
	for (int i = 0; i < x; i++) {
		if (y == cols[i] || abs(x-i) == abs(y- cols[i])) return false;
	}
	return true;
}

int go(int row) {
	if (row == N) return 1;

	int ret = 0;
	for (int i = 0; i < N; i++) {
		if (isValid(row, i)) {
			cols[row] = i;
			ret += go(row + 1);
		}
	}
	return ret;
}

int main() {
	cin >> N;
	cout << go(0);
	return 0;
}