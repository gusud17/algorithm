#include <string>
#include <vector>

using namespace std;

#define MOD 1234567

long long solution(int n) {
    long long memo[n];
    memo[0] = 1;
    memo[1] = 2;
    
    for (int i = 2; i < n; i++) {
        memo[i] = (memo[i - 1] + memo[i - 2]) % MOD;
    }
    
    return memo[n - 1];
}
