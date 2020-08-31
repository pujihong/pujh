package com.hewei.pujh.tcp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestModel implements Serializable {
    private static final long serialVersionUID = -4984721370227929766L;
    private Integer lineId;
    private String line;
    private String injuredName; // 伤损名称
    private Integer position; // 位置 单位厘米
    private String Date; // 时间 yyyy-MM-dd HH:mm:ss
    private Integer level; // 没有默认1级
    private String equipmentName; // 设备名称
    private String fileName; // 文件名称 带格式
    private byte[] file; // 文件
}
