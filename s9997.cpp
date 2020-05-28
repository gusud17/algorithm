#include <iostream>

using namespace std;

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		int angle;
		cin >> angle;

		int h = angle / 30;
		angle %= 30;

		int m = angle * 2;

		cout << "#" << i << " " << h << " " << m << endl;
	}

	return 0;
}