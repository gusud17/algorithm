#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

string input;

string solution() {
	stack<char> s1;
	vector<char> s2;

	for (int i = 0; i < input.length(); i++) {
		if (input[i] == '<') {
			if (s1.size() > 0) {
				s2.push_back(s1.top());
				s1.pop();
			}
		}
		else if (input[i] == '>') {
			if (s2.size() > 0) {
				s1.push(s2[s2.size() - 1]);
				s2.pop_back();
			}
 		}
		else if (input[i] == '-') {
			if (s1.size() > 0) {
				s1.pop();
			}
		}
		else {
			s1.push(input[i]);
		}
	}

	string ret(s2.begin(), s2.end());
	while (s1.size() > 0) {
		ret += s1.top();
		s1.pop();
	}
	reverse(ret.begin(), ret.end());
	return ret;
}

int main() {
	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		cin >> input;
		cout << solution() << endl;
	}

	return 0;
}