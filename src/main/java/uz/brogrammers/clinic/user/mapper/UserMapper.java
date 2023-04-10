package uz.brogrammers.clinic.user.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.brogrammers.clinic.user.model.dto.request.UserRegistrationRequest;
import uz.brogrammers.clinic.user.model.dto.response.UserResponse;
import uz.brogrammers.clinic.user.model.entity.User;
import uz.brogrammers.clinic.util.Utils;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class UserMapper {

    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .departmentId(user.getDepartmentId())
                .roles(Utils.getUserRoles(user))
                .isAdmin(Utils.isAdmin(Optional.of(user)))
                .build();
    }

    public static List<UserResponse> usersToUserResponses(List<User> list) {
        return list.stream()
                .map(UserMapper::userToUserResponse)
                .sorted((c1, c2) -> Long.compare(c2.getId(), c1.getId()))
                .toList();
    }

}
