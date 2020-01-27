package framework.base;

import framework.elements.Label;

public abstract class BaseForm {
    private Label label;

    public BaseForm(Label label) {
        this.label = label;
    }

    public boolean isDisplayed() {
        return this.label.isDisplayed();
    }
}