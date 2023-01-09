package com.example.service.user;

import com.example.dto.UserRequest;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder encoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserRequest userRequest) {


        User user = userRequest.getUserFromUserRequest();
        user.setPassword(encoder.encode(userRequest.getPassword()));

        Role role = new Role();
        Set<Role> roleSet = new HashSet<>();

        if(user.getEmail().split("@")[1].equals("admin.in")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }else{
            role=roleService.findByName("OWNER");
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        return userRepository.save(user);
    }
}
