package com.musala.dronemanagement.dto.response;

import lombok.Data;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 5:05:18 PM
 */
@Data
public class SuccessResponse
{
    private String responseCode;

    private String responseDescription;

    private Object data;
}
