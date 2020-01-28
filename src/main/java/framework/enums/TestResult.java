package framework.enums;

public enum TestResult {
    SUCCESS(1),
    FAILURE(2),
    SKIPPED(3);
    private int value;

    TestResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
