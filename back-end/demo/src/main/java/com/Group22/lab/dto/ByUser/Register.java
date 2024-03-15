package com.Group22.lab.dto.ByUser;

import lombok.Data;

@Data
public class Register {
    private String email;
    private String phone;
    private String id_number;
    private String username;
    private String password;
    private String user_type;
    private String confirmPassword;
}
