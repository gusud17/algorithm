
import java.util.*;
import java.util.stream.Collectors;

public class p불량사용자 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"fr*d*", "abc1**"}
                ));

        System.out.println(
                new Solution().solution(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"*rodo", "*rodo", "******"}
                ));
        System.out.println(
                new Solution().solution(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"fr*d*", "*rodo", "******", "******"}
                ));
    }
}

class Solution {
    private List<BadUser> badUsers = new ArrayList<>();
    private int answer = 0;
    private List<Set<String>> visited = new ArrayList<>();

    public int solution(String[] user_ids, String[] banned_ids) {
        for (String banned_id : banned_ids) {
            BadUser badUser = new BadUser(banned_id);
            badUser.addAll(user_ids);
            badUsers.add(badUser);
        }

        combi(0, new HashSet<>());
        return answer;
    }

    private void combi(int i, Set<String> badIds) {
        if (badUsers.size() == badIds.size()) {
            if (checkVisit(badIds)) {
                visited.add(new HashSet<>(badIds));
                answer += 1;
            }
            return;
        }

        for (String bannedId : badUsers.get(i).bannedIds) {
            int before = badIds.size();
            badIds.add(bannedId);
            if (before == badIds.size()) continue;
            combi(i + 1, badIds);
            badIds.remove(bannedId);
        }
    }

    private boolean checkVisit(Set<String> badIds) {
        for (Set<String> visit : this.visited) {
            if (badIds.equals(visit)) return false;
        }
        return true;
    }

    static class BadUser {
        private final String badUser;
        private List<String> bannedIds;

        public BadUser(String badUser) {
            this.badUser = badUser;
            bannedIds = new ArrayList<>();
        }

        public void addAll(String[] ids) {
            bannedIds.addAll(Arrays.stream(ids)
                    .filter(this::check)
                    .collect(Collectors.toList()));
        }

        private boolean check(String id) {
            int len = badUser.length();
            if (id.length() != len) return false;

            for (int i = 0; i < len; i++) {
                if (badUser.charAt(i) == '*') continue;
                if (badUser.charAt(i) != id.charAt(i)) return false;
            }
            return true;
        }
    }
}