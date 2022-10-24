package com.musala.dronemanagement.util;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 4:19:11 PM
 */
public class ResponseCode
{
    /**
     * Error Response Codes
     */

    public static final String DRONE_SAVE_SUCCESS = "d-300";
    public static final String DRONE_LIST_GET_SUCCESS = "d-301";
    public static final String DRONE_BATTERY_LEVEL_GET_SUCCESS = "d-302";
    public static final String LOAD_DRONE_MEDICATIONS_SUCCESS = "d-304";
    public static final String DRONE_MEDICATIONS_GET_SUCCESS = "d-305";

    /**
     * Error Response Codes
     */
    public static final String ALREADY_EXISTS = "801";
    public static final String INVALID_REQUEST = "802";
    public static final String ENTITY_NOT_FOUND = "803";
    public static final String SOMETHING_WENT_WRONG = "804";






    private ResponseCode()
    {
    }
}
