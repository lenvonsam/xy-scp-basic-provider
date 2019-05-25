package org.zhd.data.provider.utils;

public enum DefaultEnum {
    DPT(1),
    ORG(1);

    private int code;

    DefaultEnum(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }
}
