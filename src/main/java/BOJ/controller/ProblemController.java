package BOJ.controller;

import BOJ.domain.Problem;
import BOJ.service.ProblemService;
import BOJ.view.InputView;
import BOJ.view.OutputView;

import java.util.List;

public class ProblemController {
    private static final ProblemService problemService = new ProblemService();

    public void run() {
        String tagsInput = InputView.getTags();
        String minTierInput = InputView.getMinTier();
        String maxTierInput = InputView.getMaxTier();
        int solvedCount = InputView.getSolvedCount();
        int korean = InputView.getKorean();
        int problemCount = InputView.getProblemCount();

        List<Problem> problems = problemService.search(tagsInput,
                minTierInput, maxTierInput, solvedCount, korean, problemCount);

        OutputView.printResult(problems);
    }
}
