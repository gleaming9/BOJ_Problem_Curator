package BOJ.controller;

import BOJ.service.ProblemService;
import BOJ.view.InputView;

public class ProblemController {
    private static final ProblemService problemService = new ProblemService();

    public void run(){
        String tagsInput = InputView.getTags();
        String minTierInput = InputView.getMinTier();
        String maxTierInput = InputView.getMaxTier();
        int solvedCount = InputView.getSolvedCount();
        int korean = InputView.getKorean();

        problemService.search(tagsInput, minTierInput,maxTierInput,solvedCount,korean);
    }
}
