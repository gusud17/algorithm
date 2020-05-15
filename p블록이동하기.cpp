#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;

struct machine {
	int x1, y1, x2, y2; //1.x1<=x2   2.y1<=y2
};

int boardLen;
bool visited[100][100][2];
vector<vector<int>> board1;

//simple move
int dx[] = { 0, 0, 1, -1 };
int dy[] = { 1, -1, 0, 0 };

//rotate
vector<machine> buf;

bool outOfRange(int x) {
	return x < 0 || x >= boardLen;
}

bool isInvalid(machine mac) {
	return outOfRange(mac.x1) || outOfRange(mac.x2) || outOfRange(mac.y1) || outOfRange(mac.y2)
		|| visited[mac.x1][mac.y1][0] && visited[mac.x2][mac.y2][1]
		|| board1[mac.x1][mac.y1] == 1 || board1[mac.x2][mac.y2] == 1;
}

machine orderPos(machine mac) {
	if (mac.x1 > mac.x2) return { mac.x2, mac.y2, mac.x1, mac.y1 };
	if (mac.x1 == mac.x2 && mac.y1 > mac.y2) return { mac.x1, mac.y2, mac.x2, mac.y1 };
	return mac;
}

void rot1(machine m) { // x1 == x2
	if (m.x1 != m.x2) return;
	int nx = m.x1;
	if (nx > 0) {
		if (!board1[nx - 1][m.y2]) { //중간경로 확인
			buf.push_back({ nx - 1, m.y1, nx, m.y1});
		}
		if (!board1[nx - 1][m.y1]) {
			buf.push_back({ nx - 1, m.y2, nx, m.y2 });
		}
	}
	nx++;
	if (nx < boardLen) {
		if (!board1[nx][m.y2]) {
			buf.push_back({ nx - 1, m.y1, nx, m.y1 });
		}
		if (!board1[nx][m.y1]) {
			buf.push_back({ nx - 1, m.y2, nx, m.y2 });
		}
	}
}

void rot2(machine m) { // y1 == y2
	if (m.y1 != m.y2) return;
	int ny = m.y1;
	if (ny > 0) {
		if (!board1[m.x2][ny - 1]) { //중간경로 확인
			buf.push_back({ m.x1, ny - 1, m.x1, ny });
		}
		if (!board1[m.x1][ny - 1]) {
			buf.push_back({ m.x2, ny - 1, m.x2, ny });
		}
	}
	ny++;
	if (ny < boardLen) {
		if (!board1[m.x2][ny]) {
			buf.push_back({ m.x1, ny - 1, m.x1, ny });
		}
		if (!board1[m.x1][ny]) {
			buf.push_back({ m.x2, ny - 1, m.x2, ny });
		}
	}
}

void checkVisit(machine m) {
	visited[m.x1][m.y1][0] = true;
	visited[m.x2][m.y2][1] = true;
}

bool isEnd(machine m) {
	return m.x2 == boardLen - 1 && m.y2 == boardLen - 1;
}

int solution(vector<vector<int>> board) {
	boardLen = board.size();
	board1 = board;
	
	machine m = { 0, 0, 0, 1 };
	checkVisit(m);
	queue<pair<machine, int>> q;
	q.push(make_pair(m, 0));

	while (!q.empty()) {
		pair<machine, int> cur = q.front();
		m = cur.first;
		if (isEnd(cur.first)) return cur.second;
		q.pop();

		buf.clear();

		//simple move
		for (int i = 0; i < 4; i++) {
			buf.push_back(orderPos({ m.x1 + dx[i], m.y1 + dy[i], m.x2 + dx[i], m.y2 + dy[i] }));
		}

		//rotate
		rot1(cur.first);
		rot2(cur.first);

		for (int i = 0; i < buf.size(); i++) {
			if (isInvalid(buf[i])) continue;

			checkVisit(buf[i]);
			q.push(make_pair(buf[i], cur.second + 1));
		}
	}
	return -1;
}

int main() {
	vector<vector<int>> board = { {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}} };
	cout << solution(board) << endl;
	return 0;
}