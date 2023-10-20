package com.myeon.suda.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User  implements OAuth2User {

    private String email_id;

    private String password;

    private String name;

    private String nickname;

    private String Phone;


    private Map<String, Object> attr;

    public ClubAuthMemberDTO(String email_id, String name, String nickname, String password, String phone,
                             Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(email_id,name, nickname, password, phone, authorities);
        this.attr = attr;
    }

    public ClubAuthMemberDTO(String email_id, String name, String nickname, String password, String Phone, Collection<? extends GrantedAuthority> authorities) {
        
        super(email_id, password, authorities);

        this.email_id = email_id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.Phone = Phone;
        
        
    

    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }

}