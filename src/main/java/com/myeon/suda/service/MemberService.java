package com.myeon.suda.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myeon.suda.dto.MemberDTO;
import com.myeon.suda.entity.Member;
import com.myeon.suda.entity.MemberRole;
import com.myeon.suda.repository.MemberRepository;


public interface MemberService {

    public void join(MemberDTO memberDTO);
        
  
    // public Member saveMember(Member member){
    //     validateDuplicateMember(member);
    //     return memberRepository.save(member);
    // }

    // private void validateDuplicateMember(Member member){
    //     Member findMember = memberRepository.findByEmailId(member.getEmailId());
    //     if(findMember != null){
    //         throw new IllegalStateException("이미 가입된 회원입니다.");
    //     }
    // }

    default MemberDTO to_dto(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
            .email_id(member.getEmailId())
            .name(member.getName())
            .nickname(member.getNickname())
            .password(member.getPassword())
            .password2(member.getPassword())
            .phone(member.getPhone())
            .build();

            return memberDTO;
    }

    default Member to_entity(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        System.out.println("********************"+passwordEncoder.encode(memberDTO.getPassword()));
        Member member = Member.builder()
            .emailId(memberDTO.getEmail_id())
            .name(memberDTO.getName())
            .nickname(memberDTO.getNickname())
            .password(passwordEncoder.encode(memberDTO.getPassword()))
            .phone(memberDTO.getPhone())
            .roleSet(new HashSet<>())
            .build();

            member.addMemberRole(MemberRole.USER);
            return member;
    }
    

}
