package com.example.android.miwok;

import java.util.Arrays;

/**
 * Created by hp on 7/27/2016.
 */
public class ReportCard {
    private char[] letters = {'A', 'B', 'C', 'D', 'F'};
    private String[] subjects = {"History", "Mathematics", "biology", "geography"};
    private int[] marks;

    public ReportCard() {
        numToLetter();
    }

    public ReportCard(char[] l , String[] s, int[] m) {
        letters = l;
        subjects = s;
        marks = m;
    }

    public void historyMark(int h) {
        marks[0] = h;
    }

    public void mathMark(int m) {
        marks[1] = m;
    }

    public void bioMark(int b) {
        marks[2] = b;
    }

    public void geoMark(int g) {
        marks[3] = g;
    }

    public int getHistoryMark() {
        return marks[0];
    }

    public int getMathMark() {
        return marks[1];
    }

    public int getBioMark() {
        return marks[2];
    }

    public int getGeoMark() {
        return marks[3];
    }

    private void numToLetter() {
        for (int allMarks : marks
                ) {
            if (allMarks >= 90) {
                letters[0] = 'A';
                continue;
            }
            if (allMarks >= 80) {
                letters[0] = 'B';
                continue;
            }
            if (allMarks >= 70) {
                letters[0] = 'C';
                continue;
            }
            if (allMarks >= 50) {
                letters[0] = 'D';
                continue;
            }
            if (allMarks < 50)
                letters[0] = 'F';
        }

    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "subjects=" + Arrays.toString(subjects) +
                ", letters=" + Arrays.toString(letters) +
                '}';
    }
}
