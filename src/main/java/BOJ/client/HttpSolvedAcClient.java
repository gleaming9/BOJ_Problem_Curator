package BOJ.client;

import BOJ.exception.ErrorMessage;
import BOJ.service.SolvedAcClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class HttpSolvedAcClient implements SolvedAcClient {

    private static final String BASE_URL = "https://solved.ac/api/v3/search/problem";
    private static final String SORT = "random";
    private final HttpClient client;

    public HttpSolvedAcClient(){
        this.client = HttpClient.newHttpClient();
    }

    public String searchProblem(String queryString){
        try{
            String encodedQuery = URLEncoder.encode(queryString, StandardCharsets.UTF_8);
            String fullUri = BASE_URL + "?query=" + encodedQuery + "&sort=" + SORT;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUri))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException(ErrorMessage.API_REQUEST_FAILED.getMessage() + response.statusCode());
            }

            return response.body();
        }
        catch(IOException | InterruptedException e){
            throw new RuntimeException(ErrorMessage.API_CONNECTION_ERROR.getMessage(), e);
        }
    }
}
