#include <iostream>
#include <cstdio>

using namespace std;

int d;

int solution() {
	if (d >= 1000000) return 5;
	if (d >= 100000) return 4;
	if (d >= 10000) return 3;
	if (d >= 1000) return 2;
	if (d >= 100) return 1;
	return 0;
}

int main() {
	int tc;
	scanf("%d", &tc);

	for (int i = 1; i <= tc; i++) {
		scanf("%d", &d);

		printf("#%d %d\n", i, solution());
	}
	return 0;
}