package com.loan.approve.model.enums;

public enum ApplyStatus {

    INIT("INIT", "INVESTIGATE", false),
    INVESTIGATE("INVESTIGATE", "APPROVING", false),
    APPROVING("APPROVING", "FINISHED", false),
    FINISHED("FINISHED", "", true),
    ;

    public final String current;
    public final String next;
    public final boolean isEnd;

    ApplyStatus(String current, String next, boolean isEnd) {
        this.current = current;
        this.next = next;
        this.isEnd = isEnd;
    }

    public ApplyStatus getByCurrent(String current) {
        for (ApplyStatus applyStatus : ApplyStatus.values()) {
            if (applyStatus.current.equals(current)) {
                return applyStatus;
            }
        }
        throw new RuntimeException("Unknown ApplyStatus:" + current);
    }
}
