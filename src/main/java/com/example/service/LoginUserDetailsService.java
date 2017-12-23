package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found.");
        }
        return new LoginUserDetails(user);
    }
    
    public List<User> findAll() {
        return userRepository.findAllOrderByName();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAllOrderByName(pageable);
    }
    
    public User findOne(String username) {
        return userRepository.findOne(username);
    }
    
    public User update(User user) {
        //customer.setUser(user);
        return userRepository.save(user);
    }
    
    public User create(User user) {
        //customer.setUser(user);
        return userRepository.save(user);
    }
    
    public void delete(String username) {
        userRepository.delete(username);
    }
    
}