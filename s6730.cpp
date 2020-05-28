#include <iostream>

using namespace std;

int N;
int height[100];

int up, down;

void solution() {
	int bef = height[0];

	for (int i = 1; i < N; i++) {
		int cur = height[i] - bef;

		if (cur > 0 && up < cur) {
			up = cur;
		}
		else if (cur < 0) {
			cur *= -1;
			if (down < cur) down = cur;
		}
		bef = height[i];
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		up = 0, down = 0;

		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> height[j];
		}
		solution();

		cout << "#" << i << " " << up << " " << down << endl;
	}
}