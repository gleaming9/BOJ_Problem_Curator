package BOJ.service;

import BOJ.domain.Problem;
import BOJ.domain.Query;
import BOJ.domain.SolvedAcResponse;
import BOJ.dto.SearchRequest;
import BOJ.exception.CustomRuntimeException;
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

    public List<Problem> search(SearchRequest request) {
        Query query = createQuery(request);

        String queryString = query.toQueryString();
        String jsonResponse = solvedAcClient.searchProblem(queryString);
        List<Problem> allProblems = parseResponse(jsonResponse);

        return allProblems.stream()
                .limit(request.getCount())
                .collect(Collectors.toList());
    }

    private Query createQuery(SearchRequest request) {
        return Query.builder()
                .tag(request.getTag())
                .minimumTier(request.getMinTier())
                .maximumTier(request.getMaxTier())
                .solvedCount(request.getSolvedCount())
                .isKorean(request.getIsKorean())
                .userId(request.getUserId())
                .build();
    }

    private List<Problem> parseResponse(String json) {
        try {
            SolvedAcResponse response = objectMapper.readValue(json, SolvedAcResponse.class);
            return response.getItems();
        } catch (JsonProcessingException e) {
            throw new CustomRuntimeException(ErrorMessage.JSON_PARSING_FAILED, e);
        }
    }
}
