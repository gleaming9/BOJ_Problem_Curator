package BOJ.controller;

import BOJ.domain.Problem;
import BOJ.service.ProblemService;
import BOJ.view.InputView;
import BOJ.view.OutputView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

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

    @GetMapping("api/search")
    public List<Problem> searchProblems(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String minTier,
            @RequestParam(required = false) String maxTier,
            @RequestParam(required = false) Integer solvedCount,
            @RequestParam(required = false) Integer isKorean,
            @RequestParam(defaultValue = "1") int count
    ){
        return problemService.search(tag, minTier, maxTier, solvedCount, isKorean, count);
    }
}
