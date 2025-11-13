package BOJ.service;

import BOJ.client.HttpSolvedAcClient;
import BOJ.domain.Problem;
import BOJ.domain.Query;
import BOJ.domain.SolvedAcResponse;
import BOJ.exception.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProblemService {

    private static final SolvedAcClient solvedAcClient = new HttpSolvedAcClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<Problem> search(String tag, String minTier, String maxTier, int solvedCount, int isKorean){
        Query query = Query.builder()
                .tag(tag)
                .minimumTier(minTier)
                .maximumTier(maxTier)
                .solvedCount(solvedCount)
                .isKorean(isKorean)
                .build();

        String queryString = query.toQueryString();
        String jsonResponse = solvedAcClient.searchProblem(queryString);

        return parseResponse(jsonResponse);
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
