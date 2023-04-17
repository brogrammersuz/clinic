package uz.brogrammers.clinic.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.department.service.DepartmentService;
import uz.brogrammers.clinic.user.mapper.UserMapper;
import uz.brogrammers.clinic.user.model.dto.request.UpdateUserRequest;
import uz.brogrammers.clinic.user.model.dto.request.UserRegistrationRequest;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;
import uz.brogrammers.clinic.user.model.dto.response.UserResponse;
import uz.brogrammers.clinic.user.model.entity.Role;
import uz.brogrammers.clinic.user.model.entity.User;
import uz.brogrammers.clinic.user.model.enums.RoleName;
import uz.brogrammers.clinic.user.model.enums.UserStatus;
import uz.brogrammers.clinic.user.service.RoleService;
import uz.brogrammers.clinic.user.service.UserService;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentService departmentService;

    @GetMapping("/me")
    public UserResponse findByUsername(@RequestParam(name = "username") String username) {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found by username " + username)
        );

        return UserMapper.userToUserResponse(user);

    }

    @GetMapping("/list")
    public List<UserResponse> findAll() {
        var usersList = userService.findAll();
        return UserMapper.usersToUserResponses(usersList);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {

        if (userService.findByUsername(userRegistrationRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Username already exists!", null), HttpStatus.CONFLICT);
        }

        var departmentOptional = departmentService.findById(userRegistrationRequest.getDepartmentId());

        if (departmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Department not found", null));
        }

        var department = departmentOptional.get();

        Role userRole;

        if ("Регистратура".equals(department.getName())) {
            userRole = roleService.findByName(RoleName.ROLE_ADMIN);
        } else {
            userRole = roleService.findByName(RoleName.ROLE_USER);
        }

        String firstName = StringUtils.capitalize(userRegistrationRequest.getFirstName().trim().toLowerCase());
        String lastName = StringUtils.capitalize(userRegistrationRequest.getLastName().trim().toLowerCase());

        User user = User.builder()
                .status(UserStatus.ACTIVE)
                .username(userRegistrationRequest.getUsername().trim())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .firstName(firstName)
                .lastName(lastName)
                .roles(Collections.singleton(userRole))
                .created(ZonedDateTime.now())
                .updated(ZonedDateTime.now())
                .phone(userRegistrationRequest.getPhone())
                .departmentId(userRegistrationRequest.getDepartmentId())
                .build();

        var savedUser = userService.save(user);
        log.info("User {} successfully registered", savedUser);

        var res = UserMapper.usersToUserResponses(userService.findAll());
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully", res));

    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest request) {

        var userOptional = userService.findById(request.getId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "User not found!", null));
        }

        var departmentOptional = departmentService.findById(request.getDepartmentId());

        if (departmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Department not found", null));
        }

        var existingUser = userOptional.get();

        String firstName = StringUtils.capitalize(request.getFirstName().trim().toLowerCase());
        String lastName = StringUtils.capitalize(request.getLastName().trim().toLowerCase());

        User user = User.builder()
                .status(UserStatus.ACTIVE)
                .username(existingUser.getUsername().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(firstName)
                .lastName(lastName)
                .roles(existingUser.getRoles())
                .created(existingUser.getCreated())
                .updated(ZonedDateTime.now())
                .phone(request.getPhone())
                .departmentId(request.getDepartmentId())
                .build();

        var savedUser = userService.save(user);
        log.info("User {} successfully updated", savedUser);

        var res = UserMapper.usersToUserResponses(userService.findAll());
        return ResponseEntity.ok().body(new ApiResponse(true, "User successfully updated", res));
    }


}
