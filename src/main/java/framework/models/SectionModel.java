package framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionModel {
    @JsonProperty("suite_id")
    private int suiteId;
    @JsonProperty("name")
    private String name;
}
