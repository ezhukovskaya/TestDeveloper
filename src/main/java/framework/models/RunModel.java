package framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunModel {
    @JsonProperty("suite_id")
    private int suiteId;
    @JsonProperty("name")
    private String name;
}
