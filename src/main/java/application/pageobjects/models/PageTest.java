package application.pageobjects.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageTest {
    @JacksonXmlProperty
    String duration;
    @JacksonXmlProperty
    String method;
    @JacksonXmlProperty
    String name;
    @JacksonXmlProperty
    String startTime;
    @JacksonXmlProperty
    String endTime;
    @JacksonXmlProperty
    String status;
}
