import java.util.Scanner;

public class b17825 {

    static int[] diceNums = new int[10];
    static Node[] pieces;
    static int ans = 0;
    static int[] info = new int[10];
    static int[] info_score = new int[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        pieces = new Node[4];
        for (int i = 0; i < 4; i++) {
            pieces[i] = Node.startNode();
        }

        for (int i = 0; i < 10; i++) {
            diceNums[i] = sc.nextInt();
        }
        combi(0, 0);
        System.out.println(ans);
    }

    static void combi(int diceP, int score) {
        if (diceP == 10) {
            ans = Math.max(score, ans);
            System.out.print("순서 : ");
            for (int i = 0; i< 10; i++) {
                System.out.print(info[i] + " ");
            }
            System.out.println();
            System.out.print("점수 : ");
            for (int i = 0; i<10; i++) {
                System.out.print(info_score[i]+" ");
            }

            System.out.println("--------------------------------------");
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (pieces[i].isEnd()) continue;

            Node tmp = pieces[i];
            pieces[i] = pieces[i].next(diceNums[diceP]);
            if (noDup(i)) {
                info[diceP] = i;
                info_score[diceP] = pieces[i].score;

                combi(diceP + 1, score + pieces[i].score);
            }
            pieces[i] = tmp;
        }
    }

    private static boolean noDup(int index) {
        for (int i = 0; i < 4; i++) {
            if (i == index || pieces[i].isEnd()) continue;
            if (pieces[i] == pieces[index]) return false;
        }
        return true;
    }

    static class Node {
        private Node(int score, int next, int sNext) {
            this.score = score;
            this.next = next;
            this.sNext = sNext;
        }

        private final int score, next, sNext;

        private Node(int score, int next) {
            this(score, next, next);
        }

        public static Node startNode() {
            return get(0);
        }

        Node next(int n) {
            int next = sNext;
            for (int i = 1; i < n; i++) {
                next = get(next).next;
            }
            return NODES[next];
        }

        static Node get(int i) {
            return NODES[i];
        }

        private static final Node[] NODES = {
                new Node(0, 1), //start
                new Node(2, 2), //1
                new Node(4, 3),//2
                new Node(6, 4),//3
                new Node(8, 5),//4
                new Node(10, 6, 21),//5
                new Node(12, 7),//6
                new Node(14, 8),//7
                new Node(16, 9),//8
                new Node(18, 10),//9
                new Node(20, 11, 24),//10
                new Node(22, 12),//11
                new Node(24, 13),//12
                new Node(26, 14),//13
                new Node(28, 15),//14
                new Node(30, 16, 26),//15
                new Node(32, 17),//16
                new Node(34, 18),//17
                new Node(36, 19),//18
                new Node(38, 20),//19
                new Node(40, 32),//20
                new Node(13, 22),//21
                new Node(16, 23),//22
                new Node(19, 29),//23
                new Node(22, 25),//24
                new Node(24, 29),//25
                new Node(28, 27),//26
                new Node(27, 28),//27
                new Node(26, 29),//28
                new Node(25, 30),//29
                new Node(30, 31),//30
                new Node(35, 20),//31
                new Node(0, 32) //end
        };

        public boolean isEnd() {
            return next == 32 && score == 0;
        }
    }
}
