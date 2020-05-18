#include <iostream>

using namespace std;

int N, M;
int map[100][100]; 

int time, cheese;

int dn[] = { 0, 0, -1, 1 };
int dm[] = { -1, 1, 0, 0 };
bool visited[100][100];

bool outOfRange(int n, int m) {
	return (n < 0 || m < 0 || n >= N || m >= M);
}

void checkOutsideAir(int n, int m) {//-1은 외부 공기 (0은 내부, 1은 치즈)
	map[n][m] = -1;
	visited[n][m] = true;

	for (int i = 0; i < 4; i++) {
		int nn = n + dn[i], nm = m + dm[i];
		if (outOfRange(nn, nm) || visited[nn][nm] || map[nn][nm]!= 0) continue;

		checkOutsideAir(nn, nm);
	}
}

void checkHole(int n, int m) {
	for (int i = 0; i < 4; i++) {
		int nn = n + dn[i], nm = m + dm[i];
		if (map[nn][nm] == 0) checkOutsideAir(nn, nm);
	}
}

int goCheese(int n, int m) {
	visited[n][m] = true;

	int ret = 1;
	for (int i = 0; i < 4; i++) {
		int nn = n + dn[i], nm = m + dm[i];
		
		if (visited[nn][nm]) continue;
		
		if (map[nn][nm] == -1) {
			map[n][m] = -1;
			checkHole(n, m);
		}

		if (map[nn][nm] == 1) ret += goCheese(nn, nm);
	}
	return ret;
}

void solution() {
	checkOutsideAir(0, 0);
	
	while (true) {
		memset(visited, false, sizeof(visited));
		int cheeseCnt = 0;
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					cheeseCnt += goCheese(i, j);
				}
			}
		}
		if (cheeseCnt == 0) break;
		time++;
		cheese = cheeseCnt;
	}
}

int main() {
	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}

	solution();

	cout << time << endl;
	cout << cheese << endl;

	return 0;
}