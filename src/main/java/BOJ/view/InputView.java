package BOJ.view;

import java.util.Scanner;

public class InputView {
    private static final String TAGS_MESSAGE = "알고리즘 태그 입력(예: \"dp\", \"dp,greedy\") : ";
    private static final String DIFFICULTY_MESSAGE = "난이도 입력(예: g5, s1..g5) : ";
    private static final String SOLVED_COUNT_MESSAGE = "최소 푼 사람 수 (예: 1000) : ";

    private static final Scanner sc = new Scanner(System.in);

    public static String getTags(){
        System.out.println(TAGS_MESSAGE);
        return sc.next();
    }

    public static String getDifficulty(){
        System.out.println(DIFFICULTY_MESSAGE);
        return sc.next();
    }

    public static int getSolvedCount(){
        System.out.println(SOLVED_COUNT_MESSAGE);
        return sc.nextInt();
    }
}
