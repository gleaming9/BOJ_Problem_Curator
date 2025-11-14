package BOJ.view;

import BOJ.domain.Problem;

import java.util.List;

public class OutputView {

    public static void printResult(List<Problem> problems){
        System.out.println("\n검색 결과 (" + problems.size() + "건)");
        System.out.println("--------------------------------------------------");

        if (problems.isEmpty()) {
            System.out.println("조건에 맞는 문제가 없습니다.");
            return;
        }

        for (Problem p : problems) {
            System.out.printf("[%d] %s\n", p.getId(), p.getTitle());
            System.out.printf("   - 난이도: %s\n", p.getTierName());
            System.out.printf("   - 푼 사람: %d명\n", p.getSolvedCount());
            System.out.printf("   - 링크: %s\n", p.getUrl());
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }
}
