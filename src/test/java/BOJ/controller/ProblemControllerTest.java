package BOJ.controller;

import BOJ.dto.SearchRequest;

import BOJ.exception.CustomRuntimeException;
import BOJ.exception.ErrorMessage;
import BOJ.service.ProblemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProblemController.class)
public class ProblemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemService problemService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("입력 검증 실패 시 (400 Bad Request) 에러 메세지를 반환한다.")
    @Test
    void search_fail_badRequest() throws Exception {
        String errorMessage = ErrorMessage.INVALID_TIER_NAME.getMessage();

        when(problemService.search(any(SearchRequest.class)))
                .thenThrow(new IllegalArgumentException(errorMessage));

        mockMvc.perform(get("/api/search")
                        .param("tag", "dp")
                        .param("minTier", "g1")
                        .param("maxTier", "11p")) // 잘못된 입력
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ErrorMessage.INVALID_TIER_NAME.getMessage()));
    }

    @DisplayName("서버 내부 오류 발생 시 (500 Internal Server Error) 에러 메시지를 반환한다.")
    @Test
    void search_fail_internalError() throws Exception {
        String errorMessage = ErrorMessage.API_CONNECTION_ERROR.getMessage();

        when(problemService.search(any(SearchRequest.class)))
                .thenThrow(new CustomRuntimeException(ErrorMessage.API_CONNECTION_ERROR, new IOException()));

        mockMvc.perform(get("/api/search")
                        .param("tag", "dp"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(errorMessage));
    }
}
