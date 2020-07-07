package com.hewei.pujh.enums;

import lombok.Getter;

@Getter
public enum BoolEnum {
    NO(0),
    YES(1);

    private Integer value;

    BoolEnum(Integer value) {
        this.value = value;
    }

    public static BoolEnum toEnum(Integer value) {
        for (BoolEnum e : BoolEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }
}
