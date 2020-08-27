package com.gusud;
import java.util.*;

class p뉴스클러스터링 {
    public int solution(String str1, String str2) {
        List<String> multipleSet1 = generateMultipleSet(str1.toLowerCase());
        List<String> multipleSet2 = generateMultipleSet(str2.toLowerCase());

        float jaccard = getJaccard(multipleSet1, multipleSet2);
        return (int) (jaccard * 65536);
    }

    private float getJaccard(List<String> set1, List<String> set2) {
        if (set1.size() == 0 && set2.size() == 0) {
            return 1;
        }

        return (float) getIntersection(set1, set2).size() / getUnion(set1, set2).size();
    }

    private List<String> getUnion(List<String> set1, List<String> set2) {
        List<String> union = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;

        while (p1 < set1.size() && p2 < set2.size()) {
            int comp = set1.get(p1).compareTo(set2.get(p2));

            if (comp == 0) {
                union.add(set1.get(p1));
                p1++;
                p2++;
            } else if (comp < 0) {
                union.add(set1.get(p1));
                p1++;
            } else {
                union.add(set2.get(p2));
                p2++;
            }
        }

        while (p2 < set2.size()) {
            union.add(set2.get(p2++));
        }

        while (p1 < set1.size()) {
            union.add(set1.get(p1++));
        }

        return union;
    }

    public List<String> getIntersection(List<String> set1, List<String> set2) {
        List<String> intersection = new ArrayList<>();

        int p1 = 0;
        int p2 = 0;

        while (p1 < set1.size() && p2 < set2.size()) {
            int comp = set1.get(p1).compareTo(set2.get(p2));

            if (comp == 0) {
                intersection.add(set1.get(p1));
                p1++;
                p2++;
            } else if (comp < 0) {
                p1++;
            } else {
                p2++;
            }
        }

        return intersection;
    }

    public List<String> generateMultipleSet(String input) {
        List<String> multipleSet = new ArrayList<>();

        int len = input.length() - 1;
        for (int i = 0; i < len; i++) {
            String tmp = input.substring(i, i + 2);

            if (onlyEng(tmp)) {
                multipleSet.add(tmp);
            }
        }

        Collections.sort(multipleSet);
        return multipleSet;
    }

    private boolean onlyEng(String str) {
        return Character.isAlphabetic(str.charAt(0)) &&
                Character.isAlphabetic(str.charAt(1));
    }
}