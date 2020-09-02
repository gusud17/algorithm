package com.gusud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

class p방금그곡 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(new Solution().solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(new Solution().solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}

class Solution {

    public String solution(String m, String[] musicinfos) {
        return Arrays.stream(musicinfos)
                .map(MusicInfo::of)
                .filter(musicInfo -> musicInfo.hasPlayed(m))
                .sorted()
                .map(MusicInfo::getTitle)
                .findAny()
                .orElse("(None)");
    }
}

class MusicInfo implements Comparable<MusicInfo> {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");

    private final LocalTime startTime, finishTime;
    private final String title;
    private final String score;

    public MusicInfo(LocalTime startTime, LocalTime finishTime, String title, String score) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.title = title;
        this.score = score;
    }

    static MusicInfo of(String musicInfo) {
        String[] tokens = musicInfo.split(",");
        LocalTime startTime = LocalTime.parse(tokens[0], dtf);
        LocalTime finishTime = LocalTime.parse(tokens[1], dtf);

        return new MusicInfo(startTime, finishTime, tokens[2], tokens[3]);
    }

    public boolean hasPlayed(String scorePart) {
        scorePart = spaceEveryNote(scorePart);
        String playedScore = spaceEveryNote(getPlayedScore());

        return playedScore.contains(scorePart);
    }

    private String spaceEveryNote(String score) {
        StringBuffer stringBuffer = new StringBuffer();
        int len = score.length();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(score.charAt(i));

            if (i < len - 1 && score.charAt(i + 1) != '#') {
                stringBuffer.append(' ');
            }
        }
        stringBuffer.append(' ');
        return stringBuffer.toString();
    }

    private String getPlayedScore() {
        StringBuffer stringBuffer = new StringBuffer();
        int playTime = playTime();

        int p = 0;
        while (playTime > 0) {
            playTime--;

            stringBuffer.append(score.charAt(p++));
            if (p < score.length() && score.charAt(p) == '#') {
                stringBuffer.append('#');
                p++;
            }
            if (p == score.length()) p = 0;
        }

        return stringBuffer.toString();
    }

    private int playTime() {
        return (int) ChronoUnit.MINUTES.between(startTime, finishTime);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(MusicInfo o) {
        return o.playTime() - this.playTime();
    }
}