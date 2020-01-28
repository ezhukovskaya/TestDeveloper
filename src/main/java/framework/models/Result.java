package framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @JsonProperty("status_id")
    private int statusId;
    @JsonProperty("comment")
    private String comment;
}
