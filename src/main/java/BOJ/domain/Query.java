package BOJ.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Query {
    private final String tag;
    private final String minimumTier;
    private final String maximumTier;
    private final Integer solvedCount;
    private final Integer isKorean;

    public String toQueryString() {
        StringBuilder queryBuilder = new StringBuilder();
        if(tag != null && !tag.isBlank()) {
            String[] tags = tag.split(",");
            for (String tagValue : tags) {
                queryBuilder.append("tag:").append(tagValue).append(" ");
            }
        }
        if(!(minimumTier.isBlank() && maximumTier.isBlank())) {
            queryBuilder.append("*").append(minimumTier).append("..").append(maximumTier).append(" ");
        }
        if(solvedCount != null) {
            queryBuilder.append("s#").append(solvedCount).append(".. ");
        }
        if(isKorean != null){
            queryBuilder.append("%ko");
        }
        return queryBuilder.toString();
    }
}
