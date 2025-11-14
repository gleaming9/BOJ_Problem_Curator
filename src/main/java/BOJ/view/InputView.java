package BOJ.view;

import java.util.Scanner;

public class InputView {
    private static final String TAGS_MESSAGE = "알고리즘 태그 입력(예: \"dp\", \"dp,greedy\") : ";
    private static final String MINIMUM_TIER_MESSAGE = "최소 난이도 입력 : ";
    private static final String MAXIMUM_TIER_MESSAGE = "최대 난이도 입력 : ";
    private static final String SOLVED_COUNT_MESSAGE = "최소 푼 사람 수 (예: 1000) : ";
    private static final String KOREAN_MESSAGE = "한국어 문제만 볼 건지 여부 (예: 0, 1) : ";
    private static final String PROBLEM_COUNT_MESSAGE = "원하는 문제 개수 : ";

    private static final Scanner sc = new Scanner(System.in);

    public static String getTags(){
        System.out.println(TAGS_MESSAGE);
        return sc.next();
    }

    public static String getMinTier(){
        System.out.println(MINIMUM_TIER_MESSAGE);
        return sc.next();
    }

    public static String getMaxTier(){
        System.out.println(MAXIMUM_TIER_MESSAGE);
        return sc.next();
    }

    public static int getSolvedCount(){
        System.out.println(SOLVED_COUNT_MESSAGE);
        return sc.nextInt();
    }

    public static int getKorean(){
        System.out.println(KOREAN_MESSAGE);
        return sc.nextInt();
    }

    public static int getProblemCount(){
        System.out.println(PROBLEM_COUNT_MESSAGE);
        return sc.nextInt();
    }
}
