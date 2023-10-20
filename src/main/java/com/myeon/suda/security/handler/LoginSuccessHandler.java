package com.myeon.suda.security.handler;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myeon.suda.dto.MemberDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public LoginSuccessHandler(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public LoginSuccessHandler(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("--------------------------------------");
        log.info("onAuthenticationSuccess");

        MemberDTO authMember = (MemberDTO)authentication.getPrincipal();

    

        //실제프로젝트에서 회원정보를 수정하는 경우는 화면에서 비밀번호를 입력받아서 일치여부 판단
        //여기서는 최초 소셜로그인후 회원정보가 자동입력된 상태에서 회원정보를 수정하도록 유도
        boolean passwordResult = passwordEncoder.matches("1111", authMember.getPassword());

        // 소셜로그인으로 자동가입이 되고 비밀번호가 일치하는 경우에는 회원수정페이지로 이동
        if(passwordResult) {
            redirectStratgy.sendRedirect(request, response, "/member/modify?from=social");
        }
    }
    
}
