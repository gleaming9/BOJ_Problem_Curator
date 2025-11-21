package BOJ.client;

import BOJ.exception.CustomRuntimeException;
import BOJ.exception.ErrorMessage;
import BOJ.service.SolvedAcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class HttpSolvedAcClient implements SolvedAcClient {
    @Value("${solvedAc.api.url}")
    private String BASE_URL;
    private static final String SORT = "random";
    private final HttpClient client;

    public HttpSolvedAcClient(){
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public String searchProblem(String queryString){
        try{
            String encodedQuery = URLEncoder.encode(queryString, StandardCharsets.UTF_8);
            String fullUri = BASE_URL + "?query=" + encodedQuery + "&sort=" + SORT;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUri))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new CustomRuntimeException(ErrorMessage.API_REQUEST_FAILED,
                        String.valueOf(response.statusCode()));
            }

            return response.body();
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            throw new CustomRuntimeException(ErrorMessage.API_CONNECTION_ERROR, e);
        }
        catch(IOException e){
            throw new CustomRuntimeException(ErrorMessage.API_CONNECTION_ERROR, e);
        }
    }
}
