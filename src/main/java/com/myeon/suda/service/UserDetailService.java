package com.myeon.suda.service;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.myeon.suda.security.dto.ClubAuthMemberDTO;
import com.myeon.suda.dto.MemberDTO;
import com.myeon.suda.entity.Member;
import com.myeon.suda.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2 
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberRepository MemberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = MemberRepository.findByEmailId(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }
        Member Member = result.get();
        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                Member.getEmailId(),
                Member.getName(),
                Member.getNickname(),
                Member.getPassword(),
                Member.getPhone(),
                Member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );

        clubAuthMember.setName(Member.getName());
         clubAuthMember.setNickname(Member.getNickname());

    
        return clubAuthMember;
        }
        
}