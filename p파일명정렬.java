package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p파일명정렬 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
    }
}

class Solution {
    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            fileList.add(File.of(files[i], i));
        }

        return fileList.stream()
                .sorted()
                .map(File::getFilename)
                .toArray(String[]::new);
    }
}

class File implements Comparable<File> {
    private final String filename;
    private final String head, tail;
    private final int number, seq;

    public File(String filename, String head, int number, String tail, int seq) {
        this.filename = filename;
        this.head = head;
        this.tail = tail;
        this.number = number;
        this.seq = seq;
    }

    static File of(String filename, int seq) {
        String head, number, tail;

        int left = 0, right = 0;

        while (right < filename.length()) {
            char c = filename.charAt(right);
            if (c >= '0' && c <= '9') {
                break;
            }
            right++;
        }

        head = filename.substring(left, right);
        left = right;

        while (right < filename.length()) {
            char c = filename.charAt(right);
            if (c < '0' || c > '9') {
                break;
            }
            right++;
        }
        number = filename.substring(left, right);
        left = right;

        if (left == filename.length()) {
            left = 0;
            right = 0;
        }

        tail = filename.substring(left, right);

        return new File(filename, head, Integer.parseInt(number), tail, seq);
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public int compareTo(File o) {
        int comp = head.compareToIgnoreCase(o.head);
        if (comp == 0) {
            comp = number - o.number;
        }
        if (comp == 0) {
            comp = seq - o.seq;
        }
        return comp;
    }
}
