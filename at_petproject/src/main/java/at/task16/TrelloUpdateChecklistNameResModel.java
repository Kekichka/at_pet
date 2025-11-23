package at.task16;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@Setter
@Getter
public class TrelloUpdateChecklistNameResModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("idBoard")
    private String idBoard;

    @JsonProperty("idCard")
    private String idCard;

    @JsonProperty("pos")
    private long pos;

    @JsonProperty("checkItems")
    private List<Object> checkItems;

    @JsonProperty("limits")
    private Map<String, Object> limits;
}