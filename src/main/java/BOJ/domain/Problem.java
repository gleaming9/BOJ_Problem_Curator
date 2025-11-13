package BOJ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Problem {

    private static final String BOJ_URL = "https://www.acmicpc.net/problem/";

    @JsonProperty("problemId")
    private int id;

    @JsonProperty("titleKo")
    private String title;

    @JsonProperty("level")
    private int level;

    @JsonProperty("tags")
    private List<Tag> tags;

    @JsonProperty("acceptedUserCount")
    private int solvedCount;

    public String getTierName(){
        if (level == 0) return "Unrated";

        String[] ranks = {"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"};
        int rankIndex = (level - 1) / 5;
        int rankLevel = 5 - ((level - 1) % 5);

        if (rankIndex >= ranks.length) return "Master";

        return ranks[rankIndex] + " " + rankLevel;
    }

    public String getUrl(){
        return BOJ_URL + id;
    }
}
