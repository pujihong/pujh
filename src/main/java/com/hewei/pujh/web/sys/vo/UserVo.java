package com.hewei.pujh.web.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private Long id;
    private String username;
    private List<RoleVo> roleList;
}
