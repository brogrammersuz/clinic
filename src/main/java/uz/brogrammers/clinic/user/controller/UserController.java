package uz.brogrammers.clinic.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.department.service.DepartmentService;
import uz.brogrammers.clinic.user.mapper.UserMapper;
import uz.brogrammers.clinic.user.model.dto.request.UserRegistrationRequest;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;
import uz.brogrammers.clinic.user.model.dto.response.UserResponse;
import uz.brogrammers.clinic.user.model.entity.User;
import uz.brogrammers.clinic.user.service.RoleService;
import uz.brogrammers.clinic.user.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {

        if (userService.findByUsername(userRegistrationRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Username already exists!", null), HttpStatus.CONFLICT);
        }


        return null;

    }


}
