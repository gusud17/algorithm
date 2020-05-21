#include <iostream>

using namespace std;

int N;
string X;

int solution() {
	int ret = 0;
	int len = X.length();
	for (int i = 0; i < len; i++) {
		ret += X[i] - '0';
	}
	return ret % (N - 1);
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N >> X;
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}