package BOJ.domain;

import BOJ.exception.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Query {
    private final String BASE_DELIMITER = ",";
    private final String TIER_FORMAT_REGEX = "^[bsgpdr][1-5]$";
    private final String TAG_FORMAT_REGEX = "^[a-zA-Z_ ]+$";

    private final String tag;
    private final String minimumTier;
    private final String maximumTier;
    private final Integer solvedCount;
    private final Integer isKorean;

    public String toQueryString() {
        String[] tags = validate();

        StringBuilder queryBuilder = new StringBuilder();
        for (String tagValue : tags) {
            queryBuilder.append("tag:").append(tagValue).append(" ");
        }
        if (!(minimumTier.isBlank() && maximumTier.isBlank())) {
            queryBuilder.append("*").append(minimumTier).append("..").append(maximumTier).append(" ");
        }
        if (solvedCount != null) {
            queryBuilder.append("s#").append(solvedCount).append(".. ");
        }
        if (isKorean != null) {
            queryBuilder.append("%ko");
        }
        return queryBuilder.toString();
    }

    private String[] validate() {
        validateTier();
        return parseAndValidateTags();
    }

    private void validateTier() {
        if (!minimumTier.matches(TIER_FORMAT_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_NAME.getMessage());
        }
    }

    private String[] parseAndValidateTags() {
        if (tag == null || tag.isBlank()) return null;

        String[] tags = tag.split(BASE_DELIMITER);
        for (String tagValue : tags) {
            if (tagValue == null || !tagValue.matches(TAG_FORMAT_REGEX)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_TAG_NAME.getMessage());
            }
        }
        return tags;
    }
}
