package BOJ.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    private String tag;
    private String minTier;
    private String maxTier;
    private Integer solvedCount;
    private Integer isKorean;
    private int count = 1;
    private String userId;
}
