#include <iostream>

using namespace std;

int S, E, M;

int nextVal(int cur, int plus, int max) {
	int ret = (cur + plus) % max;
	return ret ? ret : max;
}

long solution() {
	long ret = S;
	int s = S, e = nextVal(0, S, 24), m = nextVal(0, S, 29);

	while (e != E || m != M) {
		e = nextVal(e, 365, 24);
		m = nextVal(m, 365, 29);
		ret += 365;
	}

	return ret;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> S >> E >> M;
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}