package application.constants;

import framework.elements.Label;
import org.openqa.selenium.By;

public class WebElements {
    public static final Label PLEASE_WAIT = new Label(By.xpath("//div[contains(@class,'messi')]"), "Please Wait");
}
