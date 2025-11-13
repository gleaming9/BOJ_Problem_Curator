package BOJ.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    INVALID_TAG_NAME("태그는 영문자, 언더스코어, 공백만 허용됩니다."),
    INVALID_TIER_NAME("올바르지 않은 티어 형식입니다. (가능한 티어 형식 : g5, s1, p3)");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }
}
