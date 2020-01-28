package application.pageobjects.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "test")
public class PageTest {
    String duration;
    String method;
    String name;
    String startTime;
    String endTime;
    String status;
}
