#include <iostream>
#include <algorithm>
using namespace std;

int R, C;
string board[20];

int dx[] = { 0, 0, -1, 1 };
int dy[] = { 1, -1, 0, 0 };
bool visited[26];

int go(int x, int y) {	
	int ret = 0;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i], ny = y + dy[i];

		if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[board[nx][ny] - 65]) continue;

		visited[board[nx][ny] - 65] = true;
		ret = max(ret, go(nx, ny));
		visited[board[nx][ny] - 65] = false;
	}
	return ret + 1;
}

int main() {
	cin >> R >> C;

	for (int i = 0; i < R; i++) {
		cin >> board[i];
	}
	visited[board[0][0] - 65] = true;
	cout << go(0, 0) << endl;

	return 0;
}