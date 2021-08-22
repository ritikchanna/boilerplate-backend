package com.ritik.boilerplatebackend.endpoints.base.pojo;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class BaseResponse<T> {

    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String ERROR = "Error";

    private String message;
    private String requestId;
    private T data;


    public BaseResponse(String message, T data, String requestId) {
        this.message = message;
        if(StringUtils.isNotEmpty(requestId)) {
            this.requestId = requestId;
        }else {
            this.requestId = RandomStringUtils.randomAlphanumeric(12);
        }
        this.data = data;
    }

}
