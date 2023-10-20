package com.myeon.suda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String email_id;

    private String password;

    private String password2;

    private String name;

    private String nickname;

    private String phone;
}
