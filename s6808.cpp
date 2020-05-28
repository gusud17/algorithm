#include <iostream>
#include <string.h>
#include <vector>
using namespace std;

int cards[9];
vector<int> opponent;

int win, lose;
bool cardCheck[19];

void go(int cardCnt, int opScore, int myScore) {
	if (cardCnt == 9 || myScore > 86 || opScore > 86) {
		int cnt = 1;
		for (int i = 1; i <= 9 - cardCnt; i++) {
			cnt *= i;
		}

		if (myScore > opScore) win += cnt;
		else if (myScore < opScore) lose += cnt;

		return;
	}

	for (int i = 1; i <= 18; i++) {
		if (cardCheck[i]) continue;
		cardCheck[i] = true;

		int score = i + cards[cardCnt];
		
		if (i > cards[cardCnt]) {
			go(cardCnt + 1, opScore + score, myScore);
		}
		else if (i < cards[cardCnt]) {
			go(cardCnt + 1, opScore, myScore + score);
		}
		else {
			go(cardCnt + 1, opScore, myScore);
		}

		cardCheck[i] = false;
	}
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		win = 0, lose = 0;
		memset(cardCheck, false, sizeof(cardCheck));
		
		for (int j = 0; j < 9; j++) {
			cin >> cards[j];
			cardCheck[cards[j]] = true;
		}
		go(0, 0, 0);
		
		cout << "#" << i << " " << win << " " << lose << endl;
	}

	return 0;
}