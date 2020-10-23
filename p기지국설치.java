#include <vector>

using namespace std;

int solution(int n, vector<int> stations, int w) {
    int sp = 0;
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        if (sp >= stations.size() || i < stations[sp] - w) {
            answer += 1;
            i += w * 2;
        } else {
            i = stations[sp] + w;
            sp++;
        }
    }
    
    return answer;
}
