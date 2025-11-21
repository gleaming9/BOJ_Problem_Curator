package BOJ.domain;

import BOJ.exception.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
    private final String userId;

    public String toQueryString() {
        String[] tags = validate();

        List<String> queryParts = new ArrayList<>();
        for (String tagValue : tags) {
            queryParts.add("tag:" + tagValue);
        }
        String tierQuery = buildTierQuery();
        if (!tierQuery.isEmpty()) {
            queryParts.add(tierQuery);
        }
        if (solvedCount != null) {
            queryParts.add("s#" + solvedCount + "..");
        }
        if (isKorean != null) {
            queryParts.add("%ko");
        }
        if (userId != null && !userId.isBlank()){
            queryParts.add("-@" + userId);
        }

        return String.join(" ", queryParts);
    }

    private String buildTierQuery(){
        if(!hasText(minimumTier) && !(hasText(maximumTier))){
            return "";
        }

        StringBuilder tierQuery = new StringBuilder("*");
        if(hasText(minimumTier)){
            tierQuery.append(minimumTier);
        }
        tierQuery.append("..");
        if(hasText(maximumTier)){
            tierQuery.append(maximumTier);
        }

        return tierQuery.toString();
    }

    private String[] validate() {
        validateTier();
        validateTierRange();
        return parseAndValidateTags();
    }

    private void validateTier() {
        if (hasText(minimumTier) && !minimumTier.matches(TIER_FORMAT_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_NAME.getMessage());
        }
        if(hasText(maximumTier) && !maximumTier.matches(TIER_FORMAT_REGEX)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIER_NAME.getMessage());
        }
    }

    private void validateTierRange() {
        if (hasText(minimumTier) && hasText(maximumTier)) {
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
        if (!hasText(tag)) return new String[0];

        String[] tags = tag.split(BASE_DELIMITER);
        for (String tagValue : tags) {
            if (!hasText(tagValue) || !tagValue.matches(TAG_FORMAT_REGEX)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_TAG_NAME.getMessage());
            }
        }
        return tags;
    }

    private Boolean hasText(String value){
        return value != null && !value.isBlank();
    }
}
