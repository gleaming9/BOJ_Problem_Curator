package BOJ.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    INVALID_TAG_NAME("태그는 영문자, 언더스코어, 공백만 허용됩니다."),
    INVALID_TIER_NAME("올바르지 않은 티어 형식입니다. (가능한 티어 형식 : g5, s1, p3)"),

    API_CONNECTION_ERROR("Solved.ac API 통신 중 오류가 발생했습니다."),
    API_REQUEST_FAILED("API 요청에 실패했습니다. 상태 코드 : "),

    JSON_PARSING_FAILED("API 응답 데이터를 분석하는 데 실패했습니다."),

    UNKNOWN_ERROR("알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요.");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }
}
