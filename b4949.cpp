#include <iostream>
#include <string>
#include <stack>
using namespace std;

string input;

bool solution() {
	stack<char> st;
	
	int len = input.length();
	for (int i = 0; i < len; i++) {
		if (input[i] == '(' || input[i] == '[') {
			st.push(input[i]);
		}
		else if (input[i] == ')' || input[i] == ']') {
			if (st.empty() || input[i] / 10 != st.top() / 10) return false;
			st.pop();
		}
	}

	return st.empty();
}

bool isEnd() {
	return input.length() == 1 && input[0] == '.';
}

int main() {
	while (true) {
		getline(cin, input);

		if (isEnd()) break;

		if (solution()) cout << "yes" << endl;
		else cout << "no" << endl;
	}
	return 0;
}