#include <iostream>
#include <vector>
#include <queue>
using namespace std;

#define MAX_INT 987654321

int min(int a, int b) {
    return a > b ? b : a;
}

struct Path {
    int dest;
    int weight;
};

struct cmp {
    bool operator()(Path a, Path b) {
        return a.weight > b.weight;
    }
};

int solution(int N, vector<vector<int>> roads, int K) {
    vector<pair<int, int>> graph[N + 1];
    
    for (vector<int> road : roads) {
        graph[road[0]].push_back(make_pair(road[1], road[2]));
        graph[road[1]].push_back(make_pair(road[0], road[2]));
    }

    priority_queue<Path, vector<Path>, cmp> pq;
    pq.push({1, 0});
    
    int distance[N + 1];
    for (int i = 1; i <= N; i++) {
        distance[i] = MAX_INT;
    }
    
    while (!pq.empty()) {
        Path path = pq.top();
        pq.pop();
        
        if (distance[path.dest] < path.weight) continue;
        distance[path.dest] = path.weight;
        
        for (int i = 0; i < graph[path.dest].size(); i++) {
            int next = graph[path.dest][i].first;
            int dist = graph[path.dest][i].second + path.weight;
            
            if (distance[next] == MAX_INT) {
                pq.push({next, dist});
            }
        }
    }
    
    int answer = 0;
    for (int i = 1; i <= N; i++) {
        if (distance[i] <= K) answer++;
    }
    
    return answer;
}
