package framework.elements;

import framework.base.BaseElement;
import org.openqa.selenium.By;

public class Label extends BaseElement {

    public Label(By locator, String name) {
        super(locator, name);
    }
}