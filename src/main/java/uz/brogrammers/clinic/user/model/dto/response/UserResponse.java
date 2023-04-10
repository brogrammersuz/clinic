package uz.brogrammers.clinic.user.model.dto.response;

import lombok.Builder;
import lombok.Data;
import uz.brogrammers.clinic.user.model.entity.Role;
import uz.brogrammers.clinic.user.model.enums.RoleName;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private Long departmentId;
    private Set<RoleName> roles;
    private boolean isAdmin;

}
