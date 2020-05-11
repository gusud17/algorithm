#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

int N;
pair<int, int> pos[102];
vector<int> graph[102];
bool visited[102];

int diff(pair<int, int> r1, pair<int, int> r2) {
	return abs(r1.first - r2.first) + abs(r1.second - r2.second);
}

void makeGraph() {
	for (int i = 0; i < N; i++) {
		for (int j = i + 1; j < N; j++) {
			if (diff(pos[i], pos[j]) <= 1000) {
				graph[i].push_back(j);
				graph[j].push_back(i);
			}
		}
	}
}

bool go(int index) {
	for (int i = 0; i < graph[index].size(); i++) {
		int next = graph[index][i];
		if (next == N - 1) return true;

		if (visited[next]) continue;
		visited[next] = true;
		if (go(next)) return true;
	}
	return false;
}

void cleanGraph() {
	for (int i = 0; i < N; i++) {
		graph[i].clear();
	}
}

bool solution() {
	makeGraph();
	memset(visited, false, sizeof(visited));
	visited[0] = true;
	return go(0);
}

int main() {
	int t;
	cin >> t;

	while (t--) {
		cin >> N;
		N += 2;
		for (int i = 0; i < N; i++) {
			cin >> pos[i].first >> pos[i].second;
		}

		if (solution()) cout << "happy"<<endl;
		else cout << "sad" << endl;

		cleanGraph();
	}

	return 0;
}