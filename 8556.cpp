#include <iostream>
#include <string>
using namespace std;

string dirStr;
int mom, son;

string retToStr() {
	while (son > 0 && son % 2 == 0 && mom >= 2) {
		son /= 2;
		mom /= 2;
	}

	if (mom > 1) {
		return to_string(son) + "/" + to_string(mom);
	}
	return to_string(son);
}

string solution() {
	mom = 1;
	son = 0;

	if (dirStr[dirStr.length() - 1] == 'h') {
		dirStr = dirStr.substr(0, dirStr.length()-5);
	}
	else {
		son += 90;
		dirStr = dirStr.substr(0, dirStr.length()-4);
	}
	
	while (dirStr.size() > 0) {
		mom *= 2;
		son *= 2;
		if (dirStr[dirStr.length()-1] == 'h') {
			son -= 90;
			dirStr = dirStr.substr(0, dirStr.length() - 5);
			continue;
		}
		son += 90;
		dirStr = dirStr.substr(0, dirStr.length() - 4);
	}

	return retToStr();
}


int main() {
	int t;
	cin >> t;

	for (int i = 1; i <= t; i++) {
		cin >> dirStr;
		cout << "#" << i << " " << solution() << endl;
	}
	return 0;
}