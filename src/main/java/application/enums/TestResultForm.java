package application.enums;

public enum TestResultForm {
    SUCCESS(0),
    FAILURE(1),
    SKIPPED(2);
    private int value;

    TestResultForm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
