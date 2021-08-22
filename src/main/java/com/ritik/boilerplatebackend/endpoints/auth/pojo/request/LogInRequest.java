package com.ritik.boilerplatebackend.endpoints.auth.pojo.request;


import com.ritik.boilerplatebackend.endpoints.base.pojo.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest extends BaseRequest {

    private String username;
    private String password;
}
