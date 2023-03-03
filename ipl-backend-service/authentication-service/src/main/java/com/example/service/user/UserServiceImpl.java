package com.example.service.user;

import com.example.dto.UserRequest;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.errors.ExceptionErrorHandler;
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
import java.util.HashSet;
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


    @Override
    public User save(UserRequest userRequest) {

        if (userRequest.getUsername().isEmpty() || userRequest.getUsername().length() == 0) {

            throw new ExceptionErrorHandler("801", "User name can not be empty !!!");

        } else if (userRequest.getEmail().isEmpty() || userRequest.getEmail().length() == 0) {

            throw new ExceptionErrorHandler("801", "Email id can not be empty !!!");
        } else if(userRequest.getPassword().isEmpty() || userRequest.getPassword().length()<6){

            throw  new ExceptionErrorHandler("801","Password can not be empty or Password should have more than 6 characters!!!");
        }

        try {
            User user = new User();

            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(encoder.encode(userRequest.getPassword()));

            Role role = new Role();
            Set<Role> roleSet = new HashSet<>();

            if (user.getEmail().split("@")[1].equals("admin.in")) {

                role = roleService.findByName("ADMIN");
                roleSet.add(role);

            } else {

                role = roleService.findByName("OWNER");
                roleSet.add(role);
            }
            user.setRoles(roleSet);
            return userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new ExceptionErrorHandler("802", "User Details is null !!!" + e.getMessage());
        } catch (Exception e) {
            throw new ExceptionErrorHandler("802", "Error in user service  !!!");
        }
    }

    @Override
    public Boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }
}
