package BOJ;

import BOJ.service.SolvedAcClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    // 외부 API 통신만 가짜로 대체
    @MockBean
    private SolvedAcClient solvedAcClient;

    @DisplayName("검색 요청이 오면 올바른 응답을 반환한다.")
    @Test
    void search_whole_process_success() throws Exception {
        String fakeApiResponse = """
                    {
                        "count": 2,
                        "items": [
                            {
                                "problemId": 2747,
                                "titleKo": "피보나치 수",
                                "level": 4,
                                "acceptedUserCount": 36496,
                                "tags": [
                                    { "key": "math" },
                                    { "key": "implementation" }
                                ]
                            },
                            {
                                "problemId": 2839,
                                "titleKo": "설탕 배달",
                                "level": 7,
                                "acceptedUserCount": 112542,
                                "tags": [
                                    { "key": "dp" }
                                ]
                            }
                        ]
                    }
                """;

        given(solvedAcClient.searchProblem(anyString()))
                .willReturn(fakeApiResponse);

        // GET /api/search?tag=dp&count=2
        mockMvc.perform(get("/api/search")
                        .param("tag", "dp")
                        .param("count", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))

                .andExpect(jsonPath("$.[0].problemId").value(2747))
                .andExpect(jsonPath("$.[0].titleKo").value("피보나치 수"))
                .andExpect(jsonPath("$.[0].tierName").value("Bronze 2"))
                .andExpect(jsonPath("$.[0].tags[0].key").value("math"))

                .andExpect(jsonPath("$.[1].problemId").value(2839))
                .andExpect(jsonPath("$.[1].titleKo").value("설탕 배달"))
                .andExpect(jsonPath("$.[1].tierName").value("Silver 4"));
    }
}
