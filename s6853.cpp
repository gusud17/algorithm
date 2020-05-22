#include <iostream>
#include <string.h>

using namespace std;

int X1, Y1, X2, Y2;
int N;

int ans[3]; //in on out

int checkPos(int x, int y) {
	if (x < X1 || x > X2 || y < Y1 || y > Y2) {
		return 2;
	}

	if (x == X1 || y == Y1 || x == X2 || y == Y2) {
		return 1;
	}

	return 0;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> X1 >> Y1 >> X2 >> Y2;
		cin >> N;
		memset(ans, 0, sizeof(ans));

		for (int j = 0; j < N; j++) {
			int x, y;
			cin >> x >> y;
			ans[checkPos(x, y)]++;
		}
		cout << "#" << i << " " << ans[0] << " " << ans[1] << " " << ans[2]<< endl;
	}

	return 0;
}