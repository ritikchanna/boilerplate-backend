package com.ritik.boilerplatebackend.endpoints.auth.pojo.request;


import com.ritik.boilerplatebackend.endpoints.base.pojo.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest extends BaseRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
