package BOJ.controller;

import BOJ.domain.Problem;
import BOJ.dto.SearchRequest;
import BOJ.service.ProblemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("api/search")
    public ResponseEntity<List<Problem>> searchProblems(SearchRequest request) {
        List<Problem> problems = problemService.search(request);
        return ResponseEntity.ok(problems);
    }
}
