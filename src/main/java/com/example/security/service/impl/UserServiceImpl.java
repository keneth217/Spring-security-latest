package com.example.security.service.impl;

import com.example.security.repositories.UserRepository;
import com.example.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
     private final UserRepository userRepository;


    /**
     * @return 
     */
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){

            /**
             * @param username 
             * @return
             * @throws UsernameNotFoundException
             */
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(()-> new UsernameNotFoundException("user with email"+username+" not found"));
            }
        };
    }
}
