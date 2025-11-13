package BOJ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolvedAcResponse {

    @JsonProperty("count")
    private int count;

    @JsonProperty("items")
    private List<Problem> items = new ArrayList<>();
}
