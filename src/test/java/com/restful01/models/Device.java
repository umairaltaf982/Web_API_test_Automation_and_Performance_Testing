package com.restful01.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {
    private String id;
    private String name;
    private DeviceData data;
    private String createdAt;
    private String updatedAt;
}
