package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution매칭점수 {

    String word;
    Map<String, Double> linkCntMap;
    List<Page> ps;

    public int solution(String word, String[] pages) {
        linkCntMap = new HashMap<>();
        ps = new ArrayList<>();
        this.word = word.toLowerCase();

        for (String p : pages) {
            ps.add(new Page(p));
        }

        double max = 0;
        int ans = 0;
        for (int i = 0; i < ps.size(); i++) {
            if (ps.get(i).getScore() > max) {
                max = ps.get(i).getScore();
                ans = i;
            }
        }
        return ans;
    }

    class Page {
        String url;
        int basicScore;
        List<String> links;

        Page(String page) {
            url = extractUrl(page);
            basicScore = countScore(page.toLowerCase());
            links = extractLinks(page);
            addLinkCntMap();
        }

        private void addLinkCntMap() {
            float linkScore = (float) basicScore / links.size();
            for (String link : links) {
                linkCntMap.put(link, linkScore + linkCntMap.getOrDefault(link, 0.0));
            }
        }

        private int countScore(String page) {
            int index = -1;
            int score = 0;
            do {
                index = page.indexOf(word, index + 1);
                if (isAlphabet(page, index - 1) || isAlphabet(page, index + word.length())) continue;
                score++;
            } while (index != -1);

            return score;
        }

        private boolean isAlphabet(String str, int idx) {
            if (idx < 0 || str.length() <= idx) return true;
            return 'a' <= str.charAt(idx) && str.charAt(idx) <= 'z';
        }

        private String extractUrl(String page) {
            int urlStartIndex = page.indexOf("content=\"https://", page.indexOf("<meta property=\"og:url\" ")) + 9;
            int urlEndIndex = page.indexOf("\"/>", urlStartIndex);
            return page.substring(urlStartIndex, urlEndIndex);
        }

        private List<String> extractLinks(String page) {
            int endIndex = 0;
            List<String> links = new ArrayList<>();
            while (true) {
                int startIndex = page.indexOf("<a href=\"", endIndex);
                if (startIndex == -1) break;
                endIndex = page.indexOf("\">", startIndex);
                links.add(page.substring(startIndex + 9, endIndex));
            }
            return links;
        }

        public double getScore() {
            return basicScore + linkCntMap.getOrDefault(url, 0.0);
        }
    }
}
