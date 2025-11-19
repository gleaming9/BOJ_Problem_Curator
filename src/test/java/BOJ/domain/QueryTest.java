package BOJ.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueryTest {

    @DisplayName("모든 조건이 주어졌을 때, 올바른 쿼리 문자열을 생성한다.")
    @Test
    void toQueryString_all_fields() {
        Query query = Query.builder()
                .tag("dp,math")
                .minimumTier("s5")
                .maximumTier("p1")
                .solvedCount(100)
                .isKorean(1)
                .build();
        String result = query.toQueryString();

        assertThat(result).isEqualTo("tag:dp tag:math *s5..p1 s#100.. %ko");
    }

    @DisplayName("특정한 조건만 주어졌을 때, 올바른 쿼리 문자열을 생성한다.")
    @Test
    void toQueryString_required_fields_only() {
        Query query = Query.builder()
                .tag("dp")
                .minimumTier(null)
                .maximumTier("g1")
                .solvedCount(null)
                .isKorean(1)
                .build();
        String result = query.toQueryString();

        assertThat(result).isEqualTo("tag:dp *..g1 %ko");
    }

    @DisplayName("잘못된 티어 형식이 입력되면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "g0", "g6", "gold1", "1g"})
    void invalid_tier(String invalidTier) {
        Query query = Query.builder()
                .tag("greedy")
                .minimumTier(invalidTier)
                .maximumTier("r5")
                .build();

        assertThatThrownBy(query::toQueryString)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최소/최대 티어 형식이 잘못될 경우 예외를 발생한다.")
    @Test
    void invalid_tier_range(){
        Query query = Query.builder()
                .tag("dp")
                .minimumTier("p5")
                .maximumTier("s1")
                .build();

        assertThatThrownBy(query::toQueryString)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자 아이디가 주어졌을 때, 올바른 쿼리 문자열을 생성한다.")
    @Test
    void toQueryString_with_userId() {
        Query query = Query.builder()
                .tag("dp")
                .userId("gleaming9")
                .build();
        String result = query.toQueryString();

        assertThat(result).contains("tag:dp", "-@gleaming9");
    }
}
