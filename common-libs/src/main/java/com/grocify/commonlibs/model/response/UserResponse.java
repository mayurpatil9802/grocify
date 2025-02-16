package com.grocify.commonlibs.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNo;
    private String address;
    private String role;
    private Map<String, String> metadata;

}
