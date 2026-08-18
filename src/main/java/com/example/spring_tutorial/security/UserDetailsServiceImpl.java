package com.example.spring_tutorial.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_tutorial.entity.User;
import com.example.spring_tutorial.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            User user =userRepository.findByUserName(username).get(0);
            String userRoleName=switch (user.getRoleId()){
                case 1 ->"ROLE_GENERAL";
                case 2 ->"ROLE_ADMIN";
                default -> "ROLE_GENERAL";
            };
            Collection<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userRoleName));
            return new UserDetailsImpl(user,authorities);
        }catch (Exception e){
            throw new UsernameNotFoundException("ユーザーが見つかりませんでした");
        }
    }
}
