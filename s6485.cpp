#include <iostream>

using namespace std;

int N, P;
int A[500], B[500], C[500];
int res[500];

void solution() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < P; j++) {
			if (A[i] <= C[j] && B[i] >= C[j]) res[j]++;
		}
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> N;
		for (int j = 0; j < N; j++) {
			cin >> A[j] >> B[j];
		}
		cin >> P;
		for (int j = 0; j < P; j++) {
			cin >> C[j];
		}
		solution();

		cout << "#" << i ;
		for (int j = 0; j < P; j++) {
			cout << " " << res[j];
			res[j] = 0;
		}
		cout << endl;
	}
	return 0;
}