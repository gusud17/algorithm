#include <iostream>
#include <algorithm>
#include <queue>
#include <string.h>
using namespace std;

int N, K;
int time[1001];
bool nextBuild[1001][1001];

int indegree[1001];
int res[1001];

void solution() {
	queue<int> q;

	for (int i = 1; i <= N; i++) {
		if (indegree[i] == 0) {
			res[i] = time[i];
			q.push(i);
		}
	}

	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		for (int i = 1; i <= N; i++) {
			if (nextBuild[cur][i]) {
				res[i] = max(res[i], res[cur] + time[i]);

				if (--indegree[i] == 0) {
					q.push(i);
				}
			}
		}
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 0; i < tc; i++) {
		memset(indegree, 0, sizeof(indegree));
		memset(nextBuild, false, sizeof(nextBuild));
		memset(res, 0, sizeof(res));

		cin >> N >> K;
		
		for (int j = 1; j <= N; j++) {
			cin >> time[j];
		}
		for (int j = 0; j < K; j++) {
			int a, b;
			cin >> a >> b;
			nextBuild[a][b] = true;
			indegree[b]++;
		}
		solution();
		int target;
		cin >> target;
		cout << res[target] << endl;
	}

	return 0;
}