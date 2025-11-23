package at.task16;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloAddItemResModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nameData")
    private Map<String, Object> nameData;

    @JsonProperty("pos")
    private long pos;

    @JsonProperty("state")
    private String state;

    @JsonProperty("due")
    private Object due;

    @JsonProperty("dueReminder")
    private Object dueReminder;

    @JsonProperty("idMember")
    private String idMember;

    @JsonProperty("idChecklist")
    private String idChecklist;

    @JsonProperty("limits")
    private Map<String, Object> limits;
}