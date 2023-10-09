package com.vilonov.PetClinic.controllers.utils;


public abstract class BaseController {
    public final String sharedKey = "SHARED_KEY";

    public static final String SUCCESS_STATUS = "success";
    public static final String ERROR_STATUS = "error";
    public static final int CODE_SUCCESS = 200;
    public static final int AUTH_FAILURE = 400;
}
