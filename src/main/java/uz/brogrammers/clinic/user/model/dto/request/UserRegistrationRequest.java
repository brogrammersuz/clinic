package uz.brogrammers.clinic.user.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotNull
    String username;
    @NotNull
    String password;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    String phone;
    @NotNull
    Long departmentId;
}
