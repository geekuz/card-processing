package com.demo.cardprocessing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -9168615476075217667L;
    private String userId;
    private String name;
    private String email;
    private String encryptedPassword;
}
