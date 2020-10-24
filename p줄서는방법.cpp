#include <string>
#include <vector>

using namespace std;

int getNum(int, bool[], int);
vector<long long> getFactorials(int);

vector<int> solution(int n, long long k) {
    vector<int> answer;
    vector<long long> factorial = getFactorials(n);
    bool check[20] = {false,};
    
    for (int i = n - 1; i >= 0; i--) {
        int cnt = 0;
        
        while (k > factorial[i]) {
            k -= factorial[i];
            cnt++;
        }
        answer.push_back(getNum(n, check, cnt));
    }
    return answer;
}

vector<long long> getFactorials(int n) {
    vector<long long> factorial;
    factorial.push_back(1);
    for (int i = 1; i <= n; i++) {
        factorial.push_back(factorial[i - 1] * i);
    }
    return factorial;
}

int getNum(int n, bool check[], int cnt) {
    for (int i = 0; i < n; i++) {
        if (check[i]) continue;
        if (cnt == 0) {
            check[i] = true;
            return i + 1;
        }
        cnt--;
    }
    return -1;
}
