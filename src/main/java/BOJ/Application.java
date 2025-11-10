package BOJ;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        String queryValue = "tag:dp *g5..g1 %ko s#1000..";
        String sort = "random";

        // 쿼리 파라미터의 값만 URL 인코딩
        String encodedQuery = URLEncoder.encode(queryValue, StandardCharsets.UTF_8);

        String baseUri = "https://solved.ac/api/v3/search/problem";
        String fullUri = baseUri + "?query=" + encodedQuery + "&sort=" + sort;

        // HTTP 클라이언트 생성
        HttpClient client = HttpClient.newHttpClient();

        // HTTP 요청 객체 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUri))
                .header("Accept", "application/json")
                .GET()
                .build();

        System.out.println("API 요청");
        System.out.println("Request URI: " + fullUri);
        System.out.println("-------------------------------------------------");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
    }
}
