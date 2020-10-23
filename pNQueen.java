#include <string>
#include <vector>

using namespace std;

#define MAX_N 12

int N;
int rowNum[MAX_N];

int go(int);
bool canGo(int, int);

int solution(int n) {
    N = n;
    return go(0);
}

int go(int depth) {
    if (depth == N) return 1;
    
    int ret = 0;
    for (int i = 0; i < N; i++) {
        if (!canGo(depth, i)) continue;
        rowNum[depth] = i;
        ret += go(depth + 1);
    }
    return ret;
}

bool canGo(int x, int y) {
    for (int i = 0; i < x; i++) {
        if (y == rowNum[i] || abs(y - rowNum[i]) == abs(x - i)) return false;
    }
    return true;
}

int abs(int n) {
    if (n < 0) return n * (-1);
    return n;
}
