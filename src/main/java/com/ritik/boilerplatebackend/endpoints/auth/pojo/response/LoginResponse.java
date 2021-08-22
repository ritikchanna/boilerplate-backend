package com.ritik.boilerplatebackend.endpoints.auth.pojo.response;

import com.ritik.boilerplatebackend.endpoints.base.pojo.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.List;

@Getter
@Setter
public class LoginResponse extends BaseResponse<JSONObject> {

//    private String token;
//    private String type = "Bearer";
//    private Long id;
//    private String username;
//    private String email;
//    private List<String> roles;
    public LoginResponse(String token, Long id, String username, String email, List<String> roles, String message, String requestId) {
        super(message,new JSONObject(), requestId);
        JSONObject jsonObject = getData();
        jsonObject.put("token",token);
        jsonObject.put("id",id);
        jsonObject.put("username",username);
        jsonObject.put("email",email);
        jsonObject.put("roles",roles);
    }
}
