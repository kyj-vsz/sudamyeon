package com.myeon.suda.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myeon.suda.dto.MemberDTO;
import com.myeon.suda.entity.Member;
import com.myeon.suda.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImple implements MemberService {     

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(MemberDTO memberDTO) {
        Member member = to_entity(memberDTO,passwordEncoder);
        // log.info(passwordEncoder.encode(member.getPassword()));
        // member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
    
}
