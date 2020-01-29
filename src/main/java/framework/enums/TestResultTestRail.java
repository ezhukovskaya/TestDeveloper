package framework.enums;

public enum TestResultTestRail {
    SUCCESS(1),
    FAILURE(2),
    SKIPPED(3);
    private int value;

    TestResultTestRail(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
