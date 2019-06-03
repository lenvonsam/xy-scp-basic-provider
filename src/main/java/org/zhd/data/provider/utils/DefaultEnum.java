package org.zhd.data.provider.utils;

/**
 * @author cth
 * @date 2019/06/03
 */
public enum DefaultEnum {
    /**
     * 顶级部门id
     */
    DPT(1),
    /**
     * 顶级机构id
     */
    ORG(1);

    private int code;

    DefaultEnum(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }
}
