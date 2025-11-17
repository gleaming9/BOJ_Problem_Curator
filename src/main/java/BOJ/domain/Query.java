package BOJ.domain;

import BOJ.exception.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Query {
    private static final String BASE_DELIMITER = ",";
    private static final String TIER_FORMAT_REGEX = "^[bsgpdr][1-5]$";
    private static final String TAG_FORMAT_REGEX = "^[a-zA-Z_ ]+$";
    private static final String TIER_ORDER = "bsgpdr";

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

        if (!(minimumTier == null && maximumTier == null)) {
            queryBuilder.append("*");
            if(minimumTier != null) queryBuilder.append(minimumTier);
            queryBuilder.append("..");
            if(maximumTier != null) queryBuilder.append(maximumTier);
            queryBuilder.append(" ");
        }

        if (solvedCount != null) {
            queryBuilder.append("s#").append(solvedCount).append(".. ");
        }
        if (isKorean == 1) {
            queryBuilder.append("%ko");
        }
        return queryBuilder.toString();
    }

    private String[] validate() {
        validateTier();
        validateTierRange();
        return parseAndValidateTags();
    }

    private void validateTier() {
        if (minimumTier != null && !minimumTier.isBlank() && !minimumTier.matches(TIER_FORMAT_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_NAME.getMessage());
        }
        if(maximumTier != null && !maximumTier.isBlank() && !maximumTier.matches(TIER_FORMAT_REGEX)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_NAME.getMessage());
        }
    }

    private void validateTierRange() {
        if ((minimumTier != null && !minimumTier.isBlank()) &&
                (maximumTier != null && !maximumTier.isBlank())) {
            int minScore = convertTierToScore(minimumTier);
            int maxScore = convertTierToScore(maximumTier);

            if (minScore > maxScore) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_RANGE.getMessage());
            }
        }
    }

    private int convertTierToScore(String tier){
        String cleanTier = tier.trim();
        char rankChar = cleanTier.charAt(0);
        int level = Character.getNumericValue(cleanTier.charAt(1));
        int rankIndex = TIER_ORDER.indexOf(rankChar);

        return (rankIndex * 5) + (6 - level);
    }

    private String[] parseAndValidateTags() {
        if (tag == null || tag.isBlank()) return new String[0];

        String[] tags = tag.split(BASE_DELIMITER);
        for (String tagValue : tags) {
            if (tagValue == null || tagValue.isBlank() || !tagValue.matches(TAG_FORMAT_REGEX)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_TAG_NAME.getMessage());
            }
        }
        return tags;
    }
}
