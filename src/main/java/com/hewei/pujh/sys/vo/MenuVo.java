package com.hewei.pujh.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private Long id;
    private Long parentId;
    private String name;
    private String url;
    private String icon;
    private List<MenuVo> children;
}
