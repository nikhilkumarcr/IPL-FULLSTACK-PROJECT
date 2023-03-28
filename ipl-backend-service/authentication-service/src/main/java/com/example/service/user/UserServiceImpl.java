package com.example.service.user;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.UserRequest;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exceptions.UserLoginException;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;
import com.example.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtils jwtTokenUtil;
    @Autowired
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public User save(UserRequest userRequest) throws UserLoginException {

        if (userRequest.getUsername().isEmpty() || userRequest.getUsername().length() == 0) {

            throw  new UserLoginException("Username can not empty !!!");

        } else if (userRequest.getEmail().isEmpty() || userRequest.getEmail().length() == 0) {

            throw new UserLoginException( "Email id can not be empty !!!");
        } else if(userRequest.getPassword().isEmpty() || userRequest.getPassword().length()<5){

            throw  new UserLoginException("Password can not be empty or Password should have more than 6 characters!!!");
        }

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

    }

    @Override
    public LoginResponse generateToken(LoginRequest loginRequest) throws UserLoginException {

        if (loginRequest.getUsername().isEmpty() || loginRequest.getUsername().length() == 0) {

            throw new UserLoginException("User name can not be empty !!! Enter a valid username");
        } else if (loginRequest.getPassword().isEmpty() || loginRequest.getPassword().length() == 0) {

            throw new UserLoginException("Password can not be empty !!! Enter a valid password");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    ));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateJwtToken(authentication);

            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            List<String> role = user.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            LoginResponse loginResponse = new LoginResponse();

            loginResponse.setUsername(loginRequest.getUsername());
            loginResponse.setToken(token);
            loginResponse.setRoles(role);

            return loginResponse;
        }catch(Exception e){

            throw new UserLoginException("Invalid log-in details !!! Check log-in details !!!");
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
