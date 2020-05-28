#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>

using namespace std;

string nums, colors;

int cards[3][9];

int getColor(char color) {
	if (color == 'R') return 0;
	if (color == 'G') return 1;
	return 2;
}

bool solution() {
	for (int i = 0; i < 9; i++) {
		int color = getColor(colors[i]);
		cards[color][nums[i] - '1']++;
	}

	for (int col = 0; col < 3; col++) {
		for (int i = 0; i < 9; i++) {
			if (!cards[col][i]) continue;

			cards[col][i] %= 3;

			if (i > 6 && cards[col][i] > 0) {
				return false;
			}

			if (cards[col][i] > 0) {
				if (cards[col][i + 1] < cards[col][i] || cards[col][i + 2] < cards[col][i]) {
					return false;
				}
				cards[col][i + 1] -= cards[col][i];
				cards[col][i + 2] -= cards[col][i];
				cards[col][i] = 0;
			}
		}		
	}

	return true;
}

int main() {
	int tc;
	cin >> tc;

	for (int i = 1; i <= tc; i++) {
		memset(cards, 0, sizeof(cards));
		cin >> nums >> colors;

		cout << "#" << i << " ";
		if (solution()) {
			cout << "Win" << endl;
		}
		else cout << "Continue" << endl;
	}
	return 0;
}