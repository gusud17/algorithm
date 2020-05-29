#include <iostream>

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct node {
	int x, y, val;
	node* left, * right;
};

node* root;
vector<int> order;

void insert(node*elem) {
	if (root == NULL) {
		root = elem;
	}
	else {
		node* parent = root;

		while (1) {
			if (parent->x > elem->x) {
				if (parent->left == NULL) {
					parent->left = elem;
					break;
				}
				else {
					parent = parent->left;
				}
			}
			else {
				if (parent->right == NULL) {
					parent->right = elem;
					break;
				}
				else {
					parent = parent->right;
				}
			}
		}
	}
}

void preorder(node* cur) {
	if (cur == NULL) return;
	order.push_back(cur->val);
	preorder(cur->left);
	preorder(cur->right);
}

void postorder(node* cur) {
	if (cur == NULL) return;
	postorder(cur->left);
	postorder(cur->right);
	order.push_back(cur->val);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
	for (int i = 0; i < nodeinfo.size(); i++) {
		nodeinfo[i].push_back(i+1);
	}
	sort(nodeinfo.begin(), nodeinfo.end(), [](vector<int> n1, vector<int> n2) -> bool {
		if (n1[1] == n2[1]) return n1[0] < n2[0];
		return n1[1] > n2[1];
	});

	for (int i = 0; i < nodeinfo.size(); i++) {
		node* elem = new node{ nodeinfo[i][0], nodeinfo[i][1], nodeinfo[i][2] };
		insert(elem);
	}

	vector<vector<int>> answer;
	preorder(root);
	answer.push_back(order);

	order.clear();
	postorder(root);
	answer.push_back(order);

	return answer;
}

int main() {
	vector<vector<int>> nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
	
	nodeinfo = solution(nodeinfo);

	for (int i = 0; i < nodeinfo.size(); i++) {
		for (int j = 0; j < nodeinfo[i].size(); j++) {
			cout << nodeinfo[i][j] << " ";
		}
		cout << endl;
	}
	return 0;
}