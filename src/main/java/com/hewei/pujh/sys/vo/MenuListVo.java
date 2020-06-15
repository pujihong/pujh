package com.hewei.pujh.sys.vo;

import lombok.Data;

@Data
public class MenuListVo {
    private Long id;
    private String name;
    private String icon;
    private Integer level;
    private String levelName;
    private Integer status;
}
