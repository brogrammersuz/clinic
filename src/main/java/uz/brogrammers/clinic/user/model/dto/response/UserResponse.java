package uz.brogrammers.clinic.user.model.dto.response;

import lombok.Builder;
import lombok.Data;
import uz.brogrammers.clinic.user.model.enums.RoleName;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private Integer departmentId;
    private Set<RoleName> roles;
    private boolean isAdmin;

}
