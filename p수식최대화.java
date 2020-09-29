import java.util.*;

public class p수식최대화 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("100-200*300-500+20"));
        System.out.println(new Solution().solution("50*6-3*2"));
    }
}

class Solution {
    private static final List<Character> OPERATORS = Arrays.asList('*', '+', '-');

    boolean[] visited = new boolean[3];
    long answer = 0;

    public long solution(String expression) {
        List<Node> nodes = expressionToNodes(expression);

        combi(nodes);

        return answer;
    }

    private void combi(List<Node> nodes) {
        if (nodes.size() == 1) {
            answer = Math.max(Math.abs(nodes.get(0).num), answer);
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;
            visited [i] = true;
            combi(calculate(nodes, OPERATORS.get(i)));
            visited[i] = false;
        }
    }

    List<Node> calculate(List<Node> nodes, char operator) {
        Stack<Node> nNodes = new Stack<>();

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (nodes.get(i).is(operator)) {
                nNodes.add(node.operate(nNodes.pop(), nodes.get(i + 1)));
                i++;
            } else {
                nNodes.add(nodes.get(i));
            }
        }

        return new ArrayList<>(nNodes);
    }

    private List<Node> expressionToNodes(String expression) {
        List<Node> nodes = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < expression.length(); i++) {
            if (OPERATORS.contains(expression.charAt(i))) {
                nodes.add(Node.numOf(sb.toString()));
                nodes.add(Node.operatorOf(expression.charAt(i)));
                sb.delete(0, sb.length());
            } else {
                sb.append(expression.charAt(i));
            }
        }
        nodes.add(Node.numOf(sb.toString()));

        return nodes;
    }

    static class Node {
        private final Long num;
        private final Character operator;

        public Node(Long num, Character operator) {
            this.num = num;
            this.operator = operator;
        }

        static private Node numOf(long num) {
            return new Node(num, null);
        }

        static Node numOf(String num) {
            return numOf(Long.parseLong(num));
        }

        static Node operatorOf(char operator) {
            return new Node(null, operator);
        }

        public boolean is(char operator) {
            return this.operator != null && this.operator.equals(operator);
        }

        public Node operate(Node left, Node right) {
            switch (operator) {
                case '-': return numOf(left.num - right.num);
                case '+': return numOf(left.num + right.num);
                case '*': return numOf(left.num * right.num);
            }
            return null;
        }
    }
}