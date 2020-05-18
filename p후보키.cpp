#include <iostream>

#include <string>
#include <vector>
#include <queue>
using namespace std;

vector<vector<string>> relation1;
vector<vector<int>> keySet;

bool contains(vector<vector<string>> rows, vector<string> row) {
	for (int i = 0; i < rows.size(); i++) {
		bool isEqual = true;
		for (int j = 0; j < row.size(); j++) {
			if (rows[i][j].compare(row[j])) {
				isEqual = false;
				break;
			}
		}
		if (isEqual) return true;
	}
	return false;
}

bool isValid(vector<int> tmpKey) {
	vector<vector<string>> tmp;

	for (int i = 0; i < relation1.size(); i++) {
		vector<string> cur;
		for (int j = 0; j < tmpKey.size(); j++) {
			cur.push_back(relation1[i][tmpKey[j]]);
		}
		if (contains(tmp, cur)) return false;
		tmp.push_back(cur);
	}
	return true;
}

bool isSubset(vector<int> subset, vector<int> bigger) {
	for (int i = 0; i < subset.size(); i++) {
		if (find(bigger.begin(), bigger.end(), subset[i]) != bigger.end()) {
			return false;
		}
	}
	return true;
}

bool isExpired(vector<int> tmpKey) {
	for (int i = 0; i < keySet.size(); i++) {
		if (isSubset(keySet[i], tmpKey)) return true;
	}
	return false;
}

int solution(vector<vector<string>> relation) {
	int columnNum = relation[0].size();
	relation1 = relation;

	int ret = 0;

	queue<vector<int>> q;
	for (int i = 0; i < columnNum; i++) {
		q.push({ i });
	}

	while (!q.empty()) {
		vector<int> tmpKey = q.front();
		q.pop();

		if (isExpired(tmpKey)) continue;

		if (isValid(tmpKey)) {
			keySet.push_back(tmpKey);
			ret++;
			continue;
		}

		for (int i = tmpKey.back() + 1; i < columnNum; i++) {
			vector<int> next = tmpKey;
			next.push_back(i);
			q.push(next);
		}
	}
	return ret;
}

int main() {
	vector<vector<string>> relation =
/*	{ {"b","2","a","a","b"},
{"b","2","7","1","b"},
{"1","0","a","a","8"},
{"7","5","a","a","9"},
{"3","0","a","f","9"} }; */
		{{"100", "ryan", "music", "2"}, 
		{"200", "apeach", "math", "2"}, 
		{"300", "tube", "computer", "3"}, 
		{"400", "con", "computer", "4"}, 
		{"500", "muzi", "music", "3"}, 
		{"600", "apeach", "music", "2"}};

	cout << solution(relation) << endl;
}