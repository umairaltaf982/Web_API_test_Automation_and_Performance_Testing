package com.restful01.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceData {
    private String color;
    private String capacity;
    private Double price;
    private String generation;
    private Integer year;
    private String cpuModel;
    private String hardDiskSize;
}