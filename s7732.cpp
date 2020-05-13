#include <iostream>
#include <string>

using namespace std;

string cur, target;

int strToSec(string str) {
	return 
		atoi(str.substr(0, 2).c_str()) * 60 * 60 + 
		atoi(str.substr(3, 5).c_str()) * 60 + 
		atoi(str.substr(6, 8).c_str());
}

string toStr(int input) {
	string prefix = input < 10 ? "0" : "";
	return prefix + to_string(input);
}

string secToStr(int input) {
	string sec = toStr(input % 60);
	input /= 60;
	string min = toStr(input % 60);
	input /= 60;
	string hour = toStr(input % 60);

	return hour + ":" + min + ":" + sec;
}

string solution() {
	int curSec = strToSec(cur);
	int targetSec = strToSec(target);

	if (targetSec < curSec) targetSec += 24 * 60 * 60;

	return secToStr(targetSec - curSec);
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		cin >> cur >> target;
		cout << "#" << i << " " << solution() << endl;
	}

	return 0;
}