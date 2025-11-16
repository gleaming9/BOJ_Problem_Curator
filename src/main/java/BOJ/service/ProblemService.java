package BOJ.service;

import BOJ.domain.Problem;
import BOJ.domain.Query;
import BOJ.domain.SolvedAcResponse;
import BOJ.exception.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService {

    private final SolvedAcClient solvedAcClient;
    private final ObjectMapper objectMapper;

    @Autowired
    ProblemService(SolvedAcClient solvedAcClient) {
        this.solvedAcClient = solvedAcClient;
        this.objectMapper = new ObjectMapper();
    }

    public List<Problem> search(String tag, String minTier,
                                String maxTier, int solvedCount, int isKorean, int problemCount){
        Query query = Query.builder()
                .tag(tag)
                .minimumTier(minTier)
                .maximumTier(maxTier)
                .solvedCount(solvedCount)
                .isKorean(isKorean)
                .build();

        String queryString = query.toQueryString();
        String jsonResponse = solvedAcClient.searchProblem(queryString);
        List<Problem> allProblems = parseResponse(jsonResponse);

        return allProblems.stream()
                .limit(problemCount)
                .collect(Collectors.toList());
    }

    private List<Problem> parseResponse(String json){
        try{
            SolvedAcResponse response = objectMapper.readValue(json, SolvedAcResponse.class);
            return response.getItems();
        }
        catch(JsonProcessingException e){
            throw new RuntimeException(ErrorMessage.JSON_PARSING_FAILED.getMessage(), e);
        }
    }
}
