#include <iostream>
#include <vector>
using namespace std;

string input;

long long solution() {
	long long ret = 1;

	int len = input.length();
	if (input[0] != input[1]) ret *= 2;
	if (input[len - 1] != input[len - 2]) ret *= 2;

	for (int i = 1; i < len - 1; i++) {
		int tmp = 1;
		if (input[i] != input[i - 1]) tmp++;
		if (input[i] != input[i + 1] && input[i - 1] != input[i + 1]) tmp++;

		ret = (tmp * ret) % 1000000007;
	}
	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> input;
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}